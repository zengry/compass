package org.hk.compass.modules.sys.oauth2;

import org.hk.compass.exception.CommonException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author zengry
 * @description token生成器
 * @since 2019/12/27
 */
public class TokenGenerator {
    private static final char[] hexCode = "asSDwb31j219@$#%*@".toCharArray();

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static String generateValue(String uuid){
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(uuid.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new CommonException("Token生成失败", e);
        }
    }

    private static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }
}
