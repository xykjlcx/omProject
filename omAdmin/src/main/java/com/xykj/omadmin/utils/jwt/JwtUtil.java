package com.xykj.omadmin.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author ocean
 * @Title: JwtUtil
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午10:08
 */
public class JwtUtil {

    private static final String CONTEXT_USER_ID = "userId";
    private static final String JWT_PRIVATE_KEY = "oceanmooc";
    public static int EXPIRE_TIME = 2 * 60 * 60 * 1000;

    public static String generateToken(UserToken userToken, int expire) throws Exception {
        String token = Jwts.builder()
                .setSubject(userToken.getUsername())
                .setId(userToken.getUserId() + "")
                .claim(CONTEXT_USER_ID, userToken.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, JWT_PRIVATE_KEY)
                .compact();
        System.out.println("------------生成token-------:" + token);
        return token;
    }


    public static String getInfoFromToken(String token) throws Exception {
        String returnValue = "";
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_PRIVATE_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("-------SubjectId--------:" + claims.get(CONTEXT_USER_ID));
            System.out.println("-------Subject--------:" + claims.getSubject());
            System.out.println("解析token成功");
            returnValue = claims.toString();
        } catch (Exception e) {
            throw new Exception("过期登录");
//            e.printStackTrace();
        } finally {
            return returnValue;
        }
    }

}
