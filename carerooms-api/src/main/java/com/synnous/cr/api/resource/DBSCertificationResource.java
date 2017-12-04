package com.synnous.cr.api.resource;

import java.util.Date;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class DBSCertificationResource {

    /** The id. */
    private String id;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    /** The expirationDate. */
    private Date expirationDate;

    /** The document. */
    private DocumentResource document;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public DocumentResource getDocument() {
        return document;
    }

    public void setDocument(final DocumentResource document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "DBSCertificationResource{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", expirationDate=" + expirationDate +
                ", document=" + document +
                '}';
    }
}
