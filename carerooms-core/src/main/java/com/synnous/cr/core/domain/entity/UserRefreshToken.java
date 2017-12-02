package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "OAUTH_REFRESH_TOKEN")
public class UserRefreshToken extends IdTimestampEntity {
    private static final long serialVersionUID = 1L;

    /** The jti. */
    @Column(name = "TOKEN")
    private String token;

    /** The user id. */
    @Column(name = "USER_ID")
    private String userId;

    /** The expiration date. */
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    public UserRefreshToken() {
    }

    public UserRefreshToken(final String token, final String userId, final Date expirationDate) {
        this.token = token;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
