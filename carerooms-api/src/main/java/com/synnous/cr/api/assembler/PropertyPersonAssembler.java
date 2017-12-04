package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.PropertyPersonResource;
import com.synnous.cr.core.domain.entity.PropertyPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The PropertyPerson assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class PropertyPersonAssembler implements ResourceAssembler<PropertyPerson, PropertyPersonResource>, DomainAssembler<PropertyPerson, PropertyPersonResource> {

    @Autowired
    private DBSCertificationAssembler dbsCertificationAssembler;

    @Override
    public PropertyPersonResource toResource(final PropertyPerson domain) {
        if (domain == null) {
            return null;
        }
        final PropertyPersonResource target = new PropertyPersonResource();
        target.setId(domain.getId());
        target.setFirstName(domain.getFirstName());
        target.setLastName(domain.getLastName());
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        target.setDbsCertification(dbsCertificationAssembler.toResource(domain.getDbsCertification()));
        return target;
    }

    @Override
    public PropertyPerson toDomain(final PropertyPersonResource resource) {
        final PropertyPerson target = new PropertyPerson();
        target.setId(resource.getId());
        target.setFirstName(resource.getFirstName());
        target.setLastName(resource.getLastName());
        target.setDbsCertification(dbsCertificationAssembler.toDomain(resource.getDbsCertification()));
        return target;
    }
}
