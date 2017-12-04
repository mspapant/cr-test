package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.AppointmentResource;
import com.synnous.cr.core.domain.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The appointment assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class AppointmentAssembler implements ResourceAssembler<Appointment, AppointmentResource>, DomainAssembler<Appointment, AppointmentResource> {

    @Autowired
    private UserMiniAssembler userMiniAssembler;

    @Override
    public AppointmentResource toResource(final Appointment domain) {
        if (domain == null) {
            return null;
        }
        final AppointmentResource target = new AppointmentResource();
        target.setId(domain.getId());
        target.setAppointment(domain.getAppointment());
        target.setStatus(domain.getStatus());
        target.setVisitUser(userMiniAssembler.toResource(domain.getVisitUser()));
        target.setCreatedAt(domain.getCreatedAt());
        target.setUpdatedAt(domain.getUpdatedAt());
        return target;
    }

    @Override
    public Appointment toDomain(final AppointmentResource resource) {
        final Appointment target = new Appointment();
        target.setId(resource.getId());
        target.setAppointment(resource.getAppointment());
        target.setStatus(resource.getStatus());
        return target;
    }
}
