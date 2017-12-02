package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.UserMiniResource;
import com.synnous.cr.core.domain.entity.User;
import org.springframework.stereotype.Component;

/**
 * The user assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class UserMiniAssembler implements ResourceAssembler<User, UserMiniResource>, DomainAssembler<User, UserMiniResource> {

    @Override
    public UserMiniResource toResource(final User domain) {
        if (domain == null) {
            return null;
        }
        final UserMiniResource target = new UserMiniResource();
        target.setId(domain.getId());
        target.setFirstName(domain.getFirstName());
        target.setLastName(domain.getLastName());
        target.setEmail(domain.getEmail());
        target.setStatus(domain.getStatus());
        target.setUserType(domain.getUserType());
        target.setPhone(domain.getPhone());
        if (domain.getAvatar() != null) {
            target.setAvatarURL(domain.getAvatar().getUrl());
        }
        return target;
    }

    @Override
    public User toDomain(final UserMiniResource resource) {
        final User target = new User();
        target.setId(resource.getId());
        target.setFirstName(resource.getFirstName());
        target.setLastName(resource.getLastName());
        target.setEmail(resource.getEmail());
        target.setPhone(resource.getPhone());
        target.setUserType(resource.getUserType());
        target.setAvatarBase64(resource.getAvatarBase64());
        return target;
    }
}
