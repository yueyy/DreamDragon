package com.example.yueuy.dream.data.user;

import java.util.List;

/**
 * Created by yueuy on 18-2-3.
 */

public class UserJoin {
    private List<Join> join;

    public List<Join> getJoin() {
        return join;
    }

    public void setJoin(List<Join> join) {
        this.join = join;
    }

    public class Join{
        private String story;
        private int storyid;
        private int storycid;

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

        public int getStorycid() {
            return storycid;
        }

        public void setStorycid(int storycid) {
            this.storycid = storycid;
        }
    }
}
