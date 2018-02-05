package com.example.yueuy.dream.data.story;

import java.util.List;

/**
 * Created by yueuy on 18-1-30.
 */

public class StoryRandom {


    private List<RandomBean> random;

    public List<RandomBean> getRandom() {
        return random;
    }

    public void setRandom(List<RandomBean> random) {
        this.random = random;
    }

    public static class RandomBean {
        /**
         * keyword : [{"keyword":"自由"},{"keyword":"平等"},{"keyword":"文明"},{"keyword":"和谐"},{"keyword":"民主"},{"keyword":"公正"},{"keyword":"富强"},{"keyword":"法治&"}]
         * likenum : 0
         * story : 社会主义核心价值观
         * storyid : 10
         * username : 1
         */

        private int likenum;
        private String story;
        private int storyid;
        private String username;
        private List<KeywordBean> keyword;

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

        public int getStoryid() {
            return storyid;
        }

        public void setStoryid(int storyid) {
            this.storyid = storyid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<KeywordBean> getKeyword() {
            return keyword;
        }

        public void setKeyword(List<KeywordBean> keyword) {
            this.keyword = keyword;
        }

        public static class KeywordBean {
            /**
             * keyword : 自由
             */

            private String keyword;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }
        }
    }
}
