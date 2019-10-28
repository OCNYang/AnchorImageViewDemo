package com.ocnyang.anchorimageview;

import java.util.ArrayList;

public class Imager implements IImager{
    private String url;
    private float scale;
    private ArrayList<Anchor> pointSimples;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public ArrayList<? extends IAnchor> getAnchorList() {
        return pointSimples;
    }
}
