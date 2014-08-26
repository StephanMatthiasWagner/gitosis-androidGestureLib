package com.wagner.android.sampleapp;


import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.wagner.android.gesturelib.DummyLib;

/**
 * This is the ClickHandler for controlling Click events.
 * <p/>
 *
 * @author of last revision $Author: swagner $
 * @version $Revision: 1.0 $ $Date: 2014/08/16 $
 */
public class OnClickHandler implements View.OnClickListener {
    private static final String TAG = "OnClickHandler";

    /*
     * The Linked Activity that should be clicked handled-
     */
    private HelloAndroidActivity activity;

    /**
     * The Constructor that instantiates OnClickHandler for a given HelloAndroidActivity.
     * @param aActivity
     */
    public OnClickHandler(final HelloAndroidActivity aActivity) {
        activity = aActivity;
    }

    @Override
    public void onClick(final View aView) {

        Log.d(TAG, "call onclick");
        final EditText textInput = (EditText)activity.findViewById(R.id.edit_message);
        activity.setTextMessage(new DummyLib().callDummyLib("Message from SampleApp:" + textInput.getText().toString()));

    }
}
