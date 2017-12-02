package com.synnous.cr.core.domain.enumeration;

import java.math.BigInteger;

/**
 * The permission enumeration.
 *
 * @author : Manos Papantonakos
 */
public enum Permission {

    VIEW(BigInteger.valueOf(1L << 1L)),
    CREATE(BigInteger.valueOf(1L << 2L)),
    UPDATE(BigInteger.valueOf(1L << 3L)),
    DELETE(BigInteger.valueOf(1L << 4L)),
    APPROVE(BigInteger.valueOf(1L << 5L)),
    SHARE(BigInteger.valueOf(1L << 6L)),
    MESSAGE(BigInteger.valueOf(1L << 7L)),
    INVITE(BigInteger.valueOf(1L << 8L)),
    PUBLISH(BigInteger.valueOf(1L << 9L)),
    ;

    public static final int NUM_OF_PERMISSIONS = 9;

    /** The value */
    public final BigInteger value;

    Permission(final BigInteger value) {
        this.value = value;
    }
}
