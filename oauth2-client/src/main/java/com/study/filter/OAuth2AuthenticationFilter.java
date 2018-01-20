package com.study.filter;

import com.study.model.OAuth2Token;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 身份验证过滤器
 *
 * @author xujiping 2018-01-19 14:47
 */

public class OAuth2AuthenticationFilter extends AuthenticatingFilter {

    //oauth2 authc code参数名
    private String authcCodeParam = "code";

    private String clientId;

    //服务器登陆成功/失败后重定向的客户端地址
    private String redirectUrl;
    private String failureUrl;
    //oauth2服务器响应类型
    private String responseType = "code";

    public void setAuthcCodeParam(String authcCodeParam) {
        this.authcCodeParam = authcCodeParam;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response)
        throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String code = httpRequest.getParameter(authcCodeParam);
        return new OAuth2Token(code);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object
        mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws
        Exception {
        String error = request.getParameter("error");
        String errorDescription = request.getParameter("error_description");
        if (!StringUtils.isEmpty(error)) {
            //服务端返回了错误
            WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error +
                "error_description=" + errorDescription);
            return false;
        }
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            if (StringUtils.isEmpty(request.getParameter(authcCodeParam))) {
                //如果用户没有身份验证，且没有auth code，则重定向到服务端授权
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }
        //执行父类里的登录逻辑，调用Subject.login登录
        return executeLogin(request, response);
    }

    /**
     * 登录成功后的回调方法 重定向到成功页面
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest
        request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;
    }

    /**
     * 登陆失败后回调
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                //如果身份验证成功了 则也重定向到成功页面
                issueSuccessRedirect(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                //登录失败时重定向到失败页面
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
