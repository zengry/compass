package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysUserToken;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hk.compass.util.R;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysUserTokenService extends IService<SysUserToken> {
    /**
     * 获取token
     * @param userId
     * @return
     */
    R generatorToken(Long userId);

    /**
     * 获取user toekn
     * @param token
     * @return
     */
    SysUserToken getUserToken(String token);

    /**
     * 退出登录
     * @param userId
     * @return
     */
    R logout(Long userId);
}
