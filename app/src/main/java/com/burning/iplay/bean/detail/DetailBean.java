package com.burning.iplay.bean.detail;

/**
 * @author Kiven
 * @time 2017-2-15  14:58
 * Email f842728368@163.com
 * @desc 新闻详情界面的网络请求用到的Bean
 */
public class DetailBean {
    private String message;
    private int code;
    private DetailInfo info;

    @Override
    public String toString() {
        return "DetailBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", info=" + info +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DetailInfo getInfo() {
        return info;
    }

    public void setInfo(DetailInfo info) {
        this.info = info;
    }
}
