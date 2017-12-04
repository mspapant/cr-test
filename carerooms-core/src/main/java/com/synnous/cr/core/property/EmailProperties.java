package com.synnous.cr.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Properties
 *
 * @author mspapant
 */
@Validated
@ConfigurationProperties("app.email")
public class EmailProperties {

    private String subjectApplicationStatus;

    private String sender;

    public String getSubjectApplicationStatus() {
        return subjectApplicationStatus;
    }

    public void setSubjectApplicationStatus(final String subjectApplicationStatus) {
        this.subjectApplicationStatus = subjectApplicationStatus;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }
}
