package com.ocnyang.anchorimageview;

/**
 * 图片中的锚点坐标
 */
public class Anchor implements IAnchor{

    // 标记点相对于横向的宽度的比例
    private double width_scale;
    // 标记点相对于横向的高度的比例
    private double height_scale;
    private String tag;

    @Override
    public double getWidthScale() {
        return width_scale;
    }

    @Override
    public double getHeightScale() {
        return height_scale;
    }

    @Override
    public Object getTag() {
        return tag;
    }

    public void setWidth_scale(double width_scale) {
        this.width_scale = width_scale;
    }

    public void setHeight_scale(double height_scale) {
        this.height_scale = height_scale;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
