package com.synnous.cr.core.domain.adapter;

/**
 * The email adapter.
 *
 * @author : Manos Papantonakos.
 */
public interface EmailAdapter {

    /**
     * Sends the email.
     *
     * @param recipient
     *         the recipient
     * @param subject
     *         the subject
     * @param body
     *         the body
     */
    void sendEmail(final String subject, final String body, final String recipient);
}
