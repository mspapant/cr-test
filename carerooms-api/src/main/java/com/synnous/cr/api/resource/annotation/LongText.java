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
@Size(max = 4096)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface LongText {

    String message() default "{validation.text.short}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
