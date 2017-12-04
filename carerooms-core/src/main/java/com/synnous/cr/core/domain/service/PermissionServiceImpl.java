package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.entity.Application;
import com.synnous.cr.core.domain.entity.Property;
import com.synnous.cr.core.domain.entity.PropertyPerson;
import com.synnous.cr.core.domain.entity.Training;
import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.enumeration.ApplicationStatus;
import com.synnous.cr.core.domain.enumeration.ApplicationStep;
import com.synnous.cr.core.domain.enumeration.UserType;
import com.synnous.cr.core.domain.repository.ApplicationRepository;
import com.synnous.cr.core.domain.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The permission service.
 *
 * @author : Manos Papantonakos on 25/1/2016.
 */
@Transactional
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    private User getUser(final Authentication authentication) {
        if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return userService.loadUser(principal.getUsername());
        } else {
            return userService.loadUser((String) authentication.getPrincipal());
        }
    }

    @Override
    public boolean hasUserType(final Authentication authentication, final UserType userType) {
        final User user = getUser(authentication);
        return user.getUserType().equals(userType);
    }

    @Override
    public boolean canUpdateProperty(final Authentication authentication, final String propertyId) {
        final User user = getUser(authentication);
        final Property property = propertyService.getProperty(propertyId);
        return property != null && user.equals(property.getUser());
    }

    @Override
    public boolean canUpdatePropertyPerson(final Authentication authentication, final String personId) {
        final User user = getUser(authentication);
        final Property property = propertyService.getProperty(user.getId());
        if (property != null) {
            for (final PropertyPerson person : property.getPersons()) {
                if (person.getId().equals(personId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean catUpdateApplication(final Authentication authentication, final String propertyId) {
        final User user = getUser(authentication);
        final Property property = propertyService.getProperty(user.getId());
        final Application application = applicationRepository.findByProperty(property);
        return application != null && application.getStatus().equals(ApplicationStatus.DRAFT) && (
                application.getStep().equals(ApplicationStep.DOCUMENTATION)
                        || application.getStep().equals(ApplicationStep.ONLINE_TRAINING)
        );
    }

    @Override
    public boolean canUpdateTraining(final Authentication authentication, final String trainingId) {
        final User user = getUser(authentication);
        final Training training = trainingRepository.findOne(trainingId);
        return training != null && training.getUser().equals(user);
    }

    @Override
    public boolean catUpdateApplicationByTraining(final Authentication authentication, final String trainingId) {
        final User user = getUser(authentication);
        final Training training = trainingRepository.findOne(trainingId);
        final Application application = applicationRepository.findByTraining(training);
        return application != null && application.getStatus().equals(ApplicationStatus.DRAFT) && (
                application.getStep().equals(ApplicationStep.DOCUMENTATION)
                        || application.getStep().equals(ApplicationStep.ONLINE_TRAINING)
        );
    }
}