package com.insigma.common.jwt;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;


/**
 * jwt������
 *
 * @author admin
 */
public class JWT {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK 6yhn^YHNabcdefg>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    private static final long MAX_AGE = 1000 * 60 * 60 * 24 * 7; //7��
    //���ܣ�����һ���������Ч��
    public static <T> String sign(T object) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<>(4);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + MAX_AGE);
            return signer.sign(claims);
        } catch (Exception e) {
            return null;
        }
    }

    //���ܣ�����һ�����ܺ��token�ַ����ͽ��ܺ������
    public static <T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}