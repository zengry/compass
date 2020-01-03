package org.hk.compass.modules.sys.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hk.compass.constants.Constants;
import org.hk.compass.modules.sys.entity.SysUser;
import org.hk.compass.modules.sys.entity.SysUserToken;
import org.hk.compass.modules.sys.oauth2.OAuth2Token;
import org.hk.compass.modules.sys.service.ISysUserRoleService;
import org.hk.compass.modules.sys.service.ISysUserService;
import org.hk.compass.modules.sys.service.ISysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author zengry
 * @description
 * @since 2019/12/27
 */

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserTokenService userTokenService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 添加依赖关系,否则报异常:
     * Realm [org.hk.compass.modules.sys.shiro.MyShiroRealm@5a1ebb0f]
     * does not support authentication token [org.hk.compass.modules.sys.oauth2.OAuth2Token@54d94e3c].
     * Please ensure that the appropriate Realm implementation is configured correctly or that the realm accepts AuthenticationTokens of this type.
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("************* 授权认证 。。。。。。。。。。。。");
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> permissions = sysUserRoleService.getUserPerms(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        log.info("************* 身份认证 。。。。。。。。。。。。");
        String token = (String) authenticationToken.getPrincipal();
        SysUserToken userToken = userTokenService.getUserToken(token);
        if(null == userToken || userToken.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效,请重新登录");
        }

        Long userId = userToken.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        if(Constants.Disable.equals(sysUser.getStatus())){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        return new SimpleAuthenticationInfo(sysUser, token, getName());
    }
}
