package com.yf.warehouse.data;

import java.util.List;

/**
 * @author cjq
 * https://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=1
 */
public class WebPicData {
    private TooltipsBean tooltips;
    private List<WebImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<WebImagesBean> getImages() {
        return images;
    }

    public void setImages(List<WebImagesBean> images) {
        this.images = images;
    }

    public static class TooltipsBean {
        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }
    }

}
