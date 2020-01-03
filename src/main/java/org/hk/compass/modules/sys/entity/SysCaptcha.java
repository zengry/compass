package org.hk.compass.modules.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@ApiModel(value="SysCaptcha对象", description="系统验证码")
public class SysCaptcha implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "过期时间")
    private Date expireTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "SysCaptcha{" +
        "uuid=" + uuid +
        ", code=" + code +
        ", expireTime=" + expireTime +
        "}";
    }
}
