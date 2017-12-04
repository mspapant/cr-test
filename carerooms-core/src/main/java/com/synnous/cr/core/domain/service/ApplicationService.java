package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.entity.Application;
import com.synnous.cr.core.domain.entity.Appointment;
import com.synnous.cr.core.domain.entity.Document;
import com.synnous.cr.core.domain.entity.Training;
import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.enumeration.DocumentType;

import java.util.List;

/**
 * The user service.
 *
 * @author : Manos Papantonakos.
 */
public interface ApplicationService {

    /**
     * Returns the applications.
     *
     * @param userId
     *          the user id
     * @return  the applications
     */
    List<Application> getApplications(final String userId);

    /**
     * Creates the appointment.
     *
     * @param userId
     *          the user id
     * @param applicationId
     *          the application id
     * @param request
     *          the request
     * @return  the visit
     */
    Appointment createAppointment(final String userId, final String applicationId, final Appointment request);

    /**
     * Updates the appointment.
     *
     * @param userId
     *          the user id
     * @param appointmentId
     *          the appointment id
     * @param request
     *          the request
     * @return  the visit
     */
    Appointment updateAppointment(final String userId, final String appointmentId, final String applicationId, final Appointment request);

    /**
     * Update training document.
     *
     * @param userId
     *          the user id
     * @param trainingId
     *          the training id
     * @param documentType
     *          the document type
     * @param document
     *          the document
     * @return  training
     */
    Training updateTrainingDocument(final String userId, final String trainingId, final DocumentType documentType, final Document document);

    /**
     * Updates the application.
     *
     * @param userId
     *          the user id
     * @param applicationId
     *          the application id
     * @param request
     *          the application
     * @return the application
     */
    Application updateApplication(final String userId, final String applicationId, final Application request);
}
