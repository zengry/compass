package org.hk.compass.modules.sys.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */
@Data
public class BaseParam implements Serializable {
    private static final long serialVersionUID = -5493672172511473610L;
    private Long userId;
}
