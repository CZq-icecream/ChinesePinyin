package com.czq.chinesepinyin.util;

/**
 * 用户状态
 */
public enum AuthenticationState {
    UNAUTHENTICATED,        // Initial state, the user needs to authenticate
    AUTHENTICATED,          // The user has authenticated successfully
    INVALID_AUTHENTICATION  // Authentication failed
}

