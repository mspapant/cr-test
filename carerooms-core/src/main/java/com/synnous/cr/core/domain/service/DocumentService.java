package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.entity.Document;

/**
 * The user service.
 *
 * @author : Manos Papantonakos.
 */
public interface DocumentService {

    /**
     * Updates the document.
     *
     * @param base64Data
     *         the base 64 data
     * @return the document
     */
    Document updateDocument(final String base64Data, final Document document, final String fileName, final String bucketName);

    /**
     * Creates an empty document.
     *
     * @return the document
     */
    Document createEmptyDocument();
}
