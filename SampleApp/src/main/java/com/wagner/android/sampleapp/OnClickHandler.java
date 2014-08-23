package com.wagner.android.sampleapp;

import android.view.View;
import android.widget.EditText;

/**
 * This is the ClickHandler for controlling Click events.
 * <p/>
 * @author of last revision $Author: swagner $
 * @version $Revision: 1.0 $ $Date: 2014/08/16 $
 */
public class OnClickHandler implements View.OnClickListener
{
   private static final String TAG = "OnClickHandler";

   private HelloAndroidActivity activity;

   public OnClickHandler(final HelloAndroidActivity aActivity)
   {
      activity = aActivity;
   }

   @Override
   public void onClick(final View aView)
   {
      final EditText editText = (EditText)activity.findViewById(R.id.edit_message);
   }
}
