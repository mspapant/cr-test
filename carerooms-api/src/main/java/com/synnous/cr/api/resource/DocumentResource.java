package com.synnous.cr.api.resource;

import javax.persistence.Transient;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class DocumentResource {

    /** The id. */
    private String id;

    /** The url. */
    private String url;

    /** The base64 data. */
    private String base64data;

    /** The file name. */
    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getBase64data() {
        return base64data;
    }

    public void setBase64data(final String base64data) {
        this.base64data = base64data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "DocumentResource{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", base64data='" + base64data + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
