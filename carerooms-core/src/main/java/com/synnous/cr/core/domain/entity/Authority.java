package com.synnous.cr.core.domain.entity;

import com.synnous.cr.core.domain.entity.root.IdEntity;
import com.synnous.cr.core.domain.enumeration.AuthorityEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The authority for user
 *
 * @author : Manos Papantonakos.
 */
@Entity(name = "AUTHORITY")
public class Authority extends IdEntity implements GrantedAuthority {

    static final long serialVersionUID = 1L;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /** The authorityType. */
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY")
    private AuthorityEnum authorityType;

    @Override
    public String getAuthority() {
        return authorityType.name();
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public AuthorityEnum getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(final AuthorityEnum authorityType) {
        this.authorityType = authorityType;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "user=" + user +
                ", authorityType=" + authorityType +
                "} " + super.toString();
    }
}
