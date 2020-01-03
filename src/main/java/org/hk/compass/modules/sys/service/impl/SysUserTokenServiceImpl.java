package org.hk.compass.modules.sys.service.impl;

import org.hk.compass.modules.sys.entity.SysUserToken;
import org.hk.compass.modules.sys.mapper.SysUserTokenMapper;
import org.hk.compass.modules.sys.service.ISysUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hk.compass.modules.sys.oauth2.TokenGenerator;
import org.hk.compass.util.R;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements ISysUserTokenService {

    // 8小时后过期
    private final static int EXPIRE = 3600 * 8;

    @Override
    public R generatorToken(Long userId) {

        // 生成一个 token
        String token = TokenGenerator.generateValue();

        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expire = new Date(now.getTime() + EXPIRE * 1000);

        SysUserToken userToken = this.getById(userId);
        if(null == userToken){
            userToken = new SysUserToken();
            userToken.setUserId(userId);
        }
        userToken.setToken(token);
        userToken.setExpireTime(expire);
        userToken.setUpdateTime(now);
        this.saveOrUpdate(userToken);

        return R.success().put("token", token).put("expire", EXPIRE);
    }

    @Override
    public SysUserToken getUserToken(String token) {
        return this.baseMapper.selectByToken(token);
    }

    @Override
    public R logout(Long userId) {
        SysUserToken userToken = this.baseMapper.selectById(userId);
        userToken.setToken(TokenGenerator.generateValue());
        userToken.setExpireTime(new Date());
        return (this.saveOrUpdate(userToken)) ? R.success() : R.error();
    }
}
