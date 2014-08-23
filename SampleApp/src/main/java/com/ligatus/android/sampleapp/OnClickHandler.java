package com.ligatus.android.sampleapp;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Documentation required !
 * <p/>
 * @author of last revision $Author: atodorov swagner $
 * @version $Revision: 1.1 $ $Date: 2011/03/16 14:39:45 18.12.13 15:47 $
 */
public class OnClickHandler implements View.OnClickListener
{
   private static final String TAG = "Ligatus OnClickHandler";

   private HelloAndroidActivity activity;

   private int aPlacementId;

   public OnClickHandler(final HelloAndroidActivity aActivity)
   {
      activity = aActivity;
   }

   @Override
   public void onClick(final View aView)
   {
      final EditText editText = (EditText)activity.findViewById(R.id.edit_message);
      final String placementIdString = editText.getText().toString();
      try
      {

         final int newPlacementId = Integer.valueOf(placementIdString);

         Log.e(TAG, " placementID: " + newPlacementId);

         activity.createLigAdView(newPlacementId);
        // final ViewGroup viewGroup = (ViewGroup)activity.findViewById(R.id.ligAdView);
        // viewGroup.invalidate();

      }
      catch(NumberFormatException e)
      {
         Log.e(TAG, "onClick NumberFormatException: " + e);
      }


      /*final Intent intent = new Intent(activity, LigAdBrowser.class);
      activity.startActivity(intent);*/
   }
}
