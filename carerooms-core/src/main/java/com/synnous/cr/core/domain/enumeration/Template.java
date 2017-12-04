package com.synnous.cr.core.domain.enumeration;

/**
 * The template enumeration.
 *
 * @author : Manos Papantonakos;
 */
public enum Template {
    APPLICATION_CHANGE_STATUS("application/changeStatus"),
    ;
    public String value;

    Template(final String v) {
        value = v;
    }
}
