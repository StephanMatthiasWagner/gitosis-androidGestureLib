package com.wagner.android.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
//import com.ligatus.android.adframework.LigAdView;

public class HelloAndroidActivity extends Activity
{

   private static final String TAG = "LigTestApp HelloAndroidActivity";
   //44339
   private int placementId = 20326;

   private static String PLACEMENT_BUNDLE_KEY = "PLACEMENT_ID";

   /**
    * testplacement: http://d.ligatus.com/?ids=20326 mobil: http://d.ligatus.com/?ids=44339 [21.11.2013 11:26:39]
    * Ligatus/Dirk Malorny: franzosenplacement: http://a.ligatus.com/?ids=47893
    */

   public HelloAndroidActivity()
   {
      Log.d(TAG, "call constructor" + placementId);

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

      if(savedInstanceState!=null && savedInstanceState.containsKey(PLACEMENT_BUNDLE_KEY))
      {
         placementId = savedInstanceState.getInt(PLACEMENT_BUNDLE_KEY);
      }

      setContentView(R.layout.activity_main);

      final Button button = (Button)findViewById(R.id.refreshButton);
      button.setText("Refresh");
      button.setOnClickListener(new OnClickHandler(this));
      Log.d(TAG, "onCreate called" + placementId);

      createLigAdView(placementId);
   }

   protected void createLigAdView(int aPlacementId)
   {
      placementId = aPlacementId;

      //final LigAdView ligAdView = (LigAdView)findViewById(R.id.ligAdView);
      //ligAdView.fillLigAdView(this, placementId);
   }

   @Override
   protected void onSaveInstanceState(Bundle outState)
   {
      super.onSaveInstanceState(outState);
      Log.d(TAG, "onSaveInstanceState" + placementId);
      outState.putInt(PLACEMENT_BUNDLE_KEY, placementId);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}

