package com.synnous.cr.api.controller;

import com.synnous.cr.api.assembler.DBSCertificationAssembler;
import com.synnous.cr.api.assembler.DocumentAssembler;
import com.synnous.cr.api.assembler.PropertyAssembler;
import com.synnous.cr.api.controller.parent.V1Controller;
import com.synnous.cr.api.resource.DBSCertificationResource;
import com.synnous.cr.api.resource.DocumentResource;
import com.synnous.cr.api.resource.PropertyResource;
import com.synnous.cr.core.domain.enumeration.DocumentType;
import com.synnous.cr.core.domain.service.PropertyService;
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
@Api(value = "property", description = "Operations about property")
public class PropertyController extends V1Controller {

    /** The property service. */
    @Autowired
    private PropertyService propertyService;

    /** The property assembler. */
    @Autowired
    private PropertyAssembler propertyAssembler;

    /** The document assembler. */
    @Autowired
    private DocumentAssembler documentAssembler;

    /** The document assembler. */
    @Autowired
    private DBSCertificationAssembler dbsCertificationAssembler;

    /**
     * Returns the property.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    @RequestMapping(method = RequestMethod.GET, value = "/property")
    @ApiOperation(value = "Returns property", notes = "Returns the property")
    public PropertyResource getProperties(final @AuthenticationPrincipal String userId) {
        return propertyAssembler.toResource(propertyService.getProperty(userId));
    }

    /**
     * Updates the property.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/property/{propertyId}")
    @ApiOperation(value = "Updates property", notes = "Updates the property")
    @PreAuthorize("@permissionService.canUpdateProperty(authentication, #propertyId) && @permissionService.catUpdateApplication(authentication, #propertyId)")
    public PropertyResource updateProperty(final @AuthenticationPrincipal String userId, final @PathVariable String propertyId, final @RequestBody PropertyResource propertyResource) {
        return propertyAssembler.toResource(propertyService.updateProperty(userId, propertyId, propertyAssembler.toDomain(propertyResource)));
    }

    /**
     * Updates the property document.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/property/{propertyId}/document")
    @ApiOperation(value = "Updates property document", notes = "Updates the property document")
    @PreAuthorize("@permissionService.canUpdateProperty(authentication, #propertyId) && @permissionService.catUpdateApplication(authentication, #propertyId)")
    public PropertyResource updatePropertyDocument(final @AuthenticationPrincipal String userId, final @PathVariable String propertyId, final @RequestParam DocumentType documentType, final @RequestBody DocumentResource documentResource) {
        return propertyAssembler.toResource(propertyService.updatePropertyDocument(userId, propertyId, documentType, documentAssembler.toDomain(documentResource)));
    }

    /**
     * Updates the property document.
     *
     * @param userId
     *         the user id
     * @return the property
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/property/{propertyId}/personDBSCertification/{personId}")
    @ApiOperation(value = "Updates the property person dbs certification", notes = "Updates the property person dbs certification")
    @PreAuthorize("@permissionService.canUpdateProperty(authentication, #propertyId) && @permissionService.canUpdatePropertyPerson(authentication, #personId) && @permissionService.catUpdateApplication(authentication, #propertyId)")
    public DBSCertificationResource updatePropertyPersonDBSCertification(final @AuthenticationPrincipal String userId, final @PathVariable String propertyId, final @PathVariable String personId, final @RequestBody DBSCertificationResource dbsCertification) {
        return dbsCertificationAssembler.toResource(propertyService.updatePropertyPersonDBSCertification(userId, propertyId, personId, dbsCertificationAssembler.toDomain(dbsCertification)));
    }
}
