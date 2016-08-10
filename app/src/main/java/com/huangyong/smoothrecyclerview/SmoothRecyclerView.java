package com.huangyong.smoothrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by huangyong on 2016/8/9.
 */
public class SmoothRecyclerView extends RecyclerView {
    public static float sWidth;
    public static float sHeight;
    public static float sItemWidth;
    public static float sItemMargin = 5;
    public static int sPageSize = 4;
    public static int sTotalPage;
    /**
     * 触发补间距离的最小值；只有当手动滑动超过这个值才会触发补间
     */
    public static float sTweenMinDistanceThreshold = 20;
    public float mX;

    public SmoothRecyclerView(Context context) {
        this(context, null);
    }

    public SmoothRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmoothRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.addOnScrollListener(new ScrollListner());
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        return false;
    }

    class ScrollListner extends RecyclerView.OnScrollListener {
        public int mState;
        public int mDraggingX;     //已经滑动的距离

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mState == RecyclerView.SCROLL_STATE_IDLE) {
                    mDraggingX = 0;
                    //停止滑动
                } else if (mState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //正在滑动
                    smooth(mDraggingX);
                } else if (mState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //正在补间滑动
                    mDraggingX = 0;
                }
            } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                if (mState == RecyclerView.SCROLL_STATE_IDLE) {
                    mDraggingX = 0;
                    //停止滑动
                } else if (mState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //正在滑动

                } else if (mState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //正在补间滑动
                    mDraggingX = 0;
                }
            } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                if (mState == RecyclerView.SCROLL_STATE_IDLE) {
                    mDraggingX = 0;
                    //停止滑动
                } else if (mState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //正在滑动

                } else if (mState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //正在补间滑动
                    mDraggingX = 0;
                }
            }
            mState = newState;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mX += dx;
            if (mState == RecyclerView.SCROLL_STATE_IDLE) {
                //停止滑动
            } else if (mState == RecyclerView.SCROLL_STATE_DRAGGING) {
                //正在滑动
                mDraggingX += dx;
            } else if (mState == RecyclerView.SCROLL_STATE_SETTLING) {
                //正在补间滑动
            }
        }
    }

    public void smooth(float draggingX) {
        int currentPage = (int) ((mX - 1) / sWidth);
        if (Math.abs(draggingX) > sTweenMinDistanceThreshold) {
            if (draggingX > 0) {
                if (currentPage == sTotalPage - 1) {
                    smoothScrollToPosition(sTotalPage * sPageSize);
                } else {
                    smoothScrollToPosition((currentPage + 2) * sPageSize - 1);
                }
            } else {
                currentPage++;
                if (currentPage == 0) {
                    smoothScrollToPosition(0);
                } else {
                    smoothScrollToPosition((currentPage - 1) * sPageSize);
                }

            }
        } else if (draggingX == 0) {

        } else {
            smoothScrollToPosition((currentPage) * sPageSize);
        }
    }

}
