package com.example.yueuy.dream.data.story;

import java.util.List;

/**
 * Created by yueuy on 18-1-30.
 */

public class StoryData {


    /**
     * keywords : [{"keyword":"公正"},{"keyword":"自由"},{"keyword":"民主"},{"keyword":"富强"},{"keyword":"平等"},{"keyword":"文明"},{"keyword":"和谐"},{"keyword":"法治&"}]
     * likenum : 7
     * story : 红红火火红红火火哈哈哈哈红红火火红红火火红红火火红红火火红红火火
     * storyc : [{"storyc":"好好好","usernamec":"1"}]
     * username : 1
     */

    private int likenum;
    private String story;
    private String username;
    private List<KeywordsBean> keywords;
    private List<StorycBean> storyc;

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<KeywordsBean> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordsBean> keywords) {
        this.keywords = keywords;
    }

    public List<StorycBean> getStoryc() {
        return storyc;
    }

    public void setStoryc(List<StorycBean> storyc) {
        this.storyc = storyc;
    }

    public static class KeywordsBean {
        /**
         * keyword : 公正
         */

        private String keyword;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

    public static class StorycBean {
        /**
         * storyc : 好好好
         * usernamec : 1
         */

        private String storyc;
        private String usernamec;

        public String getStoryc() {
            return storyc;
        }

        public void setStoryc(String storyc) {
            this.storyc = storyc;
        }

        public String getUsernamec() {
            return usernamec;
        }

        public void setUsernamec(String usernamec) {
            this.usernamec = usernamec;
        }
    }
}
