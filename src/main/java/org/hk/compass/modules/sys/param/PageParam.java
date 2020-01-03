package org.hk.compass.modules.sys.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengry
 * @description
 * @since 2019/12/31
 */
@Data
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1855329575278821983L;
    /**
     * 当前页
     */
    private Integer page = 1;
    /**
     * 每页记录数
     */
    private Integer limit = 10;

}
