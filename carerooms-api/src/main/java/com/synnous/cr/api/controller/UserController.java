package com.synnous.cr.api.controller;

import com.synnous.cr.api.assembler.UserAssembler;
import com.synnous.cr.api.controller.parent.V1Controller;
import com.synnous.cr.api.resource.UserResource;
import com.synnous.cr.core.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The user controller.
 *
 * @author : Manos Papantonakos.
 */
@Transactional
@RestController
@Api(value = "user", description = "Operations about users")
public class UserController extends V1Controller {

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The user resource assembler. */
    @Autowired
    private UserAssembler userAssembler;

    /**
     * Return the user.
     *
     * @param userId
     *         the userId
     * @return the user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @ApiOperation(value = "Returns user", notes = "Returns the user")
    public UserResource getUser(final @AuthenticationPrincipal String userId) {
        return userAssembler.toResource(userService.loadUser(userId));
    }

    /**
     * Updates the user.
     *
     * @param userId
     *         the userId
     * @param userResource
     *         the resources
     * @return the user
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    @ApiOperation(value = "Update user", notes = "Updates the user")
    public UserResource updateUser(final @AuthenticationPrincipal String userId, final @Valid @RequestBody UserResource userResource) {
        return userAssembler.toResource(userService.updateUser(userId, userAssembler.toDomain(userResource), userId));
    }
}
