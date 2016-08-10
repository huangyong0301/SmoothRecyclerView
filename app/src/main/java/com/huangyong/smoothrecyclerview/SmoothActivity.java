package com.huangyong.smoothrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.view.WindowManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by huangyong on 2016/8/9.
 */
@EActivity(R.layout.activity_smooth)
public class SmoothActivity extends AppCompatActivity {
    @ViewById
    SmoothRecyclerView mRecyclerView;
    @AfterViews
    public void afterInject(){
        measure();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
      //  mRecyclerView.mX = mRecyclerView.sWidth;
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new Adapter(this));
    }



    public void measure(){
        WindowManager wm = this.getWindowManager();
        SmoothRecyclerView.sWidth = wm.getDefaultDisplay().getWidth();
        SmoothRecyclerView.sHeight = wm.getDefaultDisplay().getHeight();
        SmoothRecyclerView.sItemWidth =  (SmoothRecyclerView.sWidth - SmoothRecyclerView.sPageSize * SmoothRecyclerView.sItemMargin * 2) / SmoothRecyclerView.sPageSize;
    }
}
