package org.panchuk.app.util;

public enum ActionType {
    REGISTRATION(1),
    CHECK_MAIL(2),
    LOGIN_PASS(3),
    LOGOUT(4),
    LOGIN_FAIL(5);

    private final int val;

    ActionType(int val) {
        this.val = val;
    }

    public int get() {
        return val;
    }
}
