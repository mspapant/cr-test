package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.DBSCertificationResource;
import com.synnous.cr.core.domain.entity.DBSCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The DBSCertification assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class DBSCertificationAssembler implements ResourceAssembler<DBSCertification, DBSCertificationResource>, DomainAssembler<DBSCertification, DBSCertificationResource> {

    @Autowired
    private DocumentAssembler documentAssembler;

    @Override
    public DBSCertificationResource toResource(final DBSCertification domain) {
        if (domain == null) {
            return null;
        }
        final DBSCertificationResource target = new DBSCertificationResource();
        target.setId(domain.getId());
        target.setExpirationDate(domain.getExpirationDate());
        target.setDocument(documentAssembler.toResource(domain.getDocument()));
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        return target;
    }

    @Override
    public DBSCertification toDomain(final DBSCertificationResource resource) {
        final DBSCertification target = new DBSCertification();
        target.setId(resource.getId());
        target.setExpirationDate(resource.getExpirationDate());
        target.setDocument(documentAssembler.toDomain(resource.getDocument()));
        return target;
    }
}
