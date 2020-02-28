package com.fudan.mysite.base.result;

public enum ResultCode {


    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    REGISTRY_SUCCEED(500104, "Registry Succeed!"),
    USER_EXIST(500106, "Username already exists, register failed!"),
    LOGOUT_SUCCEED(500105, "Logout Succeed!"),
    LOGOUT_FAILED(500108, "logout failed"),
    LOGIN_FAILED(500103, "Login failed!"),
    USER_NOT_EXIST(500101, "User not exists, please check your username"),
    WRONG_PASSWORD(500102, "Wrong password, please check your password"),
    ALREADY_LOGGED_IN(500107, "You have already logged in"),
    NOT_LOGIN(500100, "Not login, please log in"),
    ARTICLE_NOT_EXIST(500110, "Article Not Exist"),
    POST_ARTICLE_SUCCEED(500109, "You have posted an article successfully!");
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
