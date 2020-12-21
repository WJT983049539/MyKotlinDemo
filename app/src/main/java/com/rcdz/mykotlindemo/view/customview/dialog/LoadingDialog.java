package com.rcdz.mykotlindemo.view.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.rcdz.mykotlindemo.R;

/**
 * 描述：
 * 版权：苏州美好明天机器人技术有限公司
 */
public class LoadingDialog extends Dialog {

    public interface OnDialogDismissedListener {
        void onDialogDismissed();
    }

    private static final int loading = 0;
    public static final int success = 1;
    public static final int error = 2;
    private ProgressBar loadingProgressbar;
    private ImageView loadingPicSuccess;
    private TextView loadingTextView;
    private int loading_result;
    private String loading_text;
    private static final String DEFAULT_LOADING_TEXT = "加载中...";
    private Context mContext;
    private OnDialogDismissedListener onDialogDismissed;

    public LoadingDialog(Context context) {
        super(context, R.style.myTransparent);
        setContentView(R.layout.laoding_dialog);
        mContext = context;
        initView();
    }

    private LoadingDialog setLoading_result(int loading_result) {
        this.loading_result = loading_result;
        return this;
    }

    private LoadingDialog setLoading_text(String loading_text) {
        this.loading_text = loading_text;
        return this;
    }

    void showLoadingDialog(String text) {
        if (this.isShowing()) {
            Log.i("LoadingDialog", "can not show dialog cause dialog is showing");
            return;
        }

        setLoading_result(loading);
        setLoading_text(text);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
        this.show();

    }

    void hideSuccess(String loading_text) {
        setLoading_result(success);
        setLoading_text(loading_text);
        hideDialog();
    }

    void hideError(String loading_text) {
        setLoading_result(error);
        setLoading_text(loading_text);
        hideDialog();
    }

    void hideNow() {
        dismiss();
    }


    private void hideDialog() {
        hideProgress();
        loadText();
        loadImageAndDismiss();
    }

    public void setOnDialogDismissed(OnDialogDismissedListener onDialogDismissed) {
        this.onDialogDismissed = onDialogDismissed;
    }

    void refreshLoadingText(String text) {
        if (!this.isShowing()) {
            Log.i("LoadingDialog", "can not change test cause dialog is not showing");
            return;
        }
        loadingTextView.setText(text);
    }

    @Override
    public void show() {
        super.show();

        hideImage();
        showProgress();
        loadText();
    }

    private void showProgress() {
        loadingProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        loadingProgressbar.setVisibility(View.GONE);
    }

    private void loadImageAndDismiss() {
        showImage();
        switch (loading_result) {
            case 1:
                //设置渐变
                DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(450).setCrossFadeEnabled(true).build();
                Glide.with(mContext).load(R.drawable.ic_right_green_48x48).
                        diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).into(loadingPicSuccess);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case 2:
                DrawableCrossFadeFactory drawableCrossFadeFactory2 = new DrawableCrossFadeFactory.Builder(450).setCrossFadeEnabled(true).build();
                Glide.with(mContext).load(R.drawable.ic_error_red_48x48)
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory2)).into(loadingPicSuccess);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.onDialogDismissed != null) {
            this.onDialogDismissed.onDialogDismissed();
        }
    }

    private void loadText() {

        switch (loading_result) {
            case 0:
                if (loading_text == null || "".equals(loading_text)) {
                    loading_text = DEFAULT_LOADING_TEXT;
                }
                loadingTextView.setTextAppearance(mContext, R.style.LoadingTextStyle);
                loadingTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                break;
            case 1:
                loadingTextView.setTextAppearance(mContext, R.style.ErrorTextStyle);
                loadingTextView.setTextColor(mContext.getResources().getColor(R.color.green_c));
                break;
            case 2:
                loadingTextView.setTextAppearance(mContext, R.style.ErrorTextStyle);
                loadingTextView.setTextColor(mContext.getResources().getColor(R.color.red_a));
                break;
        }
        loadingTextView.setText(loading_text);
    }

    private void hideImage() {
        loadingPicSuccess.setVisibility(View.GONE);
    }

    private void showImage() {
        loadingPicSuccess.setVisibility(View.VISIBLE);
    }

    private void initView() {
        loadingProgressbar = findViewById(R.id.loading_progressbar);
        loadingPicSuccess = findViewById(R.id.loading_pic_success);
        loadingTextView = (TextView) findViewById(R.id.loading_textView);
    }


    @Override
    public void onBackPressed() {

    }
}
