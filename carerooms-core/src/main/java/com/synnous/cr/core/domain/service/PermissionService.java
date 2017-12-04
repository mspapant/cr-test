package com.synnous.cr.core.domain.service;


import com.synnous.cr.core.domain.enumeration.ApplicationStatus;
import com.synnous.cr.core.domain.enumeration.UserType;
import org.springframework.security.core.Authentication;

/**
 * The permission service.
 *
 * @author : Manos Papantonakos on 25/1/2016.
 */
public interface PermissionService {

    /**
     * Returns if the user has permission.
     *
     * @param authentication
     *         the authentication
     * @param userType
     *         the user type
     * @return the flag
     */
    boolean hasUserType(final Authentication authentication, final UserType userType);

    /**
     * Returns if the user can update the property.
     *
     * @param authentication
     *         the authentication
     * @param propertyId
     *         the property id
     * @return the flag
     */
    boolean canUpdateProperty(final Authentication authentication, final String propertyId);

    /**
     * Returns if the user can update the property.
     *
     * @param authentication
     *         the authentication
     * @param personId
     *         the person id
     * @return the flag
     */
    boolean canUpdatePropertyPerson(final Authentication authentication, final String personId);

    /**
     * Returns if the user can update the property.
     *
     * @param authentication
     *         the authentication
     * @param propertyId
     *         the property id
     * @return the flag
     */
    boolean catUpdateApplication(final Authentication authentication, final String propertyId);

    /**
     * Returns if the user can update the property.
     *
     * @param authentication
     *         the authentication
     * @param trainingId
     *         the training id
     * @return the flag
     */
    boolean canUpdateTraining(final Authentication authentication, final String trainingId);

    /**
     * Returns if the user can update the property.
     *
     * @param authentication
     *         the authentication
     * @param trainingId
     *         the training id
     * @return the flag
     */
    boolean catUpdateApplicationByTraining(final Authentication authentication, final String trainingId);

}
