package com.burning.iplay.bean.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.burning.iplay.bean.jingxuan.Editor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-13  0:42
 * Email f842728368@163.com
 * @desc 列表Bean
 */
public class ArticleListBean implements Parcelable {
    /**
     * articleTags :
     * digest : 近日，据国外游戏媒体PCGamer报道。Valve老板Gabe和DOTA2项目负责人Erik在接受媒体采访时称，新任美国总统特朗普的穆斯林禁令，可能会影响到DO
     * docid : CCUSVV8H00314QVB
     * fromTopicSource : false
     * gameName :
     * id : 52685
     * imgsrc : ["http://img1.cache.netease.com/game/esports/d08212185.jpg"]
     * largeLogoUrl : http://uc.bbs.d.163.com/avatar.php?uid=1695996&size=large
     * lmodify : 2017-02-10 23:55:16
     * nickname : 诺亚荒舟
     * penName : vino
     * photosetId :
     * photosetImgNum : 0
     * priority : 70
     * ptime : 2017-02-10 23:00:03
     * readSeconds : 3
     * replyCount : 5086
     * role : 爱玩小编
     * showType : 5
     * source : 易竞技
     * specialId :
     * subtitle :
     * title : Valve称由于特朗普禁令 或考虑在美国外举办TI
     * topicName : DOTA2
     * url : http://3g.163.com/play/17/0210/23/CCUSVV8H00314QVB.html
     * userId : 31234368
     * userOrder : false
     */

    private String articleTags;
    private String digest;
    private String docid;
    private boolean fromTopicSource;
    private String gameName;
    private int id;
    private String largeLogoUrl;
    private String lmodify;
    private String nickname;
    private String penName;
    private String photosetId;
    private int photosetImgNum;
    private int priority;
    private String ptime;
    private int readSeconds;
    private int replyCount;
    private String role;
    private int showType;
    private String source;
    private String specialId;
    private String subtitle;
    private String title;
    private String topicName;
    private String url;
    private int userId;
    private boolean userOrder;
    private List<String> imgsrc;
    private List<Editor> editor;

    /**
     * fidIconUrl : http://img2.cache.netease.com/game/app/bbs/icon/417_new2.png
     * imgsrc : []
     * tid : 173392171
     * topicId : T1461396257593
     */

    private String fidIconUrl;
    private String tid;
    private String topicId;
    /**
     * docId : CD8EQDS000314C3U
     * imgUrl : http://img1.cache.netease.com/game/wow/news/201702/2143a4.jpg
     */

    private String docId;// 注意这个属性是在精选-推荐里用到的
    private String imgUrl;


    private boolean isLooked;// 新闻是否被浏览过

    @Override
    public String toString() {
        return "ArticleListBean{" +
                "articleTags='" + articleTags + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", fromTopicSource=" + fromTopicSource +
                ", gameName='" + gameName + '\'' +
                ", id=" + id +
                ", largeLogoUrl='" + largeLogoUrl + '\'' +
                ", lmodify='" + lmodify + '\'' +
                ", nickname='" + nickname + '\'' +
                ", penName='" + penName + '\'' +
                ", photosetId='" + photosetId + '\'' +
                ", photosetImgNum=" + photosetImgNum +
                ", priority=" + priority +
                ", ptime='" + ptime + '\'' +
                ", readSeconds=" + readSeconds +
                ", replyCount=" + replyCount +
                ", role='" + role + '\'' +
                ", showType=" + showType +
                ", source='" + source + '\'' +
                ", specialId='" + specialId + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", topicName='" + topicName + '\'' +
                ", url='" + url + '\'' +
                ", userId=" + userId +
                ", userOrder=" + userOrder +
                ", imgsrc=" + imgsrc +
                ", editor=" + editor +
                ", fidIconUrl='" + fidIconUrl + '\'' +
                ", tid='" + tid + '\'' +
                ", topicId='" + topicId + '\'' +
                ", docId='" + docId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isLooked=" + isLooked +
                '}';
    }

    public boolean isLooked() {
        return isLooked;
    }

    public void setLooked(boolean looked) {
        isLooked = looked;
    }

    public List<Editor> getEditor() {
        return editor;
    }

    public void setEditor(List<Editor> editor) {
        this.editor = editor;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 跳转详情界面时用到的
     */
    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public boolean isFromTopicSource() {
        return fromTopicSource;
    }

    public void setFromTopicSource(boolean fromTopicSource) {
        this.fromTopicSource = fromTopicSource;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLargeLogoUrl() {
        return largeLogoUrl;
    }

    public void setLargeLogoUrl(String largeLogoUrl) {
        this.largeLogoUrl = largeLogoUrl;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getPhotosetId() {
        return photosetId;
    }

    public void setPhotosetId(String photosetId) {
        this.photosetId = photosetId;
    }

    public int getPhotosetImgNum() {
        return photosetImgNum;
    }

    public void setPhotosetImgNum(int photosetImgNum) {
        this.photosetImgNum = photosetImgNum;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReadSeconds() {
        return readSeconds;
    }

    public void setReadSeconds(int readSeconds) {
        this.readSeconds = readSeconds;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSpecialId() {
        return specialId;
    }

    public void setSpecialId(String specialId) {
        this.specialId = specialId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isUserOrder() {
        return userOrder;
    }

    public void setUserOrder(boolean userOrder) {
        this.userOrder = userOrder;
    }

    public List<String> getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(List<String> imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getFidIconUrl() {
        return fidIconUrl;
    }

    public void setFidIconUrl(String fidIconUrl) {
        this.fidIconUrl = fidIconUrl;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    //************************* Parcel化代码 ******************************/


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.articleTags);
        dest.writeString(this.digest);
        dest.writeString(this.docid);
        dest.writeByte(this.fromTopicSource ? (byte) 1 : (byte) 0);
        dest.writeString(this.gameName);
        dest.writeInt(this.id);
        dest.writeString(this.largeLogoUrl);
        dest.writeString(this.lmodify);
        dest.writeString(this.nickname);
        dest.writeString(this.penName);
        dest.writeString(this.photosetId);
        dest.writeInt(this.photosetImgNum);
        dest.writeInt(this.priority);
        dest.writeString(this.ptime);
        dest.writeInt(this.readSeconds);
        dest.writeInt(this.replyCount);
        dest.writeString(this.role);
        dest.writeInt(this.showType);
        dest.writeString(this.source);
        dest.writeString(this.specialId);
        dest.writeString(this.subtitle);
        dest.writeString(this.title);
        dest.writeString(this.topicName);
        dest.writeString(this.url);
        dest.writeInt(this.userId);
        dest.writeByte(this.userOrder ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.imgsrc);
        dest.writeList(this.editor);
        dest.writeString(this.fidIconUrl);
        dest.writeString(this.tid);
        dest.writeString(this.topicId);
        dest.writeString(this.docId);
        dest.writeString(this.imgUrl);
        dest.writeByte(this.isLooked ? (byte) 1 : (byte) 0);
    }

    public ArticleListBean() {
    }

    protected ArticleListBean(Parcel in) {
        this.articleTags = in.readString();
        this.digest = in.readString();
        this.docid = in.readString();
        this.fromTopicSource = in.readByte() != 0;
        this.gameName = in.readString();
        this.id = in.readInt();
        this.largeLogoUrl = in.readString();
        this.lmodify = in.readString();
        this.nickname = in.readString();
        this.penName = in.readString();
        this.photosetId = in.readString();
        this.photosetImgNum = in.readInt();
        this.priority = in.readInt();
        this.ptime = in.readString();
        this.readSeconds = in.readInt();
        this.replyCount = in.readInt();
        this.role = in.readString();
        this.showType = in.readInt();
        this.source = in.readString();
        this.specialId = in.readString();
        this.subtitle = in.readString();
        this.title = in.readString();
        this.topicName = in.readString();
        this.url = in.readString();
        this.userId = in.readInt();
        this.userOrder = in.readByte() != 0;
        this.imgsrc = in.createStringArrayList();
        this.editor = new ArrayList<Editor>();
        in.readList(this.editor, Editor.class.getClassLoader());
        this.fidIconUrl = in.readString();
        this.tid = in.readString();
        this.topicId = in.readString();
        this.docId = in.readString();
        this.imgUrl = in.readString();
        this.isLooked = in.readByte() != 0;
    }

    public static final Creator<ArticleListBean> CREATOR = new Creator<ArticleListBean>() {
        @Override
        public ArticleListBean createFromParcel(Parcel source) {
            return new ArticleListBean(source);
        }

        @Override
        public ArticleListBean[] newArray(int size) {
            return new ArticleListBean[size];
        }
    };
}