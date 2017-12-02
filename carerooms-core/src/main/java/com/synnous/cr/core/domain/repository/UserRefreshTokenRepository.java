package com.synnous.cr.core.domain.repository;

import com.synnous.cr.core.domain.entity.UserRefreshToken;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRefreshTokenRepository extends PagingAndSortingRepository<UserRefreshToken, Long> {

    /**
     * Finds by token and user id.
     *
     * @param token
     *         the token
     * @param userId
     *         the user id
     * @return the refresh token
     */
    UserRefreshToken findByTokenAndUserId(final String token, final String userId);
}
