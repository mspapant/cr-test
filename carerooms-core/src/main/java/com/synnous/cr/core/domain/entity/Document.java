package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * The document entity.
 *
 * @author : Manos Papantonakos on 31/08/2016.
 */
@Entity(name = "DOCUMENT")
public class Document extends IdTimestampEntity implements Serializable {

    static final long serialVersionUID = 1L;

    /** The key. */
    @Column(name = "OBJECT_KEY")
    private String objectKey;

    /** The name. */
    @Column(name = "URL")
    private String url;

    /** The base 64 data. */
    @Transient
    private String base64data;

    /** The file name. */
    @Transient
    private String fileName;

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(final String objectKey) {
        this.objectKey = objectKey;
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
        return "Document{" +
                "objectKey='" + objectKey + '\'' +
                ", url='" + url + '\'' +
                ", base64data='" + base64data + '\'' +
                ", fileName='" + fileName + '\'' +
                "} " + super.toString();
    }
}
