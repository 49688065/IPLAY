package com.burning.iplay.bean.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Kiven
 * @time 2017-2-15  15:18
 * Email f842728368@163.com
 * @desc 详情界面的视频
 */
public class Video implements Parcelable {

    /**
     * alt : DOTA2 尖枫时刻Ep.26
     * appurl :
     * broadcast : in
     * commentboard : video_bbs
     * commentid : CCAR0E0I00313N7L
     * cover : http://vimg3.ws.126.net/image/snapshot/2017/2/0/J/VCCAR0E0J.jpg
     * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/HD/movie_index.m3u8
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/SD/movie_index.m3u8
     * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/HD/fCGkt5620-mobile.mp4
     * mp4_url : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/SD/fCGkt5620-mobile.mp4
     * ref : <!--VIDEO#0-->
     * size :
     * topicid : 0031
     * url_m3u8 : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/SD/fCGkt5620-mobile.mp4
     * url_mp4 : http://flv2.bn.netease.com/videolib3/1702/14/fCGkt5620/SD/fCGkt5620-mobile.mp4
     * vid : VCCAR0E0I
     * videosource : 其他
     */

    private String alt;
    private String appurl;
    private String broadcast;
    private String commentboard;
    private String commentid;
    private String cover;
    private String m3u8Hd_url;
    private String m3u8_url;
    private String mp4Hd_url;
    private String mp4_url;
    private String ref;
    private String size;
    private String topicid;
    private String url_m3u8;
    private String url_mp4;
    private String vid;
    private String videosource;

    @Override
    public String toString() {
        return "Video{" +
                "alt='" + alt + '\'' +
                ", appurl='" + appurl + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", commentboard='" + commentboard + '\'' +
                ", commentid='" + commentid + '\'' +
                ", cover='" + cover + '\'' +
                ", m3u8Hd_url='" + m3u8Hd_url + '\'' +
                ", m3u8_url='" + m3u8_url + '\'' +
                ", mp4Hd_url='" + mp4Hd_url + '\'' +
                ", mp4_url='" + mp4_url + '\'' +
                ", ref='" + ref + '\'' +
                ", size='" + size + '\'' +
                ", topicid='" + topicid + '\'' +
                ", url_m3u8='" + url_m3u8 + '\'' +
                ", url_mp4='" + url_mp4 + '\'' +
                ", vid='" + vid + '\'' +
                ", videosource='" + videosource + '\'' +
                '}';
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getCommentboard() {
        return commentboard;
    }

    public void setCommentboard(String commentboard) {
        this.commentboard = commentboard;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getUrl_m3u8() {
        return url_m3u8;
    }

    public void setUrl_m3u8(String url_m3u8) {
        this.url_m3u8 = url_m3u8;
    }

    public String getUrl_mp4() {
        return url_mp4;
    }

    public void setUrl_mp4(String url_mp4) {
        this.url_mp4 = url_mp4;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    //************************* Parcelable ******************************/


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.alt);
        dest.writeString(this.appurl);
        dest.writeString(this.broadcast);
        dest.writeString(this.commentboard);
        dest.writeString(this.commentid);
        dest.writeString(this.cover);
        dest.writeString(this.m3u8Hd_url);
        dest.writeString(this.m3u8_url);
        dest.writeString(this.mp4Hd_url);
        dest.writeString(this.mp4_url);
        dest.writeString(this.ref);
        dest.writeString(this.size);
        dest.writeString(this.topicid);
        dest.writeString(this.url_m3u8);
        dest.writeString(this.url_mp4);
        dest.writeString(this.vid);
        dest.writeString(this.videosource);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.alt = in.readString();
        this.appurl = in.readString();
        this.broadcast = in.readString();
        this.commentboard = in.readString();
        this.commentid = in.readString();
        this.cover = in.readString();
        this.m3u8Hd_url = in.readString();
        this.m3u8_url = in.readString();
        this.mp4Hd_url = in.readString();
        this.mp4_url = in.readString();
        this.ref = in.readString();
        this.size = in.readString();
        this.topicid = in.readString();
        this.url_m3u8 = in.readString();
        this.url_mp4 = in.readString();
        this.vid = in.readString();
        this.videosource = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
