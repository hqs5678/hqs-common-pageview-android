package com.hqs.common.view.qpageview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by super on 2017/8/14.
 */

public class QPageView extends RecyclerView{

    private int separatorWidth = 50;
    private PagerSnapHelper snapHelper;
    private QAdapter qAdapter;

    public QPageView(Context context) {
        super(context);
        init();
    }

    public QPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.setLayoutManager(linearLayoutManager);
        this.addItemDecoration(new VerticalDecoration());

        this.snapHelper = new QPagerSnapHelper();
        snapHelper.attachToRecyclerView(this);

        this.setBackgroundColor(Color.DKGRAY);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        qAdapter = new QAdapter(adapter);
        super.setAdapter(qAdapter);
    }

    private class QAdapter extends Adapter{

        private Adapter adapter;

        public QAdapter(Adapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
            adapter.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public int getItemViewType(int position) {
            return adapter.getItemViewType(position);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            adapter.setHasStableIds(hasStableIds);
        }

        @Override
        public long getItemId(int position) {
            return adapter.getItemId(position);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            adapter.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(ViewHolder holder) {
            return adapter.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            adapter.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            adapter.onViewDetachedFromWindow(holder);
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            adapter.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            adapter.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder vh = adapter.onCreateViewHolder(parent, viewType);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
            float sh = getResources().getDisplayMetrics().heightPixels;
            float sw = getResources().getDisplayMetrics().widthPixels;
            params.width = (int) sw;
            params.height = (int) sh;

            vh.itemView.setLayoutParams(params);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            adapter.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return adapter.getItemCount();
        }
    }

    private class VerticalDecoration extends ItemDecoration {

        private Paint paint;

        public VerticalDecoration(){
            this.paint = new Paint();
            paint.setColor(Color.TRANSPARENT);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int pos = getChildAdapterPosition(view);
            if (pos < getAdapter().getItemCount() - 1){
                outRect.set(0, 0, separatorWidth, 200);
            }
        }
    }

    public void setSeparatorWidth(int separatorWidth) {
        this.separatorWidth = separatorWidth;
    }

    public void setSeparatorColor(int color) {
        setBackgroundColor(color);
    }

    class QPagerSnapHelper extends PagerSnapHelper {

        private OrientationHelper mHorizontalHelper;

        @Override
        public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                                  @NonNull View targetView) {
            int[] out = new int[2];
            if (layoutManager.canScrollHorizontally()) {
                out[0] = distanceToCenter(layoutManager, targetView,
                        getHorizontalHelper(layoutManager));
            } else {
                out[0] = 0;
            }
            return out;
        }

        private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager,
                                     @NonNull View targetView, OrientationHelper helper) {
            int offset;
            if (getChildAdapterPosition(targetView) == getAdapter().getItemCount() - 1){
                offset = 0;
            }
            else{
                offset = separatorWidth;
            }
            int childCenter = helper.getDecoratedStart(targetView)
                    + ((helper.getDecoratedMeasurement(targetView) - offset) / 2);
            int containerCenter;
            if (layoutManager.getClipToPadding()) {
                containerCenter = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
            } else {
                containerCenter = helper.getEnd() / 2;
            }
            return childCenter - containerCenter;
        }

        private OrientationHelper getHorizontalHelper(
                @NonNull RecyclerView.LayoutManager layoutManager) {
            if (mHorizontalHelper == null) {
                mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
            }
            return mHorizontalHelper;
        }
    }
}
