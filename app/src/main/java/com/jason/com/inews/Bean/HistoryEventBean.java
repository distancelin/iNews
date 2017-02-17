package com.jason.com.inews.Bean;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */
public class HistoryEventBean {

    /**
     * reason : success
     * result : [{"day":"7/21","date":"前776年07月21日","title":"世界上第一次奥林匹克运动会在古希腊举行","e_id":"8318"},{"day":"7/21","date":"前356年07月21日","title":"世界七大奇迹之一的亚底米神庙因遭人蓄意纵火而被毁","e_id":"8319"},{"day":"7/21","date":"541年07月21日","title":"隋文帝杨坚诞辰","e_id":"8320"},{"day":"7/21","date":"710年07月21日","title":"李隆基政变诛韦后","e_id":"8321"},{"day":"7/21","date":"710年07月21日","title":"唐代著名才女上官婉儿逝世","e_id":"8322"},{"day":"7/21","date":"1298年07月21日","title":"苏格兰独立战争中的法兰克林战役爆发","e_id":"8323"},{"day":"7/21","date":"1568年07月21日","title":"尼德兰独立战争中的杰明根战役爆发","e_id":"8324"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * day : 7/21
         * date : 前776年07月21日
         * title : 世界上第一次奥林匹克运动会在古希腊举行
         * e_id : 8318
         */

        private String day;
        private String date;
        private String title;
        private String e_id;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }
    }
}