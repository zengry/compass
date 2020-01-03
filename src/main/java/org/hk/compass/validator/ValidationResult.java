package org.hk.compass.validator;

import lombok.Data;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */
@Data
public class ValidationResult {
    private boolean hasErrors = true;
    private String  errorMeg;
}
