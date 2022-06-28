package com.l3azh.bonsai.ValidCustomAnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidRoleImpl.class)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
@NotNull
public @interface ValidRole {
    Class<? extends Enum<?>> bonsaiEnumClass();

    String message() default "Value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
