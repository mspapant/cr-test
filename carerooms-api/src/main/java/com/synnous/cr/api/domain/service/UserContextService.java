package com.synnous.cr.api.domain.service;

import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The user details service.
 *
 * @author : Manos Papantonakos on 17/1/2017.
 */
@Service
@Transactional
public class UserContextService {

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findOne(authentication.getName());
    }
}
