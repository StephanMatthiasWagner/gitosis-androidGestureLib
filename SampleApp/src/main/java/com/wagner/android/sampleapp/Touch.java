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


public class Touch extends Activity implements OnTouchListener {
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
    //PointF mid = new PointF();
    float oldDist = 0f;
    GestureDecoder gestureDecoder;
    ScaleGestureDetector scaleGestureDetector;
    View mainView;

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
        mainView  = findViewById(android.R.id.content);

        Log.d(TAG, "first call newDist:"+newDist+" into initDist:"+initDist);
        initDist=1;
        newDist=1;

       // mainView.setOnTouchListener(this);
      //  mainView.postInvalidate();


        //gestureDecoder = new GestureDecoder(mainView);

         //scaleGestureDetector = new ScaleGestureDetector(this, gestureDecoder);
//        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.textView2);
       View parent = (View)mainView.getParent();
        parent.setOnTouchListener(this);
     //   textView.setOnTouchListener(this);
}

    float initDist;

    @Override
    public boolean onTouch(View zoomView, MotionEvent anEvent){
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
                Log.d(TAG, "mainViewScaleX:"+mainView.getScaleX());
                start.set(anEvent.getX(), anEvent.getY());    //getting position
               // aView.setPivotX(start.x);     //setzt sofort den punkt der view
               // aView.setPivotY(start.y);   anEvent.getX()   anEvent.getY()


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
                Log.d(TAG, "mainViewScaleX:"+mainView.getScaleX());
                //oldDist = spacing(anEvent); // calculates the distance between two points where user touched.
                //Log.d(TAG, "oldDist=" + oldDist);
                // minimal distance between both the fingers
                //if (oldDist > 5f) {
                   // savedMatrix.set(matrix); //overwrite current matrix ()
                  //  midPoint(mid, anEvent); // sets the mid-point of the straight line between two points where user touched.
                    mode = ZOOM;
                   // Log.d(TAG, "mode=ZOOM" );
               // }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                { //movement of first finger
                   // matrix.set(savedMatrix);
                    if (mainView.getLeft() >= -392)
                    {
                        //matrix.postTranslate(anEvent.getX() - start.x, anEvent.getY() - start.y);
                        //aView.setPivotX(anEvent.getX());
                        //jöaView.setPivotY(anEvent.getY());
                        float newX = anEvent.getX();
                        float newY = anEvent.getY();
                        Log.d(TAG, "mode=DRAG:newX:"+newX +" VIEW-TRANSLATION:"+ zoomView.getTranslationX());
                        Log.d(TAG, "mode=DRAG:newY:"+newY +" VIEW-TRANSLATION:"+ zoomView.getTranslationY());

                        float latency = 10f;

                        float distanceX = (start.x-newX)/latency;
                        float distanceY = (start.y-newY)/latency;

                        mainView.setTranslationX(mainView.getTranslationX() - distanceX);
                        mainView.setTranslationY(mainView.getTranslationY() - distanceY);

                        //aView.setPivotY(newY);
                    }
                }
                else if (mode == ZOOM) { //pinch zooming
                    //float newDist = spacing(anEvent);
                    float newDist = spacing(anEvent);
                    Log.d(TAG, "newDist = "+ newDist);


                    if(newDist>initDist+5f || newDist<initDist-5f){

                    float factor = newDist/ initDist ;

                    if(newDist > initDist)//pinch open --> zoom in
                    {
                       scaleFactor = scaleFactor + factor;
                    }
                    if(newDist < initDist)//pinch close --> zoom out
                    {
                       scaleFactor = scaleFactor - factor;
                    }
                    scaleFactor = scaleFactor/3; // make scale slower

                    //float x = anEvent.getX(0) - anEvent.getX(1);
                    //float y = anEvent.getY(0) - anEvent.getY(
                    Log.d(TAG, "mode=zoom initDist = " + initDist+" newDist = " +newDist +" Pointer1:"+ anEvent.getX(0) +" Pointer2:"+anEvent.getX(1));
                    Log.d(TAG, "mode=zoom x=" + mainView.getX() +" y=" + mainView.getY());

                    Log.d(TAG, "Factor Zoom = "+ (float)factor);

                    //if (factor > 5d) {
                       // matrix.set(savedMatrix);
                        //scale = newDist/oldDist; //thinking I need to play around with this value to limit it**
                       // matrix.postScale(factor, factor, mid.x, mid.y);
                        //Log.d(TAG, "scale=" + scale);

                    //ViewGroup.LayoutParams layoutParams = zoomView.getLayoutParams();

                    //zoomView.setFactor(factor);
                    //zoomView.setOrientation(start);

                    /*aView.setScaleX((float) factor);
                    aView.setScaleY((float)factor);
                                      aView.invalidate();
*/                   // }
                   // scaleFactor = factor;
                   // gestureDecoder.scaleFactor=scaleFactor;
                   // scaleGestureDetector.onTouchEvent(anEvent);
                    //mainView.setPivotX(start.x);
                    //mainView.setPivotY(start.y);
                    mainView.setScaleX(scaleFactor);
                    mainView.setScaleY(scaleFactor);
                    }
                }
                break;
        }


        return true;
    }

    PointF oldDistPoint = new PointF();
    float scaleFactor;
    View scaleView;
    float newDist;

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




 /*   public boolean onTouchTwo(View v, MotionEvent event) {

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
    }                  */


   // @Override
  /*  public boolean onTouchThree(View scaleView, MotionEvent event) {
        Log.d(TAG, "mode=DRAG");
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG");
                mode = DRAG;

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                oldDistPoint = spacingPoint(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                System.out.println("current time :" + System.currentTimeMillis());
                break;// return !gestureDetector.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "mode=NONE");
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {

                } else if (mode == ZOOM) {
                    PointF newDist = spacingPoint(event);
                    float newD = spacing(event);
                    Log.e(TAG, "newDist=" + newDist);
                    float[] old = new float[9];
                    float[] newm = new float[9];
                    Log.e(TAG, "x=" + old[0] + ":&:" + old[2]);
                    Log.e(TAG, "y=" + old[4] + ":&:" + old[5]);
                    float scale = newD / oldDist;
                    float scalex = newDist.x / oldDistPoint.x;
                    float scaley = newDist.y / oldDistPoint.y;
                    //zoom(scale, scale, start);
                }
                break;
        }
        return true;
    }                    */

}