package com.synnous.cr.core.domain.entity;


import com.synnous.cr.core.domain.entity.root.IdTimestampEntity;
import com.synnous.cr.core.domain.enumeration.ApplicationStep;
import com.synnous.cr.core.domain.enumeration.UserStatus;
import com.synnous.cr.core.domain.enumeration.UserType;
import com.synnous.cr.core.domain.util.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The user entity.
 *
 * @author : Manos Papantonakos on 18/08/2016.
 */
@Entity(name = "USER")
public class User extends IdTimestampEntity implements Serializable, UserDetails {

    static final long serialVersionUID = 1L;

    /** The password. */
    @Column(name = "PASSWORD")
    private String password;

    /** The email. */
    @Column(name = "EMAIL")
    private String email;

    /** The first name. */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /** The last name. */
    @Column(name = "LAST_NAME")
    private String lastName;

    /** The phone. */
    @Column(name = "PHONE")
    private String phone;

    /** The user type. */
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;

    /** The enabled. */
    @Column(name = "ENABLED")
    private Boolean enabled = true;

    /** The locked. */
    @Column(name = "LOCKED")
    private Boolean locked = false;

    /** The user token. */
    @Column(name = "USER_TOKEN")
    private String userToken;

    /** The status. */
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /** The avatar. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AVATAR_ID")
    private Avatar avatar;

    /** The avatar base 64. */
    @Transient
    private String avatarBase64;

    /** The authorityList. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Authority> authorityList;

    /** The applications. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Application> applications;

    /** The properties. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Property> properties;

    public User() {
    }

    public User(final String id) {
        setId(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<String> names = AuthorityUtils.getAuthorityNames(this);
        return org.springframework.security.core.authority.AuthorityUtils.createAuthorityList(names.toArray(new String[names.size()]));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(final Boolean locked) {
        this.locked = locked;
    }

    public Set<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(final Set<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(final String userToken) {
        this.userToken = userToken;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(final UserStatus status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(final UserType userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(final Avatar avatar) {
        this.avatar = avatar;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(final String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(final Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(final Set<Property> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;
        return !(id != null ? !id.equals(user.id) : user.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", userToken='" + userToken + '\'' +
                ", status=" + status +
                ", avatar=" + avatar +
                ", avatarBase64='" + avatarBase64 + '\'' +
                ", authorityList=" + authorityList +
                ", properties=" + properties +
                "} " + super.toString();
    }
}
