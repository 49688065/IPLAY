package com.burning.iplay.bean.detail;

/**
 * @author Kiven
 * @time 2017-2-15  15:15
 * Email f842728368@163.com
 * @desc 详情界面的图片
 */
public class Image {


    /**
     * alt :
     * pixel : 550*344
     * ref : <!--IMG#0-->
     * src : http://img5.cache.netease.com/game/2017/2/15/20170215100319b52e0_550.jpg?w=768&h=20000&quality=75
     */

    private String alt;
    private String pixel;
    private String ref;
    private String src;

    @Override
    public String toString() {
        return "Image{" +
                "alt='" + alt + '\'' +
                ", pixel='" + pixel + '\'' +
                ", ref='" + ref + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
