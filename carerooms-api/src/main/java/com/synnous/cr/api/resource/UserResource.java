package com.synnous.cr.api.resource;

import com.synnous.cr.core.domain.enumeration.UserStatus;
import com.synnous.cr.core.domain.enumeration.UserType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class UserResource {

    /** The id. */
    private String id;

    /** The email. */
    @Email
    private String email;

    /** The first name. */
    @NotBlank
    private String firstName;

    /** The last name. */
    @NotBlank
    private String lastName;

    /** The user type. */
    private UserType userType;

    /** The user status. */
    private UserStatus userStatus;

    /** The updated at. */
    private Long updatedAt;

    /** The created at. */
    private Long createdAt;

    /** The enabled. */
    private Boolean enabled;

    /** The phone. */
    private String phone;

    /** The avatar URL. */
    private String avatarURL;

    /** The avatar base 64. */
    private String avatarBase64;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Long createdAt) {
        this.createdAt = createdAt;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(final UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(final String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(final String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    @Override
    public String toString() {
        return "UserResource{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", enabled=" + enabled +
                ", phone='" + phone + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", avatarBase64='" + avatarBase64 + '\'' +
                '}';
    }
}
