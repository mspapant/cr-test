package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.entity.Appointment;
import com.synnous.cr.core.domain.entity.User;

import java.util.List;

/**
 * The user service.
 *
 * @author : Manos Papantonakos.
 */
public interface UserService {

    /**
     * Authenticates the user.
     *
     * @param username
     *         the username
     * @param password
     *         the password
     * @return the user
     */
    User authenticateUser(final String username, final String password);

    /**
     * Authenticates by user token.
     *
     * @param token
     *         the token
     * @return the user
     */
    User authenticateByUserToken(final String token);

    /**
     * Loads the user.
     *
     * @param id
     *         the id
     * @return the user
     */
    User loadUser(final String id);

    /**
     * Updates the user avatar.
     *
     * @param userId
     *         the user id
     * @param base64Data
     *         the base 64 data
     * @return the user
     */
    User updateAvatar(final String userId, final String base64Data);

    /**
     * Updates the user.
     *
     * @param authUserId
     *         the user id
     * @param user
     *         the user
     * @param userId
     *         the user
     * @return the user
     */
    User updateUser(final String authUserId, final User user, final String userId);
}
