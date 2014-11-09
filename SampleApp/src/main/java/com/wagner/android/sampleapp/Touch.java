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

/**
 *
 <FrameLayout
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:layout_width="fill_parent"
 android:layout_height="fill_parent" >
 <ImageView android:id="@+id/imageView"
 android:layout_width="fill_parent"
 android:layout_height="fill_parent"
 android:src="@drawable/nature"
 android:scaleType="matrix" >
 </ImageView>
 </FrameLayout>
 */


public class Touch extends Activity implements OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {
    private static final String TAG = "Touch";

    // These matrices will be used to move and zoom image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        TextView textView = (TextView) findViewById(R.id.textView2);


        textView.setText("test TEXT BLAB BALB BLAdddddd!!!!!!");

        ImageView view = (ImageView) findViewById(R.id.imageView);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER); // make the image fit to the center.

    /*    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        viewGroup.setOnTouchListener(this);
      */
        final View mainView  = findViewById(android.R.id.content);


        mainView.setOnTouchListener(this);
//        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.textView2);
    //   view.setOnTouchListener(this);
     //   textView.setOnTouchListener(this);
}

    float initDist = 1f;

    public boolean onTouch(View aView, MotionEvent anEvent){
                 /*
        *
        mainView.setPivotX(pivot.x);
        mainView.setPivotY(pivot.y);
        mainView.setScaleX(scaleX);
        mainView.setScaleY(scaleY);
        */
        float scale;
        switch (anEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN: //first finger down only
                //savedMatrix.set(matrix);        //overwrite
                start.set(anEvent.getX(), anEvent.getY());    //getting position
               // aView.setPivotX(start.x);     //setzt sofort den punkt der view
               // aView.setPivotY(start.y);   anEvent.getX()   anEvent.getY()


                Log.d(TAG, "mode=DRAG" );
                mode = DRAG;
                break;
            case MotionEvent.ACTION_UP: //first finger lifted
            case MotionEvent.ACTION_POINTER_UP: //second finger lifted
                mode = NONE;
                Log.d(TAG, "mode=NONE" );
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //second finger down
                initDist = spacing(anEvent);

                //oldDist = spacing(anEvent); // calculates the distance between two points where user touched.
                //Log.d(TAG, "oldDist=" + oldDist);
                // minimal distance between both the fingers
                //if (oldDist > 5f) {
                   // savedMatrix.set(matrix); //overwrite current matrix ()
                    midPoint(mid, anEvent); // sets the mid-point of the straight line between two points where user touched.
                    mode = ZOOM;
                   // Log.d(TAG, "mode=ZOOM" );
               // }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                { //movement of first finger
                   // matrix.set(savedMatrix);
                    if (aView.getLeft() >= -392)
                    {
                        //matrix.postTranslate(anEvent.getX() - start.x, anEvent.getY() - start.y);
                        //aView.setPivotX(anEvent.getX());
                        //jöaView.setPivotY(anEvent.getY());
                        float newX = anEvent.getX();
                        float newY = anEvent.getY();
                        Log.d(TAG, "mode=DRAG:newX:"+newX +" VIEW-TRANSLATION:"+ aView.getTranslationX());
                        Log.d(TAG, "mode=DRAG:newY:"+newY +" VIEW-TRANSLATION:"+ aView.getTranslationY());

                        float latency = 10f;

                        float distanceX = (start.x-newX)/latency;
                        float distanceY = (start.y-newY)/latency;

                        aView.setTranslationX(aView.getTranslationX() - distanceX);
                        aView.setTranslationY(aView.getTranslationY() - distanceY);

                        //aView.setPivotY(newY);
                    }
                }
                else if (mode == ZOOM) { //pinch zooming
                    //float newDist = spacing(anEvent);
                    float newDist = spacing(anEvent);

                    float factor = newDist/ initDist ;

                    //float x = anEvent.getX(0) - anEvent.getX(1);
                    //float y = anEvent.getY(0) - anEvent.getY(1);
                    Log.d(TAG, "initDist = " + initDist+" newDist = " +newDist +" Pointer1:"+ anEvent.getX(0) +" Pointer2:"+anEvent.getX(1));
                    Log.d(TAG, "Factor Zoom = "+ (float)factor);

                    //if (factor > 5d) {
                       // matrix.set(savedMatrix);
                        //scale = newDist/oldDist; //thinking I need to play around with this value to limit it**
                       // matrix.postScale(factor, factor, mid.x, mid.y);
                        //Log.d(TAG, "scale=" + scale);

                    ViewGroup.LayoutParams layoutParams = aView.getLayoutParams();
                    layoutParams
                        aView.setScaleX((float) factor);
                        aView.setScaleY((float)factor);
                                      aView.invalidate();
                   // }
                }
                break;
        }


        return true;
    }

    public boolean onTouchTwo(View v, MotionEvent event) {

        ImageView view = (ImageView) v;
        // make the image scalable as a matrix
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN: //first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG" );
                mode = DRAG;
                break;
            case MotionEvent.ACTION_UP: //first finger lifted
            case MotionEvent.ACTION_POINTER_UP: //second finger lifted
                mode = NONE;
                Log.d(TAG, "mode=NONE" );
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //second finger down
                oldDist = spacing(event); // calculates the distance between two points where user touched.
                Log.d(TAG, "oldDist=" + oldDist);
                // minimal distance between both the fingers
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event); // sets the mid-point of the straight line between two points where user touched.
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM" );
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                { //movement of first finger
                    matrix.set(savedMatrix);
                    if (view.getLeft() >= -392)
                    {
                        matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                    }
                }
                else if (mode == ZOOM) { //pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    Log.d(TAG, " Pointer1:"+ event.getX(0) +" Pointer2:"+event.getX(1));

                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist/oldDist; //thinking I need to play around with this value to limit it**
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        // Perform the transformation
        view.setImageMatrix(matrix);

        return true; // indicate event was handled
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    private double distBetween(MotionEvent event){
        return Math.sqrt(Math.pow(event.getX(0) - event.getX(1),2d) + Math.pow(event.getY(1) - event.getY(0),2d));

    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scaleFactor *= detector.getScaleFactor();  // class variable of type float
        if (scaleFactor > 5) scaleFactor = 5;      // some limitations
        if (scaleFactor < 1) scaleFactor = 1;
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        setScaleX(scaleFactor); setScaleY(scaleFactor);     }
}