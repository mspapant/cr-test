package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * The avatar entity.
 *
 * @author : Manos Papantonakos on 31/08/2016.
 */
@Entity(name = "AVATAR")
public class Avatar extends IdTimestampEntity implements Serializable {

    static final long serialVersionUID = 1L;

    /** The key. */
    @Column(name = "OBJECT_KEY")
    private String objectKey;

    /** The name. */
    @Column(name = "URL")
    private String url;

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

    @Override
    public String toString() {
        return "Avatar{" +
                "objectKey='" + objectKey + '\'' +
                ", url='" + url + '\'' +
                "} " + super.toString();
    }
}
