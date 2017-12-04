package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.adapter.FileAdapter;
import com.synnous.cr.core.domain.entity.DBSCertification;
import com.synnous.cr.core.domain.entity.Document;
import com.synnous.cr.core.domain.entity.Property;
import com.synnous.cr.core.domain.entity.PropertyPerson;
import com.synnous.cr.core.domain.enumeration.DocumentType;
import com.synnous.cr.core.domain.exception.IllegalDataException;
import com.synnous.cr.core.domain.repository.DBSCertificationRepository;
import com.synnous.cr.core.domain.repository.DocumentRepository;
import com.synnous.cr.core.domain.repository.PropertyPersonRepository;
import com.synnous.cr.core.domain.repository.PropertyRepository;
import com.synnous.cr.core.domain.util.DomainUtils;
import com.synnous.cr.core.property.StorageProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * The property service.
 *
 * @author : Manos Papantonakos.
 */
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    /** The property repository. */
    @Autowired
    private PropertyRepository propertyRepository;

    /** The property person repository. */
    @Autowired
    private PropertyPersonRepository propertyPersonRepository;

    /** The storage properties. */
    @Autowired
    private StorageProperties storageProperties;

    /** The db certification repository. */
    @Autowired
    private DBSCertificationRepository dbsCertificationRepository;

    /** The document service. */
    @Autowired
    private DocumentService documentService;

    @Override
    public Property getProperty(final String userId) {
        final List<Property> properties = propertyRepository.findByUserId(userId);
        if (!CollectionUtils.isEmpty(properties)) {
            return properties.get(0);
        }
        return null;
    }

    @Override
    public Property updateProperty(final String userId, final String propertyId, final Property request) {
        final Property db = propertyRepository.findOne(propertyId);
        db.setUpdatedAt(new Date());
        db.setInsurancePolicyNo(request.getInsurancePolicyNo());
        db.setApprovalFromMortgageLender(request.getApprovalFromMortgageLender());
        db.setRegistrationWithCouncil(request.getRegistrationWithCouncil());

        db.getPersons().clear();
        if (!CollectionUtils.isEmpty(request.getPersons())) {
            for (final PropertyPerson item : request.getPersons()) {
                PropertyPerson target;
                if (StringUtils.isEmpty(item.getId())) {
                    target = new PropertyPerson();
                    target.setFirstName(item.getFirstName());
                    target.setLastName(item.getLastName());
                    target.setProperty(db);
                } else {
                    target = propertyPersonRepository.findOne(item.getId());
                    target.setFirstName(item.getFirstName());
                    target.setLastName(item.getLastName());
                }
                db.getPersons().add(target);
            }
        }
        db.setUpdatedAt(new Date());
        return propertyRepository.save(db);
    }

    @Override
    public Property updatePropertyDocument(final String userId, final String propertyId, final DocumentType documentType, final Document request) {
        final Property property = propertyRepository.findOne(propertyId);
        Document document = null;
        if (DocumentType.INSURANCE_POLICY.equals(documentType)) {
            document = property.getInsurancePolicyDocument();
        } else if (DocumentType.PROOF_OF_HOME_OWNERSHIP.equals(documentType)) {
            document = property.getProofHomeOwnership();
        }
        if (document == null) {
            document = documentService.createEmptyDocument();
        }
        if (DocumentType.INSURANCE_POLICY.equals(documentType)) {
            property.setInsurancePolicyDocument(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getPropertyDocumentBucket()));
        } else if (DocumentType.PROOF_OF_HOME_OWNERSHIP.equals(documentType)) {
            property.setProofHomeOwnership(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getPropertyDocumentBucket()));
        }
        property.setUpdatedAt(new Date());
        return propertyRepository.save(property);
    }

    @Override
    public DBSCertification updatePropertyPersonDBSCertification(final String userId, final String propertyId, final String personId, final DBSCertification request) {
        if (request.getExpirationDate() == null) {
            throw new IllegalDataException(IllegalDataException.Reason.DBS_EXPIRATION_EMPTY);
        }
        if (request.getExpirationDate().before(new Date())) {
            throw new IllegalDataException(IllegalDataException.Reason.DBS_EXPIRATION_IN_PAST);
        }
        final PropertyPerson person = propertyPersonRepository.findOne(personId);
        DBSCertification certification = person.getDbsCertification();
        if (certification == null) {
            certification = new DBSCertification();
            certification.setCreatedAt(new Date());
            certification.setUpdatedAt(new Date());
            dbsCertificationRepository.save(certification);
        }
        final Document document = certification.getDocument() == null ? documentService.createEmptyDocument() : certification.getDocument();
        certification.setDocument(documentService.updateDocument(request.getDocument().getBase64data(), document, request.getDocument().getFileName(), storageProperties.getPropertyDocumentBucket()));
        certification.setExpirationDate(request.getExpirationDate());

        dbsCertificationRepository.save(certification);

        person.setDbsCertification(certification);
        person.setUpdatedAt(new Date());
        propertyPersonRepository.save(person);
        return certification;
    }
}
