package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.adapter.FileAdapter;
import com.synnous.cr.core.domain.entity.Document;
import com.synnous.cr.core.domain.repository.DocumentRepository;
import com.synnous.cr.core.domain.util.DomainUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * The document service.
 *
 * @author : Manos Papantonakos.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    /** The file adapter. */
    @Autowired
    private FileAdapter fileAdapter;

    /** The document repository. */
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document updateDocument(final String base64Data, final Document document, final String fileName, final String bucketName) {
        final String fileMimeType = DomainUtils.getFileMimeType(fileName);
        final String extension = DomainUtils.getFilenameExtension(fileName);
        final String remoteName = document.getId() + "." + extension;
        document.setUpdatedAt(new Date());

        final String url = fileAdapter.uploadFile(bucketName, remoteName, Base64.decodeBase64(base64Data), fileMimeType) + "?t=" + new Date().getTime();
        document.setUrl(url);
        document.setObjectKey(remoteName);

        return documentRepository.save(document);
    }


    @Override
    public Document createEmptyDocument() {
        Document document = new Document();
        document.setCreatedAt(new Date());
        document.setUpdatedAt(new Date());
        return documentRepository.save(document);
    }
}
