package com.ssafy.sixhats.service;

import com.ssafy.sixhats.exception.UnAuthorizedException;
import com.ssafy.sixhats.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private static final String secretKey = "ssafySecret";
    private static final int EXPIRE_MINUTES = 60;

    public <T> String createToken(String key, T data, String subject) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
                .setSubject(subject)
                .claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
        return jwt;
    }


    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return key;
    }

    // 토큰 유효성 검증
    public boolean isUsable(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String jwt = request.getHeader("access-token");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(jwt);
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
        Map<String, Object> value = claims.getBody();
        return value;
    }

    public String getUserId(){
        return (String)this.get("user").get("userId");
    }
}
