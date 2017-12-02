package com.synnous.cr.core.domain.enumeration;

/**
 * The access level enumeration.
 *
 * @author : Manos Papantonakos.
 */
public enum AccessLevel {
    CONTENT(1),
    USER_PATIENT(Permission.NUM_OF_PERMISSIONS + 1),
    USER_HCP(2 * Permission.NUM_OF_PERMISSIONS + 1),
    USER_ADMIN(3 * Permission.NUM_OF_PERMISSIONS + 1),
    ;

    /** The value */
    public final int value;

    AccessLevel(final int value) {
        this.value = value;
    }
}
