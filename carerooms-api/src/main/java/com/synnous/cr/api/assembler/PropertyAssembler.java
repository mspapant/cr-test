package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.PropertyResource;
import com.synnous.cr.core.domain.entity.Property;
import com.synnous.cr.core.domain.entity.PropertyPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;

/**
 * The Property assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class PropertyAssembler implements ResourceAssembler<Property, PropertyResource>, DomainAssembler<Property, PropertyResource> {

    @Autowired
    private PropertyPersonAssembler propertyPersonAssembler;

    @Autowired
    private DocumentAssembler documentAssembler;

    @Override
    public PropertyResource toResource(final Property domain) {
        if (domain == null) {
            return null;
        }
        final PropertyResource target = new PropertyResource();
        target.setId(domain.getId());
        target.setInsurancePolicyNo(domain.getInsurancePolicyNo());
        target.setInsurancePolicyDocument(documentAssembler.toResource(domain.getInsurancePolicyDocument()));
        target.setProofHomeOwnership(documentAssembler.toResource(domain.getProofHomeOwnership()));
        target.setApprovalFromMortgageLender(domain.getApprovalFromMortgageLender());
        target.setRegistrationWithCouncil(domain.getRegistrationWithCouncil());
        target.setPersons(propertyPersonAssembler.toResource(domain.getPersons()));
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        return target;
    }

    @Override
    public Property toDomain(final PropertyResource resource) {
        final Property target = new Property();
        target.setId(resource.getId());
        target.setInsurancePolicyNo(resource.getInsurancePolicyNo());
        target.setInsurancePolicyDocument(documentAssembler.toDomain(resource.getInsurancePolicyDocument()));
        target.setProofHomeOwnership(documentAssembler.toDomain(resource.getProofHomeOwnership()));
        target.setApprovalFromMortgageLender(resource.getApprovalFromMortgageLender());
        target.setRegistrationWithCouncil(resource.getRegistrationWithCouncil());
        target.setPersons(propertyPersonAssembler.toDomainSet(resource.getPersons()));

        if (!CollectionUtils.isEmpty(target.getPersons())) {
            for (final PropertyPerson person : target.getPersons()) {
                person.setProperty(target);
            }
        }
        return target;
    }
}
