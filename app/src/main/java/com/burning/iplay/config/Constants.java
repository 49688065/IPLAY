package com.burning.iplay.config;

/**
 * @author Kiven
 * @time 2017-2-16  17:19
 * Email f842728368@163.com
 * @desc 常量类
 */
public interface Constants {
    /**
     * SP存储的所有参数的文件名称
     */
    String SP_FILE_NAME = "shared_prefer_file";

    /**
     * 日间/夜间模式存储的键值
     */
    String IS_NIGHT_MODE = "is_night_mode";


    /**
     * 头条
     */
    String TOPIC = "T1461396257593";
    /**
     * 头条
     */
    String FOCUSED = "T1461396257593";
    /**
     * 网游
     */
    String ONLINE_GAME = "T1461396384709";
    /**
     * 手游
     */
    String MOBILE_GAME = "T1461396358842";
    /**
     * 主机
     */
    String PLAYSTATION = "T1461396291717";
    /**
     * 电竞
     */
    String ELEC_SPORT = "T1461396413033";

    /**
     * 每次网络请求时请求数据的数量
     */
    int REQUEST_COUNTS = 20;

    /**
     * 热门评论的数量
     */
    int HOT_COMMENT_COUNT = 10;


    /**
     * 存放在SP中的关注的类型
     */
    String FOCUSE_IDS = "topic_ids";

    /**
     * 是否有关注的栏目
     */
    String IS_FOCUSED = "is_focused";


    /**
     * 当前字体
     */
    java.lang.String FONT_FAMILY = "font_family";

    /**
     * 微软雅黑字体
     */
    String WEI_RUAN_YA_HEI = "fonts/WeiRuanYaHei.ttf";
    /**
     * 庞中华行书
     */
    String PANGZHONGHUA_XING_SHU  = "fonts/PangZhongHuaXingShu.ttf";
}
