package com.example.singletouchtest;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private DragView mDragView;//添加材料视图的地方
    private ImageView image;//放材料的图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDragView = (DragView)findViewById(R.id.dragview);
        //relativeLayoutNewMaterialView.bringToFront();


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View my_self_view = LayoutInflater.from(MainActivity.this).inflate(R.layout.my_self_view, null);
                image = (ImageView)my_self_view.findViewById(R.id.imageview);
                image.setImageResource(R.drawable.material);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                mDragView.addDragView(my_self_view, 0,400,380,760, false,false);
            }
        });
    }
}
