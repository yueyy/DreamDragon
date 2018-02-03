package com.example.yueuy.dream.data.story;

import java.util.List;

/**
 * Created by yueuy on 18-1-30.
 */

public class StoryRandom {
    private List<Random> random;

    public List<Random> getRandom() {
        return random;
    }

    public void setRandom(List<Random> random) {
        this.random = random;
    }

    public class Random{
        private String username;
        private int storyid;
        private String story;
        private int likenum;
        private List<Keyword> keyword;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getStoryid() {
            return storyid;
        }

        public void setStoryid(int storyid) {
            this.storyid = storyid;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public int getLikenum() {
            return likenum;
        }

        public void setLikenum(int likenum) {
            this.likenum = likenum;
        }

        public List<Keyword> getKeyword() {
            return keyword;
        }

        public void setKeyword(List<Keyword> keyword) {
            this.keyword = keyword;
        }

        public class Keyword{
            private String keyword1;
            private String keyword2;
            private String keyword3;
            private String keyword4;
            private String keyword5;
            private String keyword6;
            private String keyword7;
            private String keyword8;

            public String getKeyword1() {
                return keyword1;
            }

            public void setKeyword1(String keyword1) {
                this.keyword1 = keyword1;
            }

            public String getKeyword2() {
                return keyword2;
            }

            public void setKeyword2(String keyword2) {
                this.keyword2 = keyword2;
            }

            public String getKeyword3() {
                return keyword3;
            }

            public void setKeyword3(String keyword3) {
                this.keyword3 = keyword3;
            }

            public String getKeyword4() {
                return keyword4;
            }

            public void setKeyword4(String keyword4) {
                this.keyword4 = keyword4;
            }

            public String getKeyword5() {
                return keyword5;
            }

            public void setKeyword5(String keyword5) {
                this.keyword5 = keyword5;
            }

            public String getKeyword6() {
                return keyword6;
            }

            public void setKeyword6(String keyword6) {
                this.keyword6 = keyword6;
            }

            public String getKeyword7() {
                return keyword7;
            }

            public void setKeyword7(String keyword7) {
                this.keyword7 = keyword7;
            }

            public String getKeyword8() {
                return keyword8;
            }

            public void setKeyword8(String keyword8) {
                this.keyword8 = keyword8;
            }
        }
    }

}
