package com.burning.iplay.bean.news;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-3-6  16:01
 * Email f842728368@163.com
 * @desc 近期热搜的数据
 */
public class HotSearchData {

    /**
     * code : 0
     * info : [{"id":6,"keyword":"steam","priority":99},{"id":7,"keyword":"一周COS秀","priority":97},{"id":8,"keyword":"手游有姿势","priority":96},{"id":9,"keyword":"每日一雷","priority":94},{"id":10,"keyword":"游戏音乐","priority":93},{"id":11,"keyword":"爱玩也爱买","priority":91}]
     * writeNull : false
     */

    private int code;
    private boolean writeNull;
    private List<InfoBean> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isWriteNull() {
        return writeNull;
    }

    public void setWriteNull(boolean writeNull) {
        this.writeNull = writeNull;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 6
         * keyword : steam
         * priority : 99
         */

        private int id;
        private String keyword;
        private int priority;

        @Override
        public String toString() {
            return "InfoBean{" +
                    "id=" + id +
                    ", keyword='" + keyword + '\'' +
                    ", priority=" + priority +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}

