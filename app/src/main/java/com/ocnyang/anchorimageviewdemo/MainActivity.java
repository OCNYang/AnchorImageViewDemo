package com.ocnyang.anchorimageviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.ocnyang.anchorimageview.Anchor;
import com.ocnyang.anchorimageview.AnchorImageView;
import com.ocnyang.anchorimageviewdemo.bean.DataBean;
import com.ocnyang.anchorimageviewdemo.bean.PartsBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String IMG_URL = "http://image.daishupei.com/parts/image/partImageInfo/Bmw/X/GTRzh5JxssyKsQnQE2zsg4W4JeG2XrFTtbcqNmAGk=";
    private AnchorImageView anchorImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anchorImageView = ((AnchorImageView) findViewById(R.id.anchor_image_view));
        anchorImageView.setAnchorOnClickListener(new AnchorImageView.OnAnchorClickListener() {
            @Override
            public void onAnchorClick(View view, ViewGroup viewGroup, Object tag, Object previousTag) {
                Toast.makeText(MainActivity.this, ((String) view.getTag()),Toast.LENGTH_LONG).show();
            }
        });

        initImageData();
    }

    private void initImageData() {

        int width = 1287;
        int height = 910;
        float scale = (height * 1.0f) / width;

        ArrayList<Anchor> anchors = initAnchors(width, height);

        int layoutWidth = getScreenWidthPixels();
        int layoutHeight = (int) (layoutWidth * scale);

        anchorImageView.setAnchors(anchors, true);
        anchorImageView.setImage(layoutWidth, layoutHeight, IMG_URL);

//        Glide.with(this)
//                .load(IMG_URL)
//                .asBitmap()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                        Log.e("glide", "glide");
//                        int width = resource.getWidth();
//                        int height = resource.getHeight();
//
//                        initAnchors(width, height);
//
//                    }
//                });
    }

    private ArrayList<Anchor> initAnchors(int width, int height) {

        List<PartsBean> partsBeanList = (new Gson().fromJson(readTextFromSDcard(), DataBean.class)).data;

        ArrayList<Anchor> anchorArrayList = new ArrayList<>();

        for (PartsBean partsBean : partsBeanList) {

            if (TextUtils.isEmpty(partsBean.position)) {
                continue;
            }

            String positionArrStr = partsBean.position;
            String[] positionStr = positionArrStr.split("[),(|(|)]");

            for (int i = 0; i < positionStr.length; ) {
                if (TextUtils.isEmpty(positionStr[i])) {
                    i++;
                    continue;
                } else {
                    Anchor anchor = new Anchor();
                    int x = Integer.valueOf(positionStr[i++]);
                    int y = Integer.valueOf(positionStr[i++]);
                    int horizontal = Integer.valueOf(positionStr[i++]);
                    int vertical = Integer.valueOf(positionStr[i++]);

                    double widthScale = (horizontal / 2.0 + x) / width;
                    double heightScale = (vertical / 2.0 + y) / height;
                    anchor.setWidth_scale(widthScale);
                    anchor.setHeight_scale(heightScale);
                    anchor.setTag(partsBean.partRefOnImage);
                    anchorArrayList.add(anchor);
                }
            }
        }

        return anchorArrayList;
    }


    private int getScreenWidthPixels() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    private String readTextFromSDcard() {
        InputStreamReader inputStreamReader;

        try {
            inputStreamReader = new InputStreamReader(getAssets().open("parts.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
