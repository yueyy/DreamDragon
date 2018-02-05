package com.example.yueuy.dream.data.user;

import java.util.List;

/**
 * Created by yueuy on 18-2-3.
 */

public class UserStart {
    private List<Start> start;

    public List<Start> getStart() {
        return start;
    }

    public void setStart(List<Start> start) {
        this.start = start;
    }

    public static class Start{
        private int storyid;
        private String story;
        private List<Start> start;

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

        public List<Start> getStart() {
            return start;
        }

        public void setStart(List<Start> start) {
            this.start = start;
        }
    }

}
