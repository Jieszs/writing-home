package com.jiesz.writinghome.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiesz.writinghome.exception.UnauthorizedException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtil {
    private static final String TOKEN_SECRET = "123456";//token签名key
    public static final int TOKEN_EXPIRE_TIME = 24 * 60;//token过期时间(分钟)

    /**
     * 创建token
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String createToken(Map<String, String> data) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, TOKEN_EXPIRE_TIME);
        Date expiresDate = nowTime.getTime();
        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        JWTCreator.Builder builder = JWT.create().withHeader(map) // header
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate); // expire time
        for (Map.Entry<String, String> e : data.entrySet()) {
            builder.withClaim(e.getKey(), e.getValue());
        }
        String token = builder.sign(Algorithm.HMAC256(TOKEN_SECRET)); // signature
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> parse(String token) throws UnauthorizedException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException("invalid token");
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @return 1:校验通过,0:过期,-1:校验不通过
     */
    public static int verify(String token) throws UnauthorizedException {
        try {
            parse(token);
            return 1;
        } catch (TokenExpiredException e) {
            return 0;
        } catch (JWTDecodeException | SignatureVerificationException e) {
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZ2VudElkIjoiNDAiLCJwYXNzd29yZCI6InBhNDR3MHJkIiwiYWdlbnRUeXBlIjoiMiIsImFnZW50TmFtZSI6IuiSi-aZqOm-mSIsImlzQWRtaW4iOiJmYWxzZSIsImV4cCI6MTU1OTY2NzYxNCwiaWF0IjoxNTU5NjM4ODE0LCJ1c2VybmFtZSI6ImppYW5nY2hlbmxvbmcifQ.5Kq5BbY_J_JHdU9cB7pp5Uq6nzKnsJL8VWmAuCgHunM";
        Map<String, Claim> map = parse(token);
        System.out.println(map.get("agentType").asString());
        System.out.println(map.get("agentName").asString());
        System.out.println(map.get("isAdmin").asString());
    }
}
