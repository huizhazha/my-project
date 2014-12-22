package org.springframework.spring.security.custom;

import org.springframework.security.core.AuthenticationException;


public class CaptchaIncorrectException extends AuthenticationException{

    private static final long serialVersionUID = -3087715212466495446L;

    public CaptchaIncorrectException(String msg) {
        super(msg);
    }

}
