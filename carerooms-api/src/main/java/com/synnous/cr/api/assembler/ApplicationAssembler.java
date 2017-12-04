package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.ApplicationResource;
import com.synnous.cr.core.domain.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Application assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class ApplicationAssembler implements ResourceAssembler<Application, ApplicationResource>, DomainAssembler<Application, ApplicationResource> {

    @Autowired
    private UserMiniAssembler userMiniAssembler;

    @Autowired
    private AppointmentAssembler appointmentAssembler;

    @Autowired
    private PropertyAssembler propertyAssembler;

    @Autowired
    private TrainingAssembler trainingAssembler;

    @Override
    public ApplicationResource toResource(final Application domain) {
        if (domain == null) {
            return null;
        }
        final ApplicationResource target = new ApplicationResource();
        target.setId(domain.getId());
        target.setStep(domain.getStep());
        target.setStatus(domain.getStatus());
        target.setUser(userMiniAssembler.toResource(domain.getUser()));
        target.setAppointment(appointmentAssembler.toResource(domain.getAppointment()));
        target.setProperty(propertyAssembler.toResource(domain.getProperty()));
        target.setTraining(trainingAssembler.toResource(domain.getTraining()));
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        return target;
    }

    @Override
    public Application toDomain(final ApplicationResource resource) {
        final Application target = new Application();
        target.setId(resource.getId());
        target.setStep(resource.getStep());
        target.setStatus(resource.getStatus());
        target.setAppointment(appointmentAssembler.toDomain(resource.getAppointment()));
        return target;
    }
}
