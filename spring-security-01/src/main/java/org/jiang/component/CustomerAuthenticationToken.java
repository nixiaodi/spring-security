package org.jiang.component;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2020/12/29
 * @Version 1.0
 */
public class CustomerAuthenticationToken extends AbstractAuthenticationToken {
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 构造方法在认证时调用
     * @param ip
     */
    public CustomerAuthenticationToken(String ip) {
        super(null);
        this.ip = ip;
        super.setAuthenticated(true);
    }

    /**
     * 构造方法在认证成功后调用
     * @param ip
     * @param authorities
     */
    public CustomerAuthenticationToken(String ip,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.ip = ip;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
