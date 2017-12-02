package com.synnous.cr.api.resource.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The valid password annotation.
 *
 * @author : Manos Papantonakos on 22/2/2015.
 */
@Constraint(validatedBy = {})
@Size(min = 8, max = 16)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ValidPassword {

    String message() default "{validation.password.empty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
