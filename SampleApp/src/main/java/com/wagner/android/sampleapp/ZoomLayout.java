package com.wagner.android.sampleapp;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ZoomLayout extends Activity {
    View mainView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
    /*    mainView =(LinearLayout)findViewById(R.id.linearLayout);

        Button buttonZoomOut = (Button)findViewById(R.id.buttonZoomOut);
        Button buttonNormal = (Button)findViewById(R.id.buttonNormal);
        Button buttonZoomIn = (Button)findViewById(R.id.buttonZoomIn);

        buttonZoomOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zoom(0.5f,0.5f,new PointF(0,0));
            }
        });
        buttonNormal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zoom(1f,1f,new PointF(0,0));
            }
        });
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                zoom(2f,2f,new PointF(0,0));
            }
        });*/
    }

    /** zooming is done from here */
    public void zoom(Float scaleX,Float scaleY,PointF pivot){
        mainView.setPivotX(pivot.x);
        mainView.setPivotY(pivot.y);
        mainView.setScaleX(scaleX);
        mainView.setScaleY(scaleY);
    }
}
