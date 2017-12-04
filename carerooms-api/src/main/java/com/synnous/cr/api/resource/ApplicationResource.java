package com.synnous.cr.api.resource;

import com.synnous.cr.api.assembler.TrainingAssembler;
import com.synnous.cr.core.domain.enumeration.ApplicationStatus;
import com.synnous.cr.core.domain.enumeration.ApplicationStep;

import java.util.Date;

/**
 * The application resource
 *
 * @author : Manos Papantonakos.
 */
public class ApplicationResource {

    /** The id. */
    private String id;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    /** The user. */
    private UserMiniResource user;

    /** The property. */
    private PropertyResource property;

    /** The appointment. */
    private AppointmentResource appointment;

    /** The step. */
    private ApplicationStep step;

    /** The status. */
    private ApplicationStatus status;

    /** The training. */
    private TrainingResource training;

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

    public UserMiniResource getUser() {
        return user;
    }

    public void setUser(final UserMiniResource user) {
        this.user = user;
    }

    public PropertyResource getProperty() {
        return property;
    }

    public void setProperty(final PropertyResource property) {
        this.property = property;
    }

    public AppointmentResource getAppointment() {
        return appointment;
    }

    public void setAppointment(final AppointmentResource appointment) {
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

    public TrainingResource getTraining() {
        return training;
    }

    public void setTraining(final TrainingResource training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "ApplicationResource{" +
                "id='" + id + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", property=" + property +
                ", appointment=" + appointment +
                ", step=" + step +
                ", status=" + status +
                ", training=" + training +
                '}';
    }
}
