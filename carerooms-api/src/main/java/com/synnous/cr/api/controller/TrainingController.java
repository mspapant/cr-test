package com.synnous.cr.api.controller;

import com.synnous.cr.api.assembler.DocumentAssembler;
import com.synnous.cr.api.assembler.TrainingAssembler;
import com.synnous.cr.api.controller.parent.V1Controller;
import com.synnous.cr.api.resource.DocumentResource;
import com.synnous.cr.api.resource.TrainingResource;
import com.synnous.cr.core.domain.enumeration.DocumentType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user controller.
 *
 * @author : Manos Papantonakos.
 */
@Transactional
@RestController
@Api(value = "training", description = "Operations about training")
public class TrainingController extends V1Controller {

    /** The application service. */
    @Autowired
    private ApplicationService applicationService;

    /** The training assembler. */
    @Autowired
    private TrainingAssembler trainingAssembler;

    /** The document assembler. */
    @Autowired
    private DocumentAssembler documentAssembler;

    /**
     * Updates the property document.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/training/{trainingId}/document")
    @ApiOperation(value = "Updates training document", notes = "Updates the training document")
    @PreAuthorize("@permissionService.canUpdateTraining(authentication, #trainingId) && @permissionService.catUpdateApplicationByTraining(authentication, #trainingId) ")
    public TrainingResource updateTrainingDocument(final @AuthenticationPrincipal String userId, final @PathVariable String trainingId, final @RequestParam DocumentType documentType, final @RequestBody DocumentResource documentResource) {
        return trainingAssembler.toResource(applicationService.updateTrainingDocument(userId, trainingId, documentType, documentAssembler.toDomain(documentResource)));
    }
}
