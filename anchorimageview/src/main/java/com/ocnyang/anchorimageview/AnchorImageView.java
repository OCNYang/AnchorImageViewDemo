package com.ocnyang.anchorimageview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AnchorImageView extends FrameLayout implements View.OnClickListener {

    protected ArrayList<? extends IAnchor> points;
    protected FrameLayout layouPoints;
    protected ImageView imgBg;
    protected Context mContext;
    private OnAnchorClickListener mOnAnchorClickListener;
    private boolean mUseCustomTag;
    private Object previousTag;

    public AnchorImageView(Context context) {
        this(context, null);
    }

    public AnchorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnchorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    protected void initView(Context context, AttributeSet attrs) {
        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    public Object getPreviousTag() {
        return previousTag;
    }

    public void setPreviousTag(Object previousTag) {
        this.previousTag = previousTag;
    }

    public ViewGroup getLayouPoints() {
        return layouPoints;
    }

    public void setImage(int width, int height, String imgUrl) {
        setLayoutParamsWithImageSize(width, height);

        Glide.with(mContext).load(imgUrl).override(width, height).into(imgBg);

        addAnchors(width, height, this);
    }

    protected void setLayoutParamsWithImageSize(int width, int height) {
        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);
    }

    public void setAnchors(ArrayList<? extends IAnchor> points) {
        this.setAnchors(points, false);
    }

    public void setAnchors(ArrayList<? extends IAnchor> points, boolean useCustomTag) {
        this.points = points;
        this.mUseCustomTag = useCustomTag;
    }


    /**
     * 需要自定义锚点样式的，重写此方法，或 addOneAnchorView(...) 方法
     * <p>
     * 注意：锚点 tag 的设置、锚点点击事件的设置
     *
     * @param width
     * @param height
     * @param onClickListener
     */
    protected void addAnchors(int width, int height, View.OnClickListener onClickListener) {
        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {
            addOneAnchorView(width, height, points.get(i), mUseCustomTag ? points.get(i).getTag() : i, onClickListener);
        }
    }

    protected void addOneAnchorView(int width, int height, IAnchor iAnchor, Object tag, View.OnClickListener onClickListener) {
        double width_scale = iAnchor.getWidthScale();
        double height_scale = iAnchor.getHeightScale();

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);

        if (tag != null) {
            imageView.setTag(tag);
        }

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

        layoutParams.leftMargin = (int) ((width * width_scale) - 30);
        layoutParams.topMargin = (int) ((height * height_scale) - 30);

        imageView.setOnClickListener(onClickListener);

        layouPoints.addView(view, layoutParams);
    }

    @Override
    public void onClick(View view) {
        if (mOnAnchorClickListener != null) {
            mOnAnchorClickListener.onAnchorClick(view, layouPoints, view.getTag(), previousTag);
            previousTag = view.getTag();
        }
    }

    public void setAnchorOnClickListener(OnAnchorClickListener onAnchorClickListener) {
        this.mOnAnchorClickListener = onAnchorClickListener;
    }

    public interface OnAnchorClickListener {
        /**
         * 锚点的点击事件监听器
         *
         * @param view        当前点击的锚点 View
         * @param viewGroup   锚点的父布局，可以通过 {@link ViewGroup#getChildCount()} & {@link ViewGroup#getChildAt(int)} 遍历所有锚点
         * @param tag         当前点击锚点的 tag。
         *                    当 {@link AnchorImageView#setAnchors(ArrayList, boolean)} 第二个参数为 true 时，tag 为 {@link IAnchor#getTag()} 的返回值；
         *                    当没有使用自定义 tag 时，返回的 tag 为锚点的下标。
         * @param previousTag 上一次点击事件的 tag。你可以用于取消上次锚点的点击状态需求
         */
        void onAnchorClick(View view, ViewGroup viewGroup, Object tag, Object previousTag);
    }

}
