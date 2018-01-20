package com.study.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * ${DESCRIPTION}
 *
 * @author xujiping 2018-01-19 15:08
 */

public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
