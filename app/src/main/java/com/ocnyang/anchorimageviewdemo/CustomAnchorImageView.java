package com.ocnyang.anchorimageviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocnyang.anchorimageview.AnchorImageView;
import com.ocnyang.anchorimageview.IAnchor;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time: 2019-10-29 11:20.
 *    *     *   *         *   * *       Email address: ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site: www.ocnyang.com
 *******************************************************************/

public class CustomAnchorImageView extends AnchorImageView {
    public CustomAnchorImageView(Context context) {
        super(context);
    }

    public CustomAnchorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAnchorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void addOneAnchorView(int width, int height, IAnchor iAnchor, Object tag, OnClickListener onClickListener) {
        double width_scale = iAnchor.getWidthScale();
        double height_scale = iAnchor.getHeightScale();

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_custom_point, this, false);
        TextView textView =  view.findViewById(R.id.tvPoint);

        if (tag != null) {
            textView.setTag(tag);
        }

        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

        layoutParams.leftMargin = (int) ((width * width_scale) - 20);
        layoutParams.topMargin = (int) ((height * height_scale) - 20);

        textView.setOnClickListener(onClickListener);
        textView.setText(String.valueOf(tag));
        textView.setAlpha(0);

        layouPoints.addView(view, layoutParams);
    }
}
