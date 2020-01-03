package org.hk.compass.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zengry
 * @description  token
 * @since 2019/12/31
 */
public class OAuth2Token implements AuthenticationToken {
    private static final long serialVersionUID = 4892229366200667194L;
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
