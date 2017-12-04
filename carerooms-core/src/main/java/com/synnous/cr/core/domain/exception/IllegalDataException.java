package com.synnous.cr.core.domain.exception;

/**
 * The illegal data exception.
 *
 * @author : Manos Papantonakos.
 */
public class IllegalDataException extends RuntimeException {
    public enum Reason {
        DBS_EXPIRATION_EMPTY("error.property.person.dbsExpiration.empty"),
        DBS_EXPIRATION_IN_PAST("error.property.person.dbsExpiration.inPast"),
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
    public IllegalDataException(final Reason reason) {
        super(reason.name());
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
