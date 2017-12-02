package com.synnous.cr.core.domain.enumeration;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * The authority enumeration
 *
 * @author : Manos Papantonakos
 */
public enum AuthorityEnum {

    _CONTENT_ALL(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).add(Permission.APPROVE.value).shiftLeft(AccessLevel.CONTENT.value)
    ),
    _CONTENT_ALL_NO_APPROVE(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).shiftLeft(AccessLevel.CONTENT.value)
    ),
    _CONTENT_ALL_NO_SHARE(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.APPROVE.value).shiftLeft(AccessLevel.CONTENT.value)
    ),
    _CONTENT_CRUD(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).shiftLeft(AccessLevel.CONTENT.value)
    ),

    _USER_PATIENT_ALL(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).add(Permission.APPROVE.value).shiftLeft(AccessLevel.USER_PATIENT.value)
    ),
    _USER_HPC_ALL(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).add(Permission.APPROVE.value).shiftLeft(AccessLevel.USER_HCP.value)
    ),
    _USER_HPC_ALL_INVITE(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).add(Permission.APPROVE.value).add(Permission.INVITE.value).shiftLeft(AccessLevel.USER_HCP.value)
    ),
    _USER_ADMIN_ALL(
            (Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value)).add(Permission.DELETE.value).add(Permission.SHARE.value).add(Permission.APPROVE.value).shiftLeft(AccessLevel.USER_ADMIN.value)
    ),

    PRACTICTIONER("MEMBER",
            _CONTENT_ALL_NO_APPROVE.value
                    .add((Permission.VIEW.value.add(Permission.CREATE.value).add(Permission.UPDATE.value).add(Permission.MESSAGE.value)).shiftLeft(AccessLevel.USER_PATIENT.value))
                    .add((Permission.VIEW.value).shiftLeft(AccessLevel.USER_HCP.value))
    ),
    PRACTICE_ADMIN("PRACTICE ADMIN",
            _CONTENT_ALL.value
                    .add(_USER_HPC_ALL_INVITE.value)
                    .add(_USER_HPC_ALL.value)
    ),
    REGIONAL_ADMIN("REGIONAL ADMIN",
            _CONTENT_ALL.value
    ),
    NATIONAL_ADMIN("NATIONAL ADMIN",
            _CONTENT_ALL_NO_SHARE.value
    ),
    SCS_ADMIN("SCS ADMIN",
            _CONTENT_ALL.value
                    .add(_USER_PATIENT_ALL.value)
                    .add(_USER_HPC_ALL.value)
                    .add(_USER_ADMIN_ALL.value)
    ),
    PATIENT("PATIENT",
            (Permission.VIEW.value).shiftLeft(AccessLevel.CONTENT.value)
    ),
    ;
    /** The value. */
    public final BigInteger value;

    /** The title. */
    public String title;

    AuthorityEnum(final BigInteger value) {
        this.value = value;
        this.title = title;
    }

    AuthorityEnum(final String title, final BigInteger value) {
        this.value = value;
        this.title = title;
    }

    public static AuthorityEnum[] getAuthorities() {
        final List<AuthorityEnum> list = new LinkedList<>();
        for (AuthorityEnum item : AuthorityEnum.values()) {
            if (!item.name().startsWith("_")) {
                list.add(item);
            }
        }
        return list.toArray(new AuthorityEnum[list.size()]);
    }
}
