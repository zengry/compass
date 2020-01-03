package org.hk.compass.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.hk.compass.exception.CommonException;
import org.hk.compass.modules.sys.entity.SysCaptcha;
import org.hk.compass.modules.sys.mapper.SysCaptchaMapper;
import org.hk.compass.modules.sys.service.ISysCaptchaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hk.compass.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements ISysCaptchaService {

    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new CommonException("uuid不能为空");
        }

        //生成文字验证码
        String code = producer.createText();
        SysCaptcha captcha = new SysCaptcha();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        captcha.setExpireTime(DateUtils.addDateMinutes(new Date(), 2));     //2分钟后过期
        this.save(captcha);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptcha captcha = this.getOne(new QueryWrapper<SysCaptcha>().eq("uuid", uuid));
        if(null == captcha){
            return false;
        }
        this.removeById(uuid);
        return captcha.getCode().equalsIgnoreCase(code)
            && captcha.getExpireTime().getTime() >= System.currentTimeMillis();

    }



}
