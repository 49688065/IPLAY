package com.burning.iplay.bean.detail;

/**
 * @author Kiven
 * @time 2017-2-15  15:07
 * Email f842728368@163.com
 */
public class DetailInfo {

    private boolean isCollectArticle;
    private Article article;
    private Tie tie;

    @Override
    public String toString() {
        return "DetailInfo{" +
                "isCollectArticle=" + isCollectArticle +
                ", article=" + article +
                ", tie=" + tie +
                '}';
    }

    public boolean isCollectArticle() {
        return isCollectArticle;
    }

    public void setCollectArticle(boolean collectArticle) {
        isCollectArticle = collectArticle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Tie getTie() {
        return tie;
    }

    public void setTie(Tie tie) {
        this.tie = tie;
    }
}
