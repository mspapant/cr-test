package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.adapter.EmailAdapter;
import com.synnous.cr.core.domain.entity.Application;
import com.synnous.cr.core.domain.entity.Appointment;
import com.synnous.cr.core.domain.entity.Document;
import com.synnous.cr.core.domain.entity.Training;
import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.enumeration.ApplicationStatus;
import com.synnous.cr.core.domain.enumeration.ApplicationStep;
import com.synnous.cr.core.domain.enumeration.AppointmentStatus;
import com.synnous.cr.core.domain.enumeration.DocumentType;
import com.synnous.cr.core.domain.enumeration.Template;
import com.synnous.cr.core.domain.enumeration.UserType;
import com.synnous.cr.core.domain.repository.ApplicationRepository;
import com.synnous.cr.core.domain.repository.AppointmentRepository;
import com.synnous.cr.core.domain.repository.TrainingRepository;
import com.synnous.cr.core.domain.repository.UserRepository;
import com.synnous.cr.core.property.EmailProperties;
import com.synnous.cr.core.property.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The user service.
 *
 * @author : Manos Papantonakos.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    /** The application repository. */
    @Autowired
    private ApplicationRepository applicationRepository;

    /** The appointment repository. */
    @Autowired
    private AppointmentRepository appointmentRepository;

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /** The training repository. */
    @Autowired
    private TrainingRepository trainingRepository;

    /** The document service. */
    @Autowired
    private DocumentService documentService;

    /** The storage properties. */
    @Autowired
    private StorageProperties storageProperties;

    /** The email adapter. */
    @Autowired
    private EmailAdapter emailAdapter;

    /** The template service. */
    @Autowired
    private TemplateService templateService;

    /** The email properties. */
    @Autowired
    private EmailProperties emailProperties;

    /**
     * Returns the applications.
     *
     * @param userId
     *         the user id
     * @return the applications
     */
    public List<Application> getApplications(final String userId) {
        final User user = userRepository.findOne(userId);
        if (UserType.AREA_MANAGER.equals(user.getUserType())) {
            return applicationRepository.findAll();
        } else {
            if (CollectionUtils.isEmpty(user.getApplications())) {
                return Collections.emptyList();
            } else {
                return new LinkedList<>(user.getApplications());
            }
        }
    }

    @Override
    public Appointment createAppointment(final String userId, final String applicationId, final Appointment request) {
        final Application application = applicationRepository.findOne(applicationId);
        final Appointment target = new Appointment();
        target.setAppointment(request.getAppointment());
        target.setStatus(request.getStatus());
        target.setVisitUser(userRepository.findOne(userId));
        application.setAppointment(target);
        applicationRepository.save(application);
        return request;
    }

    @Override
    public Appointment updateAppointment(final String userId, final String appointmentId, final String applicationId, final Appointment request) {
        final Appointment target = appointmentRepository.findOne(appointmentId);
        target.setUpdatedAt(new Date());
        target.setAppointment(request.getAppointment());
        target.setStatus(request.getStatus());
        target.setVisitUser(userRepository.findOne(userId));

        final Application application = applicationRepository.findOne(applicationId);
        if (AppointmentStatus.APPROVED.equals(request.getStatus())) {
            application.setStatus(ApplicationStatus.DRAFT);
            application.setStep(ApplicationStep.DOCUMENTATION);
        } else if (AppointmentStatus.REJECTED.equals(request.getStatus())) {
            application.setStatus(ApplicationStatus.REJECTED);
        }
        target.setUpdatedAt(new Date());
        applicationRepository.save(application);
        return appointmentRepository.save(target);
    }

    @Override
    public Training updateTrainingDocument(final String userId, final String trainingId, final DocumentType documentType, final Document request) {
        final Training training = trainingRepository.findOne(trainingId);
        Document document = null;
        if (DocumentType.SAFEGUARDING_TRAINING.equals(documentType)) {
            document = training.getSafeguardingTraining();
        } else if (DocumentType.CLEANING_TRAINING.equals(documentType)) {
            document = training.getCleaningTraining();
        } else if (DocumentType.FOOD_TRAINING.equals(documentType)) {
            document = training.getFoodTraining();
        } else if (DocumentType.MENDAL_CAPACITY.equals(documentType)) {
            document = training.getMendalCapacity();
        }
        if (document == null) {
            document = documentService.createEmptyDocument();
        }
        if (DocumentType.SAFEGUARDING_TRAINING.equals(documentType)) {
            training.setSafeguardingTraining(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getTrainingDocumentBucket()));
        } else if (DocumentType.CLEANING_TRAINING.equals(documentType)) {
            training.setCleaningTraining(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getTrainingDocumentBucket()));
        } else if (DocumentType.FOOD_TRAINING.equals(documentType)) {
            training.setFoodTraining(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getTrainingDocumentBucket()));
        } else if (DocumentType.MENDAL_CAPACITY.equals(documentType)) {
            training.setMendalCapacity(documentService.updateDocument(request.getBase64data(), document, request.getFileName(), storageProperties.getTrainingDocumentBucket()));
        }
        training.setUpdatedAt(new Date());
        return trainingRepository.save(training);
    }

    @Override
    public Application updateApplication(final String userId, final String applicationId, final Application request) {
        final Application target = applicationRepository.findOne(applicationId);
        final ApplicationStatus previous = target.getStatus();

        target.setStep(request.getStep());
        target.setStatus(request.getStatus());
        target.setUpdatedAt(new Date());
        final Application application = applicationRepository.save(target);

        if (!previous.equals(target.getStatus())) {
            emailAdapter.sendEmail(emailProperties.getSubjectApplicationStatus(), templateService.generate(Template.APPLICATION_CHANGE_STATUS, new HashMap<>()), application.getUser().getEmail());
        }
        return application;
    }
}
