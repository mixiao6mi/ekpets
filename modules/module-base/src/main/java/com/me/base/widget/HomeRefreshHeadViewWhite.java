package com.me.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.me.base.R;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;


/**
 * 白色背景下使用的下拉刷新
 */
@SuppressLint("RestrictedApi")
public class HomeRefreshHeadViewWhite extends LinearLayout implements RefreshHeader {

    private LottieAnimationView lottieAnimationView;
    private TextView tvPullDown;
    private RefreshKernel mRefreshKernel;
    private Context mContext;

    public HomeRefreshHeadViewWhite(Context context) {
        super(context);
        mContext = context;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        View view = View.inflate(context, R.layout.home_white_pull_refresh_layout, this);
        lottieAnimationView = view.findViewById(R.id.lottieView);
        tvPullDown = view.findViewById(R.id.tv_pull_down);
    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }


    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                tvPullDown.setVisibility(VISIBLE);
                lottieAnimationView.cancelAnimation();
                break;
            case Refreshing:
                break;
            case ReleaseToRefresh:
                //即将释放

                break;
            case RefreshReleased:
                lottieAnimationView.playAnimation();
                tvPullDown.setVisibility(INVISIBLE);
                break;

            case RefreshFinish:
                lottieAnimationView.pauseAnimation();
                break;

        }
    }
}
