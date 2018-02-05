package com.example.yueuy.dream.data.story;

import java.util.List;

/**
 * Created by yueuy on 18-1-30.
 */

public class StoryRank {


    private List<RankBean> rank;

    public List<RankBean> getRank() {
        return rank;
    }

    public void setRank(List<RankBean> rank) {
        this.rank = rank;
    }

    public static class RankBean {
        /**
         * keyword : [{"keyword":"公正"},{"keyword":"自由"},{"keyword":"民主"},{"keyword":"富强"},{"keyword":"平等"},{"keyword":"文明"},{"keyword":"和谐"},{"keyword":"法治&"}]
         * likenum : 7
         * story : 红红火火红红火火哈哈哈哈红红火火红红火火红红火火红红火火红红火火
         * storyid : 2
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
    }
}
