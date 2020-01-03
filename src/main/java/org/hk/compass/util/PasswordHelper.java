package org.hk.compass.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author zengry
 * @description 密码加密
 * @since 2019/12/27
 */
public class PasswordHelper {
    public static String encyptPassword(String source, String salt){
        return new Sha256Hash(source, salt).toHex();
    }
}
