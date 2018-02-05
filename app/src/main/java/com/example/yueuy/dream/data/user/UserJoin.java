package com.example.yueuy.dream.data.user;

import java.util.List;

/**
 * Created by yueuy on 18-2-3.
 */

public class UserJoin {
    private List<JoinBean> join;

    public List<JoinBean> getJoin() {
        return join;
    }

    public void setJoin(List<JoinBean> join) {
        this.join = join;
    }

    public static class JoinBean {
        /**
         * story : 红红火火红红火火哈哈哈哈红红火火红红火火红红火火红红火火红红
         * storyid : 2
         */

        private String story;
        private int storyid;

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
    }
}
