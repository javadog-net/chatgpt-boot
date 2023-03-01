package net.javadog.chatgpt.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: JWTToken
 * @Author: hdx
 * @Date: 2022/1/13 15:36
 * @Version: 1.0
 */
public class JwtToken implements AuthenticationToken {
    private final String token;

    public JwtToken(String token) {
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
