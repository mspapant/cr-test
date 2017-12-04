package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;
import com.synnous.cr.core.domain.enumeration.ApplicationStatus;
import com.synnous.cr.core.domain.enumeration.ApplicationStep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The application
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "APPLICATION")
public class Application extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /** The property. */
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    /** The appointment. */
    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID")
    private Appointment appointment;

    /** The training. */
    @ManyToOne
    @JoinColumn(name = "TRAINING_ID")
    private Training training;

    /** The step. */
    @Enumerated(EnumType.STRING)
    @Column(name = "STEP")
    private ApplicationStep step;

    /** The status. */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ApplicationStatus status;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(final Property property) {
        this.property = property;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(final Appointment appointment) {
        this.appointment = appointment;
    }

    public ApplicationStep getStep() {
        return step;
    }

    public void setStep(final ApplicationStep step) {
        this.step = step;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(final ApplicationStatus status) {
        this.status = status;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(final Training training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "Application{" +
                "user=" + user +
                ", property=" + property +
                ", appointment=" + appointment +
                ", training=" + training +
                ", step=" + step +
                ", status=" + status +
                "} " + super.toString();
    }
}
