package com.hqs.common.view;

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

/**
 * Created by super on 2017/8/14.
 */

public class QPageView extends RecyclerView{

    private int pageMargin = 50;
    private PagerSnapHelper snapHelper;

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
                outRect.set(0, 0, pageMargin, 200);
            }
        }
    }

    public void setPageMargin(int pageMargin) {
        this.pageMargin = pageMargin;
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
                offset = pageMargin;
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
