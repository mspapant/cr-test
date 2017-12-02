package com.synnous.cr.api.assembler;

import com.synnous.cr.api.assembler.root.DomainAssembler;
import com.synnous.cr.api.assembler.root.ResourceAssembler;
import com.synnous.cr.api.resource.UserResource;
import com.synnous.cr.core.domain.entity.User;
import org.springframework.stereotype.Component;

/**
 * The user assembler.
 *
 * @author : Manos Papantonakos.
 */
@Component
public class UserAssembler implements ResourceAssembler<User, UserResource>, DomainAssembler<User, UserResource> {

    @Override
    public UserResource toResource(final User domain) {
        if (domain == null) {
            return null;
        }
        final UserResource target = new UserResource();
        target.setId(domain.getId());
        target.setEmail(domain.getEmail());
        target.setFirstName(domain.getFirstName());
        target.setLastName(domain.getLastName());
        target.setEnabled(domain.getEnabled());
        target.setUserStatus(domain.getStatus());
        target.setPhone(domain.getPhone());
        target.setCreatedAt(domain.getCreatedAt().getTime());
        target.setUpdatedAt(domain.getUpdatedAt().getTime());
        target.setUserType(domain.getUserType());
        if (domain.getAvatar() != null) {
            target.setAvatarURL(domain.getAvatar().getUrl());
        }
        return target;
    }

    @Override
    public User toDomain(final UserResource resource) {
        final User target = new User();
        target.setId(resource.getId());
        target.setFirstName(resource.getFirstName());
        target.setLastName(resource.getLastName());
        target.setEmail(resource.getEmail());
        target.setEnabled(resource.getEnabled());
        target.setPhone(resource.getPhone());
        target.setUserType(resource.getUserType());
        target.setAvatarBase64(resource.getAvatarBase64());
        return target;
    }
}
