package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The dbs certification
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "DBS_CERTIFICATION")
public class DBSCertification extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The expiration date. */
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    /** The document id. */
    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(final Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "DBSCertification{" +
                "expirationDate=" + expirationDate +
                ", document=" + document +
                "} " + super.toString();
    }
}
