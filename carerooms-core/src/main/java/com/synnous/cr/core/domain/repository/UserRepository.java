package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The user repository.
 *
 * @author : Manos Papantonakos.
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * Finds by email.
     *
     * @param email
     *         the email
     * @return the user
     */
    User findByEmailIgnoreCaseAndEnabled(final String email, final boolean enabled);

    /**
     * Finds the user by user token.
     *
     * @param userToken
     *         the user token
     * @return the user
     */
    User findByUserToken(final String userToken);
}
