package com.wagner.android.sampleapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class Touch extends Activity implements OnTouchListener {
    private static final String TAG = "Touch";

    // These matrices will be used to move and zoom image

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    View view1;
    View view2;

    // Remember some things for zooming
    PointF start = new PointF();

    View mainView;
    private float maxScale = 4f;
    private float minScale = 0.1f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("test TEXT BLAB BALB BLAdddddd!!!!!!");
        view1 = textView;

        TextView textView2 = (TextView) findViewById(R.id.textView2_2);
        textView2.setText("not ZOOMABLE!!!");

        ImageView view = (ImageView) findViewById(R.id.imageView);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER); // make the image fit to the center.
        view2= view;

        mainView  = findViewById(android.R.id.content);

        Log.d(TAG, "first call newDist:"+newDist+" into initDist:"+initDist);
        initDist=1;
        newDist=1;

        View parent = (View)mainView.getParent();
        parent.setOnTouchListener(this);
}

    float initDist;

    @Override
    public boolean onTouch(View zoomView, MotionEvent anEvent){
        float scale;


        switch (anEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN: //first finger down only
                //savedMatrix.set(matrix);        //overwrite
                Log.d(TAG, "mainViewScaleX:"+mainView.getScaleX());
                start.set(anEvent.getX(), anEvent.getY());    //getting position

                Log.d(TAG, "mode=DRAG" );
                mode = DRAG;
                break;
            case MotionEvent.ACTION_UP: //first finger lifted
                break;
            case MotionEvent.ACTION_POINTER_UP: //second finger lifted
                Log.d(TAG, "write newDist:"+newDist+" into initDist:"+initDist);
                if(newDist!=1)
                initDist = newDist;
                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //second finger down
                initDist = spacing(anEvent);
                    mode = ZOOM;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                { //movement of first finger
                    if (mainView.getLeft() >= -392)
                    {
                        float newX = anEvent.getX();
                        float newY = anEvent.getY();
                        Log.d(TAG, "mode=DRAG:newX:"+newX +" VIEW-TRANSLATION:"+ zoomView.getTranslationX());
                        Log.d(TAG, "mode=DRAG:newY:"+newY +" VIEW-TRANSLATION:"+ zoomView.getTranslationY());

                        float latency = 10f;

                        float distanceX = (start.x-newX)/latency;
                        float distanceY = (start.y-newY)/latency;

                        //mainView.setTranslationX(mainView.getTranslationX() - distanceX);
                        //mainView.setTranslationY(mainView.getTranslationY() - distanceY);
                        view1.setTranslationX(mainView.getTranslationX() - distanceX);
                        view1.setTranslationY(mainView.getTranslationY() - distanceY);
                        view2.setTranslationX(mainView.getTranslationX() - distanceX);
                        view2.setTranslationY(mainView.getTranslationY() - distanceY);
                    }
                }
                else if (mode == ZOOM) { //pinch zooming
                    //float newDist = spacing(anEvent);
                    float newDist = spacing(anEvent);

                    if(newDist>initDist+5f || newDist<initDist-5f){

                    float factor = newDist/ initDist ;
                    Log.d(TAG, "*******ViewSCALE = "+mainView.getScaleX());
                    Log.d(TAG, "*******factor = "+ factor);

                        if(newDist > initDist && scaleFactor < maxScale)//pinch open --> zoom in
                    {
                        factor = factor /10; //latency
                        scaleFactor = scaleFactor + factor;
                        Log.d(TAG, "*******scaleFactor = "+ scaleFactor);
                    }
                    if(newDist < initDist && scaleFactor>minScale && factor < scaleFactor)//pinch close --> zoom out
                    {
                        factor = factor /2; //latency
                        scaleFactor = scaleFactor - factor;
                    }
                   // scaleFactor = scaleFactor; // make scale slower
                    //mainView.setScaleX(scaleFactor);
                    //mainView.setScaleY(scaleFactor);
                        view1.setScaleX(scaleFactor);
                        view1.setScaleY(scaleFactor);
                        view2.setScaleX(scaleFactor);
                        view2.setScaleY(scaleFactor);

                    }
                }
                break;
        }


        return true;
    }

    float scaleFactor=1;
    float newDist;

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }


    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private PointF spacingPoint(MotionEvent event) {
        PointF f = new PointF();
        f.x = event.getX(0) - event.getX(1);
        f.y = event.getY(0) - event.getY(1);
        return f;
    }

    /**
     * zooming is done from here
     */
    public void zoom(Float scaleX, Float scaleY, PointF pivot) {
        mainView.setPivotX(pivot.x);
        mainView.setPivotY(pivot.y);
        mainView.setScaleX(scaleX);
        mainView.setScaleY(scaleY);
    }

}