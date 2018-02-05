package com.example.yueuy.dream.data.story;

/**
 * Created by yueuy on 18-2-3.
 */

public class StoryWrite {

    /**
     * story : 社会主义核心价值观
     * uid : 1
     * keyword : {"keyword1":"富强","keyword2":"民主","keyword3":"文明","keyword4":"和谐","keyword5":"自由","keyword6":"平等","keyword7":"公正","keyword8":"法治"}
     */

    private String story;
    private String uid;
    private KeywordBean keyword;

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public KeywordBean getKeyword() {
        return keyword;
    }

    public void setKeyword(KeywordBean keyword) {
        this.keyword = keyword;
    }

    public static class KeywordBean {
        /**
         * keyword1 : 富强
         * keyword2 : 民主
         * keyword3 : 文明
         * keyword4 : 和谐
         * keyword5 : 自由
         * keyword6 : 平等
         * keyword7 : 公正
         * keyword8 : 法治
         */

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
