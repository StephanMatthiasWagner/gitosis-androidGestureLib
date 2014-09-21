package com.wagner.android.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import com.wagner.android.gesturelib.DummyLib;

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

      final Button button = (Button)findViewById(R.id.refreshButton);
      button.setText("Refresh");
      button.setOnClickListener(new OnClickHandler(this));

      final TextView textView = (TextView) findViewById(R.id.textView);
      textView.setText(savedInstance);


      Log.d(TAG, "onCreate called" );

      final DummyLib gestureLib = new DummyLib();
      gestureLib.callDummyLib("This is the call Message for GestureLib");
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

