package com.insigma.validator;

import com.insigma.validator.impl.IdcardValidator;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = IdcardValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
@Pattern(regexp = "")
public @interface Idcard {

    String message() default "身份证号不符合格式";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @OverridesAttribute(constraint = Pattern.class, name = "regexp") String regexp() default ".*";

    @OverridesAttribute(constraint = Pattern.class, name = "flags") Pattern.Flag[] flags() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Idcard[] value();
    }
}
