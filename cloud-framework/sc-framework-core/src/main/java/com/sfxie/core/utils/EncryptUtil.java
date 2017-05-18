package com.sfxie.core.utils;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import com.google.common.io.BaseEncoding;

public class EncryptUtil {

    private static final String ENCODING = "UTF-8";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
    
    /**
     * 
     * @param ts
     * 			毫秒
     * @param secret
     * 			密钥
     * @param params
     * 			请求参数
     * @return
     * 			加密后的密文
     * @throws SignatureException
     */
	public static String hmacSha1(List<String> ts ,String secret,String uri,Map<String ,List<String>> params) throws SignatureException {
		params.put("ts", ts);
		return calculateRFC2104HMAC(sortParams(uri,params),secret);
	}
	
	private static String sortParams(String uri,Map<String ,List<String>> param){
		String paramValues = "";
		if(null==param)
			return paramValues;
        List<String> keyList = new ArrayList<String>();
        for (String key : param.keySet()) {
            keyList.add(key);
        }
        Collections.sort(keyList);
        for (String key : keyList) {
            if (!StringUtils.isEmpty(param.get(key))) {
                paramValues += JsonUtil.toJson(param.get(key));
            }
        }
        return paramValues+uri;
	}
	
	
	/**
     * * Computes RFC 2104-compliant HMAC signature.
     * * * @param data
     * * The data to be signed.
     * *
     * * @param key The signing key.
     * * @return The Base64-urlSafe encoded RFC 3548-compliant HMAC signature.
     * * @throws java.security.SignatureException when signature generation fails
     */
    private static String calculateRFC2104HMAC(String data, String key)
            throws java.security.SignatureException {
        //System.out.println(data);
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(ENCODING), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes(ENCODING));
            result = BaseEncoding.base64Url().encode(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}
