package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * The home visit
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "PROPERTY")
public class Property extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /** The persons. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyPerson> persons = new HashSet<>();

    /** The insurance policy no. */
    @Column(name = "INSURANCE_POLICY_NO")
    private String insurancePolicyNo;

    /** The insurance policy document. */
    @ManyToOne
    @JoinColumn(name = "INSURANCE_POLICY_DOCUMENT_ID")
    private Document insurancePolicyDocument;

    /** The proof of home ownership. */
    @ManyToOne
    @JoinColumn(name = "PROOF_HOME_OWNERSHIP_DOCUMENT_ID")
    private Document proofHomeOwnership;

    /** The insurance policy no. */
    @Column(name = "APPROVAL_FROM_MORTGAGE_LENDER")
    private Boolean approvalFromMortgageLender;

    /** The insurance policy no. */
    @Column(name = "REGISTRATION_WITH_COUNCIL")
    private Boolean registrationWithCouncil;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<PropertyPerson> getPersons() {
        return persons;
    }

    public void setPersons(final Set<PropertyPerson> persons) {
        this.persons = persons;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(final String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public Document getInsurancePolicyDocument() {
        return insurancePolicyDocument;
    }

    public void setInsurancePolicyDocument(final Document insurancePolicyDocument) {
        this.insurancePolicyDocument = insurancePolicyDocument;
    }

    public Document getProofHomeOwnership() {
        return proofHomeOwnership;
    }

    public void setProofHomeOwnership(final Document proofHomeOwnership) {
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
        return "Property{" +
                "user=" + user +
                ", persons=" + persons +
                ", insurancePolicyNo='" + insurancePolicyNo + '\'' +
                ", insurancePolicyDocument=" + insurancePolicyDocument +
                ", proofHomeOwnership=" + proofHomeOwnership +
                ", approvalFromMortgageLender=" + approvalFromMortgageLender +
                ", registrationWithCouncil=" + registrationWithCouncil +
                "} " + super.toString();
    }
}
