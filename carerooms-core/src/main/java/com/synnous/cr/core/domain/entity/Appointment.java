package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;
import com.synnous.cr.core.domain.enumeration.AppointmentStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The appointment
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "APPOINTMENT")
public class Appointment extends IdTimestampEntity {

    static final long serialVersionUID = 1L;

    /** The visit user. */
    @ManyToOne
    @JoinColumn(name = "VISIT_USER_ID")
    private User visitUser;

    /** The status. */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppointmentStatus status;

    /** The appointment. */
    @Enumerated(EnumType.STRING)
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointment;

    public User getVisitUser() {
        return visitUser;
    }

    public void setVisitUser(final User visitUser) {
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

    @Override
    public String toString() {
        return "Appointment{" +
                ", visitUser=" + visitUser +
                ", visitStatus=" + status +
                ", appointment=" + appointment +
                "} " + super.toString();
    }
}
