package org.softuni.mobilele.util;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    private final Validator validator;

    @Autowired
    public ValidationUtil(Validator validator) {
        this.validator = validator;
    }

    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }

}
