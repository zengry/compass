package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysCaptcha;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * <p>
 * 系统验证码 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysCaptchaService extends IService<SysCaptcha> {

    /**
     * 获取图片验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 校验验证码
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);


}
