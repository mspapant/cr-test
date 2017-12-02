package com.synnous.cr.api.controller.parent;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The v1 parent controller.
 *
 * @author : Manos Papantonakos
 */
@RestController
@Api(value = "/v1", description = "Version 1")
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class V1Controller {
}
