package org.panchuk.app.util;

/**
 * time in ms
 */
public enum ExpireTime {
    REGISTRATION_TIME(500000000),
    CHECK_MAIL_TIME(500000000),
    LOGIN_PASS_TIME(5000000),
    LOGOUT_TIME(50000),
    LOGIN_FAIL_TIME(50000);

    private final int value;
    ExpireTime(int value) {
        this.value = value;
    }
    public int get() {
        return value;
    }
}
