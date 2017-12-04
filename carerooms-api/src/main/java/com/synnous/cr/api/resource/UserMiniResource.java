package com.synnous.cr.api.resource;

import com.synnous.cr.core.domain.enumeration.ApplicationStep;
import com.synnous.cr.core.domain.enumeration.UserStatus;
import com.synnous.cr.core.domain.enumeration.UserType;

/**
 * The user resource
 *
 * @author : Manos Papantonakos.
 */
public class UserMiniResource {

    /** The id. */
    private String id;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The email. */
    private String email;

    /** The status. */
    private UserStatus status;

    /** The phone. */
    private String phone;

    /** The user type. */
    private UserType userType;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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
        return "UserMiniResource{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", avatarURL='" + avatarURL + '\'' +
                ", avatarBase64='" + avatarBase64 + '\'' +
                '}';
    }
}
