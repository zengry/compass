package org.hk.compass.modules.sys.mapper;

import org.hk.compass.modules.sys.entity.SysUserToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 * 系统用户Token Mapper 接口
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface SysUserTokenMapper extends BaseMapper<SysUserToken> {
    @Override
    SysUserToken selectById(Serializable id);

    SysUserToken selectByToken(String token);
}
