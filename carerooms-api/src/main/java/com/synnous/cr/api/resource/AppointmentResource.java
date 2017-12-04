package com.synnous.cr.api.resource;

import com.synnous.cr.core.domain.enumeration.AppointmentStatus;

import java.util.Date;

/**
 * The appointment resource
 *
 * @author : Manos Papantonakos.
 */
public class AppointmentResource {

    /** The id. */
    private String id;

    /** The visit user. */
    private UserMiniResource visitUser;

    /** The status. */
    private AppointmentStatus status;

    /** The appointment. */
    private Date appointment;

    /** The updated at. */
    private Date updatedAt;

    /** The created at. */
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public UserMiniResource getVisitUser() {
        return visitUser;
    }

    public void setVisitUser(final UserMiniResource visitUser) {
        this.visitUser = visitUser;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(final AppointmentStatus status) {
        this.status = status;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(final Date appointment) {
        this.appointment = appointment;
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

    @Override
    public String toString() {
        return "InHomeVisitResource{" +
                "id='" + id + '\'' +
                ", visitUser=" + visitUser +
                ", status=" + status +
                ", appointment=" + appointment +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
