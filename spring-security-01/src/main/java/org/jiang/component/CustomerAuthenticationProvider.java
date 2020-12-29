package org.jiang.component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2020/12/29
 * @Version 1.0
 */
public class CustomerAuthenticationProvider implements AuthenticationProvider {
    final static Map<String, SimpleGrantedAuthority> ipAuthorityMap = new ConcurrentHashMap<>();
    //维护一个ip白名单列表，每个ip对应一定的权限
    static {
        ipAuthorityMap.put("127.0.0.1",new SimpleGrantedAuthority("admin"));
        ipAuthorityMap.put("123.57.66.144",new SimpleGrantedAuthority("user"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomerAuthenticationToken authenticationToken = (CustomerAuthenticationToken) authentication;
        String ip = authenticationToken.getIp();
        SimpleGrantedAuthority simpleGrantedAuthority = ipAuthorityMap.get(ip);
        // 不在白名单当中
        if (simpleGrantedAuthority == null) {
            return null;
        } else {
            // 封装权限信息，并且此时身份已经被认证
            return new CustomerAuthenticationToken(ip, Arrays.asList(simpleGrantedAuthority));
        }
    }

    // 只支持CustomerAuthenticationToken该身份
    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomerAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
