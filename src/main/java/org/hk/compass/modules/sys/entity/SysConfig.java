package org.hk.compass.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@ApiModel(value="SysConfig对象", description="系统配置信息表")
public class SysConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "key")
    private String paramKey;

    @ApiModelProperty(value = "value")
    private String paramValue;

    @ApiModelProperty(value = "状态   0：隐藏   1：显示")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
        "id=" + id +
        ", paramKey=" + paramKey +
        ", paramValue=" + paramValue +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
