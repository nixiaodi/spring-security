package org.jiang.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2020/12/29
 * @Version 1.0
 */
public class CustomerAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    // 使用/ipVerify端点进行ip认证
    public CustomerAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        // 获取host信息
        String host = req.getRemoteHost();
        // 交给内部的AuthenticationManager去认证，实现解耦
        return getAuthenticationManager().authenticate(new CustomerAuthenticationToken(host));
    }
}
