package top.xlaoer.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private static final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/v1/user/login",
            "/api/short-link/v1/user/has-username",
            "/api/short-link/v1/user"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // TODO：后续要在网关这里去让用户进行登录，而不是直接写死在这里
        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URI.contains(requestURI)) {
            String username = httpServletRequest.getHeader("username");
            if(StrUtil.isNotBlank(username)) {
                String token = httpServletRequest.getHeader("token");
                Object userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                if (userInfoJsonStr != null) {
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
                }
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 防止内存泄露
            UserContext.removeUser();
        }
    }
}