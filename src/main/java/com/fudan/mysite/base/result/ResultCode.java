package com.fudan.mysite.base.result;

public enum ResultCode {


    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    REGISTRY_SUCCEED(500104, "Registry Succeed!"),
    USER_EXIST(500106, "Username already exists, register failed!"),
    LOGOUT_SUCCEED(500105, "Logout Succeed!"),
    LOGIN_FAILED(500103, "Login failed!"),
    USER_NOT_EXIST(500101, "User not exists, please check your username"),
    WRONG_PASSWORD(500102, "Wrong password, please check your password"),
    NOT_LOGIN(500100, "Not login, please log in");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
