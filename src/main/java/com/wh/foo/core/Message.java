package com.wh.foo.core;

/**
 * @Description:
 * @Auther: WangHong
 * @Date: 2020/4/7 16:14
 */
public enum Message {

    SUCCESS("00000", "SUCCESS", "操作成功！"),
    ERROR("00001", "ERROR", "操作失败！");
    /**
     * 错误代码
     */
    private String code;

    /**
     * 前端弹出代码
     */
    private String alertCode;

    /**
     * 通用提示
     */
    private String message;

    Message(String code, String alertCode, String message) {
        this.code = code;
        this.alertCode = alertCode;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(String alertCode) {
        this.alertCode = alertCode;
    }
}
