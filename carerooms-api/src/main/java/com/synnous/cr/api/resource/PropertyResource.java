package com.synnous.cr.api.resource;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class PropertyResource {

    /** The id. */
    private String id;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    /** The persons */
    private Set<PropertyPersonResource> persons;

    /** The insurance policy no. */
    private String insurancePolicyNo;

    /** The insurance policy document. */
    private DocumentResource insurancePolicyDocument;

    /** The proof of home ownership. */
    private DocumentResource proofHomeOwnership;

    /** The insurance policy no. */
    private Boolean approvalFromMortgageLender;

    /** The insurance policy no. */
    private Boolean registrationWithCouncil;

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

    public Set<PropertyPersonResource> getPersons() {
        return persons;
    }

    public void setPersons(final Set<PropertyPersonResource> persons) {
        this.persons = persons;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(final String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public DocumentResource getInsurancePolicyDocument() {
        return insurancePolicyDocument;
    }

    public void setInsurancePolicyDocument(final DocumentResource insurancePolicyDocument) {
        this.insurancePolicyDocument = insurancePolicyDocument;
    }

    public DocumentResource getProofHomeOwnership() {
        return proofHomeOwnership;
    }

    public void setProofHomeOwnership(final DocumentResource proofHomeOwnership) {
        this.proofHomeOwnership = proofHomeOwnership;
    }

    public Boolean getApprovalFromMortgageLender() {
        return approvalFromMortgageLender;
    }

    public void setApprovalFromMortgageLender(final Boolean approvalFromMortgageLender) {
        this.approvalFromMortgageLender = approvalFromMortgageLender;
    }

    public Boolean getRegistrationWithCouncil() {
        return registrationWithCouncil;
    }

    public void setRegistrationWithCouncil(final Boolean registrationWithCouncil) {
        this.registrationWithCouncil = registrationWithCouncil;
    }

    @Override
    public String toString() {
        return "PropertyResource{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", persons=" + persons +
                ", insurancePolicyNo='" + insurancePolicyNo + '\'' +
                ", insurancePolicyDocument=" + insurancePolicyDocument +
                ", proofHomeOwnership=" + proofHomeOwnership +
                ", approvalFromMortgageLender=" + approvalFromMortgageLender +
                ", registrationWithCouncil=" + registrationWithCouncil +
                '}';
    }
}
