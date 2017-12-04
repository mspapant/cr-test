package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.enumeration.Template;

import java.util.Map;

/**
 * The template service.
 *
 * @author : Manos Papantonakos.
 */
public interface TemplateService {

    /**
     * Generate the report from template and data provided.
     *
     * @param template
     *         the template
     * @param data
     *         the data
     * @return the report
     */
    String generate(final Template template, final Map<String, Object> data);
}
