package com.synnous.cr.api.controller;

import com.synnous.cr.api.assembler.ApplicationAssembler;
import com.synnous.cr.api.assembler.AppointmentAssembler;
import com.synnous.cr.api.controller.parent.V1Controller;
import com.synnous.cr.api.resource.ApplicationResource;
import com.synnous.cr.api.resource.AppointmentResource;
import com.synnous.cr.core.domain.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The user controller.
 *
 * @author : Manos Papantonakos.
 */
@Transactional
@RestController
@Api(value = "application", description = "Operations about applications")
public class ApplicationController extends V1Controller {

    /** The application service. */
    @Autowired
    private ApplicationService applicationService;

    /** The appointment assembler. */
    @Autowired
    private AppointmentAssembler appointmentAssembler;

    /** The application assembler. */
    @Autowired
    private ApplicationAssembler applicationAssembler;

    /**
     * Return the visits.
     *
     * @param userId
     *         the userId
     * @return the visits
     */
    @RequestMapping(method = RequestMethod.GET, value = "/application")
    @ApiOperation(value = "Returns the applications", notes = "Returns the applications")
    public List<ApplicationResource> getApplications(final @AuthenticationPrincipal String userId) {
        return applicationAssembler.toResourceList(applicationService.getApplications(userId));
    }

    /**
     * Create the appointment.
     *
     * @param userId
     *         the userId
     * @return the appointment
     */
    @PreAuthorize("@permissionService.hasUserType(authentication, 'AREA_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/application/{applicationId}/appointment")
    @ApiOperation(value = "Create the appointment", notes = "Create the appointment")
    public AppointmentResource createAppointment(final @AuthenticationPrincipal String userId, final @PathVariable String applicationId, @RequestBody AppointmentResource resource) {
        return appointmentAssembler.toResource(applicationService.createAppointment(userId, applicationId, appointmentAssembler.toDomain(resource)));
    }

    /**
     * Update the appointment.
     *
     * @param userId
     *         the userId
     * @return the appointment
     */
    @PreAuthorize("@permissionService.hasUserType(authentication, 'AREA_MANAGER')")
    @RequestMapping(method = RequestMethod.PUT, value = "/application/{applicationId}/appointment/{appointmentId}")
    @ApiOperation(value = "Returns the appointment", notes = "Returns the appointment")
    public AppointmentResource updateAppointment(final @AuthenticationPrincipal String userId, @PathVariable String appointmentId, final @PathVariable String applicationId, @RequestBody AppointmentResource resource) {
        return appointmentAssembler.toResource(applicationService.updateAppointment(userId, appointmentId, applicationId, appointmentAssembler.toDomain(resource)));
    }

    /**
     * Update the application.
     *
     * @param userId
     *         the userId
     * @return the appointment
     */
    @PreAuthorize("@permissionService.hasUserType(authentication, 'AREA_MANAGER')")
    @RequestMapping(method = RequestMethod.PUT, value = "/application/{applicationId}")
    @ApiOperation(value = "Updates the application", notes = "Updates the application")
    public ApplicationResource updateApplication(final @AuthenticationPrincipal String userId, final @PathVariable String applicationId, @RequestBody ApplicationResource resource) {
        return applicationAssembler.toResource(applicationService.updateApplication(userId, applicationId, applicationAssembler.toDomain(resource)));
    }
}
