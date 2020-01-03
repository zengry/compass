package org.hk.compass.validator;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.HibernateValidator;
import org.hk.compass.exception.CommonException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author zengry
 * @description  参数校验
 * @since 2020/1/2
 */
public class ValidationUtils {
    private static Validator validator =
             Validation.byProvider(HibernateValidator.class)
                        .configure()
                        .failFast(true)
                        .buildValidatorFactory().getValidator();

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws CommonException  校验不通过，则报RRException异常
     */
    public static void validateEntityGroup(Object object, Class<?>... groups)
        throws CommonException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new CommonException(msg.toString());
        }
    }



    public static <T> ValidationResult validateEntity(T obj){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        return dealValidateResult(set);
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName){

        Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
        return dealValidateResult(set);
    }

    public static <T> ValidationResult dealValidateResult(Set<ConstraintViolation<T>> set){
        ValidationResult result = new ValidationResult();
        if(!CollectionUtils.isEmpty(set)){
            result.setHasErrors(true);
            for(ConstraintViolation<T> cv : set){
                result.setErrorMeg(cv.getMessage());
                return result;
            }
        }
        result.setHasErrors(false);
        return result;
    }

    public static <T> void dealValidateException(T obj){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        dealValidateException(set);
    }

    public static <T> void dealValidateException(T obj, String propertyName){
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
        dealValidateException(set);
    }

    public static <T> void dealValidateException(Set<ConstraintViolation<T>> set){
        if(!CollectionUtils.isEmpty(set)){
            for(ConstraintViolation<T> cv : set){
                throw new CommonException(99999, cv.getMessage());
            }
        }
    }
}


