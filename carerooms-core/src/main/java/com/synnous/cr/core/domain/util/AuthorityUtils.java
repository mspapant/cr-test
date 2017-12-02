package com.synnous.cr.core.domain.util;

import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.entity.Authority;

import java.util.LinkedList;
import java.util.List;

/**
 * The authority utilities.
 *
 * @author : Manos Papantonakos.
 */
public class AuthorityUtils {

    /**
     * Returns the user authorities.
     *
     * @param user
     *         the user
     * @return the authorities
     */
    public static List<String> getAuthorityNames(final User user) {
        final List<String> names = new LinkedList<>();
        for (Authority authority : user.getAuthorityList()) {
            names.add(authority.getAuthorityType().name());
        }
        return names;
    }
}
