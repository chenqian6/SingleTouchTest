package com.example.singletouchtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FunctionView extends PopupWindow implements View.OnClickListener{
    private Context context;
    private View delete, tofront;
    private DragView dragView;
    private MoveLayout moveLayout;
    private View view;

    public FunctionView(Context context, DragView dragView, MoveLayout moveLayout){
        super(context);
        this.context = context;
        this.dragView = dragView;
        this.moveLayout = moveLayout;
        initalize();
    }
    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.material_function, null);
        delete = view.findViewById(R.id.delete);
        tofront = view.findViewById(R.id.tofront);
        delete.setOnClickListener(this);
        tofront.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }
    private void initWindow(){
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        //this.setWidth((int)(d.widthPixels * 0.35));
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();

        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPickPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    public void showAtBottom(View view){
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.delete:
                Toast.makeText(context,"删除素材",Toast.LENGTH_SHORT).show();
                dragView.removeView(moveLayout);
                view.setVisibility(View.GONE);
                break;
            case R.id.tofront:
                Toast.makeText(context,"置顶素材",Toast.LENGTH_SHORT).show();
                moveLayout.bringToFront();
                break;
            default:
        }
    }
}
