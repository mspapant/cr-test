package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.DocumentResource;
import com.synnous.cr.core.domain.entity.Document;
import org.springframework.stereotype.Component;

/**
 * The Document assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class DocumentAssembler implements ResourceAssembler<Document, DocumentResource>, DomainAssembler<Document, DocumentResource> {

    @Override
    public DocumentResource toResource(final Document domain) {
        if (domain == null) {
            return null;
        }
        final DocumentResource target = new DocumentResource();
        target.setId(domain.getId());
        target.setUrl(domain.getUrl());
        return target;
    }

    @Override
    public Document toDomain(final DocumentResource resource) {
        final Document target = new Document();
        target.setId(resource.getId());
        target.setBase64data(resource.getBase64data());
        target.setFileName(resource.getFileName());
        return target;
    }
}
