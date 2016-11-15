package com.yl.updatedemo.bean;

/**
 * Created by Administrator on 2016/11/13 0013.
 */

public class MyUpdate {


    /**
     * forced : false
     * updateContent : yl大神吐血制作的新版本
     * updateUrl : xxx
     * updateTime : xxx
     * versionCode : 2.0
     * versionName : 吐血版
     * ignore : false
     */

    private OriginalBean original;

    public OriginalBean getOriginal() {
        return original;
    }

    public void setOriginal(OriginalBean original) {
        this.original = original;
    }

    public static class OriginalBean {
        private boolean forced;
        private String updateContent;
        private String updateUrl;

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        private int updateTime;
        private double versionCode;
        private String versionName;
        private boolean ignore;

        public boolean isForced() {
            return forced;
        }

        public void setForced(boolean forced) {
            this.forced = forced;
        }

        public String getUpdateContent() {
            return updateContent;
        }

        public void setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }



        public double getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(double versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public boolean isIgnore() {
            return ignore;
        }

        public void setIgnore(boolean ignore) {
            this.ignore = ignore;
        }
    }
}
