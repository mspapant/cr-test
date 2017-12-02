package com.synnous.cr.core.domain.entity.root;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The id timestamp entity.
 *
 * @author : Manos Papantonakos.
 */
@MappedSuperclass
public class TimestampEntity {

    /** The created at. */
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    /** The updated at. */
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "IdTimestampEntity{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "} " + super.toString();
    }
}
