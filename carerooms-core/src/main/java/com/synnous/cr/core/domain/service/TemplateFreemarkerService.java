package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.enumeration.Template;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.text.MessageFormat;
import java.util.Map;

/**
 * The template service.
 *
 * @author : Manos Papantonakos.
 */
@Service
public class TemplateFreemarkerService implements TemplateService {

    /** The ERROR constant. */
    private static final String ERROR = "Unable to process the template {0}.";

    /** The freemarker configuration. */
    private Configuration configuration;

    @Autowired
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }

    /** The ftl extension. */
    private static final String EXTENSION = ".ftl";

    /**
     * Returns the path from template.
     *
     * @param template
     *         the template
     * @return the path
     */
    private String getPathFromTemplate(final Template template) {
        return template.value + EXTENSION;
    }

    @Override
    public String generate(final Template template, final Map<String, Object> data) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(getPathFromTemplate(template)), data);
        } catch (final Exception e) {
            throw new RuntimeException(MessageFormat.format(ERROR, template), e);
        }
    }
}
