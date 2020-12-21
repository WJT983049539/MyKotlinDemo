package com.rcdz.mykotlindemo.view.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rcdz.mykotlindemo.R;


/**
 * 昵称修改dialog
 *
 * @author yandaocheng <br/>
 * 弹窗温馨提示
 * 2018-06-21
 * 修改者，修改日期，修改内容
 */
public class PuTongDialog extends Dialog {
    private DialogConfirmClick mLisetner;
    private Context mContext;
    private boolean cancelShow = true;//是否显示取消按钮
    private TextView dialog_content;
    private String content = "";


    public PuTongDialog(Context context, String content, DialogConfirmClick lisetner) {
        super(context);
        this.mContext = context;
        this.mLisetner = lisetner;
        this.content=content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_putong);
        initView();
    }

    private void initView() {
        dialog_content=findViewById(R.id.dialog_content);
        dialog_content.setText(content);
        findViewById(R.id.button_sure).setOnClickListener(new View.OnClickListener() {  //确定
            @Override
            public void onClick(View v) {
                if(mLisetner!=null){
                    mLisetner.ConfirmClick();
                }
            }
        });
        findViewById(R.id.button_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLisetner!=null){
                    mLisetner.NoConfirmClick();
                    cancel();
                }
            }
        });


    }
    public void show2(){
        //隐藏虚拟按键
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

    public interface DialogConfirmClick {
        void ConfirmClick();
        void NoConfirmClick();
    }
}
