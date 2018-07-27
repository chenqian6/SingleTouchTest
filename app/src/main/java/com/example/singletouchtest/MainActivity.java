package com.example.singletouchtest;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidParameterException;

public class MainActivity extends AppCompatActivity {

    private DragView mDragView;//添加材料视图的地方
    private ImageView image;//放材料的图片

    final private String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Material" + File.separator;
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1);//获取读写权限

        mDragView = (DragView)findViewById(R.id.dragview);
        //relativeLayoutNewMaterialView.bringToFront();

        Button saved = (Button)findViewById(R.id.saved);
        final Button button = (Button)findViewById(R.id.button);
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

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.save_view, null);
                final EditText editText = view.findViewById(R.id.addname);
                Button save = view.findViewById(R.id.save);
                Button cancel = view.findViewById(R.id.cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String path = rootPath + editText.getText().toString() + ".png";
                        //File file = new File(path);
                        getScreenHot(mDragView, path);


                        if (alertDialog != null){
                            alertDialog.dismiss();
                        }
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }


    private void getScreenHot(View v, String filePath){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bitmap = v.getDrawingCache();
        if (bitmap != null){
            try{
                File file = new File(filePath);
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
