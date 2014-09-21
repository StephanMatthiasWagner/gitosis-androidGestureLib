package com.wagner.android.gesturelib;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * @author : Stephan Wagner
 * Date: 25.08.14
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class DummyLibView {

    private static final String TAG = "DummyLibView";

    private static final String SAVED_INSTANCE_SOME_KEY = "SOME_Dummy_KEY";

    private Integer savedInstance;

    private ViewGroup viewGroup;

    /**
     * The default constructor.
     * @param aViewGroup the linearLayout.
     */
    public DummyLibView(final ViewGroup aViewGroup)
    {
        Log.d(TAG, "call constructor");
        viewGroup = aViewGroup;
    }

    public void addDummyTextView(final int below_id){
        final TextView textView = new TextView(viewGroup.getContext());
        textView.setText("This text view was added to the context by DummyLib: DummyLibTextView");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, below_id);
        textView.setLayoutParams(params);

        viewGroup.addView(textView);
    }


    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this
     * Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is
     * null.</b>
     */
    /*@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null && savedInstanceState.containsKey(SAVED_INSTANCE_SOME_KEY))
        {
            savedInstance = savedInstanceState.getInt(SAVED_INSTANCE_SOME_KEY);
        }

        LinearLayout linearLayout = new LinearLayout(this);
        TextView textView = new TextView(this);
        textView.setText("");
    }
      */

}
