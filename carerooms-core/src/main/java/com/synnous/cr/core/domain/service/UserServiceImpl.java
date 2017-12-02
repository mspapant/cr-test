package com.synnous.cr.core.domain.service;

import com.synnous.cr.core.domain.exception.AuthenticationException;
import com.synnous.cr.core.domain.repository.UserRepository;
import com.synnous.cr.core.domain.adapter.FileAdapter;
import com.synnous.cr.core.domain.entity.Avatar;
import com.synnous.cr.core.domain.entity.User;
import com.synnous.cr.core.domain.enumeration.UserStatus;
import com.synnous.cr.core.property.StorageProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * The user service.
 *
 * @author : Manos Papantonakos.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /** The password encoder */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** The file adapter. */
    @Autowired
    private FileAdapter fileAdapter;

    /** The storage properties. */
    @Autowired
    private StorageProperties storageProperties;

    @Override
    public User authenticateUser(final String username, final String password) {
        final User user = userRepository.findByEmailIgnoreCaseAndEnabled(username, true);
        checkUser(user, password);
        return user;
    }

    @Override
    public User authenticateByUserToken(final String token) {
        final User user = userRepository.findByUserToken(token);
        user.setUserToken(null);
        userRepository.save(user);
        return user;
    }

    @Override
    public User loadUser(final String id) {
        return userRepository.findOne(id);
    }


    /**
     * Checks the user.
     *
     * @param user
     *         the user
     * @param password
     *         the password
     */
    private void checkUser(final User user, final String password) {
        if (user == null) {
            throw new AuthenticationException(AuthenticationException.Reason.USER_NOT_FOUND);
        }
        if (password == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException(AuthenticationException.Reason.PASSWORD_NO_MATCH);
        }
        if (user.getLocked()) {
            throw new AuthenticationException(AuthenticationException.Reason.USER_LOCKED);
        }
        if (!user.getEnabled()) {
            throw new AuthenticationException(AuthenticationException.Reason.USER_NOT_ACTIVE);
        }
        if (UserStatus.SIGNUP.equals(user.getStatus())) {
            throw new AuthenticationException(AuthenticationException.Reason.USER_PENDING);
        }
    }

    @Override
    public User updateUser(final String authUserId, final User source, final String userId) {
        final User user = loadUser(userId);
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setUpdatedAt(new Date());
        user.setUserType(source.getUserType());
        user.setEmail(source.getEmail());
        if (!StringUtils.isEmpty(source.getAvatarBase64())) {
            updateAvatar(userId, source.getAvatarBase64());
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateAvatar(final String userId, final String base64Data) {
        final User user = loadUser(userId);
        Avatar avatar;
        if (user.getAvatar() == null) {
            avatar = new Avatar();
        } else {
            avatar = user.getAvatar();
        }
        avatar.setUpdatedAt(new Date());

        final String filename = userId + ".png";
        final String url = fileAdapter.uploadFile(storageProperties.getAvatarBucket(), filename, Base64.decodeBase64(base64Data), FileAdapter.ContentType.IMAGE.id);
        avatar.setUrl(url + "?t=" + new Date().getTime());
        avatar.setObjectKey(filename);

        user.setAvatar(avatar);
        userRepository.save(user);
        return user;
    }
}
