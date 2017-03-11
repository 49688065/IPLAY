package com.burning.iplay.bean.comment;

/**
 * @author Kiven
 * @time 2017-3-7  10:40
 * Email f842728368@163.com
 * @desc 用户相关信息，目前用在评论界面
 */
public class UserBean {

    /**
     * avatar : http://cms-bucket.nosdn.127.net/b6664e6ce2804d758cbcb4217eba971420161214174857.jpg
     * location : 河南省
     * nickname : 人如羊屠做粮
     *
     * id : eHNqMTM2Mzk2MjU2NDlAMTYzLmNvbQ==
     * np :
     * nu : 烦恼之歌
     * userId : 9539042
     * vipInfo : vipm
     */

    private String avatar;
    private String id;
    private String location;
    private String nickname;
    private String np;
    private String nu;
    private int userId;
    private String vipInfo;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVipInfo() {
        return vipInfo;
    }

    public void setVipInfo(String vipInfo) {
        this.vipInfo = vipInfo;
    }


}
