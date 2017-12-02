package com.synnous.cr.core.domain.exception;

/**
 * The user authentication exception.
 *
 * @author : Manos Papantonakos.
 */
public class AuthenticationException extends org.springframework.security.core.AuthenticationException {
    public enum Reason {

        USER_NOT_FOUND("error.authorization.reason.userNotFound"),
        PASSWORD_NO_MATCH("error.authorization.reason.userNotFound"),
        USER_LOCKED("error.authorization.reason.locked"),
        USER_NOT_ACTIVE("error.authorization.reason.notActive"),
        USER_PENDING("error.authorization.reason.pending"),
            ;

        public String messageId;

        Reason(final String messageId) {
            this.messageId = messageId;
        }
    }

    /** The reason. */
    private Reason reason;

    /**
     * Creates a new exception with a reason.
     *
     * @param reason
     *         the reason
     */
    public AuthenticationException(final Reason reason) {
        super(reason.name());
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
