package com.synnous.cr.core.domain.adapter;

/**
 * The file adapter.
 *
 * @author : Manos Papantonakos on 28/3/2015.
 */
public interface FileAdapter {


    enum ContentType {
        IMAGE("image/png"),
        VIDEO("video/mp4"),
        APPLICATION_PDF("application/pdf"),
        APPLICATION_EXCEL("application/vnd.ms-excel"),
        APPLICATION_DOC("application/msword"),;
        public final String id;

        ContentType(final String id) {
            this.id = id;
        }

    }

    /**
     * Uploads the file into the bucket with file name.
     *
     * @param bucket
     *         the bucket
     * @param filename
     *         the filename
     */
    void deleteFile(final String bucket, final String filename);

    /**
     * Uploads the file into the bucket with file name.
     *
     * @param bucket
     *         the bucket
     * @param filename
     *         the filename
     * @param data
     *         the data
     * @return the final url
     */
    String uploadFile(final String bucket, final String filename, final byte[] data, final String contentType);
}
