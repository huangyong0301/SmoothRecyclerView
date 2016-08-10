package com.huangyong.smoothrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter {

        protected List<String> dataSource = new ArrayList<>();
        protected Context mContext;
        protected LayoutInflater inflater;
        protected int itemResource = R.layout.smooth_item;
        protected int scrollDistanceUnit;

        public Adapter(Context context) {
            this.mContext = context;
            this.inflater = LayoutInflater.from(context);
            for (int i = 0; i < 20; i++) {
                dataSource.add(i + "");
            }
            SmoothRecyclerView.sTotalPage =  (dataSource.size() + SmoothRecyclerView.sPageSize - 1) / SmoothRecyclerView.sPageSize;
        }

        @Override
        public int getItemCount() {
            if (dataSource == null) {
                return 0;
            }
            return ((dataSource.size() - 1) / SmoothRecyclerView.sPageSize + 1) * SmoothRecyclerView.sPageSize;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(itemResource, parent, false);
            ViewHolder vh = new ViewHolder(generaLayoutParam(view));
            return vh;
        }

        public View generaLayoutParam(View v){
            RecyclerView.LayoutParams parmas = (RecyclerView.LayoutParams)v.getLayoutParams();
            parmas.width = (int)SmoothRecyclerView.sItemWidth;
            parmas.leftMargin = (int)SmoothRecyclerView.sItemMargin;
            parmas.rightMargin = (int)SmoothRecyclerView.sItemMargin;
//            v.setLayoutParams(parmas);
            v.requestLayout();
            return v;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
            vh.tv.setText(dataSource.get(position));
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tv = (TextView) itemView;
            }
        }
    }