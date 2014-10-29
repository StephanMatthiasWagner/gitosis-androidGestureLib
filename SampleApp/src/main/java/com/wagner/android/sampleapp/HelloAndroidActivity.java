package com.wagner.android.sampleapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wagner.android.gesturelib.DummyLib;
import com.wagner.android.gesturelib.DummyLibView;

/**
 * This is the HelloAndoridActivity for presenting first Android App Content.
 * <p/>
 * @author of last revision $Author: swagner $
 * @version $Revision: 1.0 $ $Date: 2014/08/16 $
 */
public class HelloAndroidActivity extends Activity
{

   private static final String TAG = "HelloAndroidActivity";

   private static final String SAVED_INSTANCE_SOME_KEY = "SOME_KEY";

    /**
     * The Saved Instance string.
     */
   private String savedInstance;

   public HelloAndroidActivity()
   {
      Log.d(TAG, "call constructor");

   }

   /**
    * Called when the activity is first created.
    * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this
    * Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is
    * null.</b>
    */
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      if(savedInstanceState!=null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY))
      {
         savedInstance = savedInstanceState.getString(SAVED_INSTANCE_SOME_KEY);
      }

      setContentView(R.layout.activity_main);

       LayoutInflater inflater = getLayoutInflater();
       final View l  = (View) findViewById(android.R.id.content);

      final Button button = (Button)findViewById(R.id.refreshButton);
      button.setText("Refresh");
       button.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               zoom(l, 2f, 2f, new PointF(0, 0));
           }
       });

      final TextView textView = (TextView) findViewById(R.id.textView);
      textView.setText(savedInstance);

       final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
               .findViewById(android.R.id.content)).getChildAt(0);


      final DummyLibView dummyLibView = new DummyLibView();
       dummyLibView.addDummyTextView(viewGroup, R.id.refreshButton);

      Log.d(TAG, "onCreate called" );

      final DummyLib gestureLib = new DummyLib();
      gestureLib.callDummyLib("This is the call Message for GestureLib");


   }


    /** zooming is done from here */
    public void zoom(View layout, Float scaleX,Float scaleY,PointF pivot){
        Log.d(TAG, "zoom called" );
        layout.setPivotX(pivot.x);
        layout.setPivotY(pivot.y);
        layout.setScaleX(scaleX);
        layout.setScaleY(scaleY);
    }

    public void setTextMessage(final String aTextMessage)
    {
       savedInstance = aTextMessage;

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(savedInstance);
        textView.invalidate();
    }

    @Override
   protected void onSaveInstanceState(Bundle outState)
   {
      super.onSaveInstanceState(outState);
      Log.d(TAG, "onSaveInstanceState" + savedInstance);
      outState.putString(SAVED_INSTANCE_SOME_KEY, savedInstance);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}

