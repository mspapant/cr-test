package com.synnous.cr.core.domain.adapter;

import com.synnous.cr.core.property.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * The email adapter.
 *
 * @author : Manos Papantonakos on 5/2/2016.
 */
@Service
public class EmailSimpleAdapter implements EmailAdapter {

    @Autowired
    private EmailProperties emailProperties;

    /** The java mail sender. */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(final String subject, final String body, final String recipient) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipient);
            helper.setFrom(emailProperties.getSender());
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            helper.setText(body, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
