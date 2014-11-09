package com.wagner.android.sampleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Ligatus
 * Date: 04.11.14
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class ZoomActivity extends Activity {
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_trd);
        /*View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.acivity_trd, null, false);

        v.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.FILL_PARENT, android.widget.LinearLayout.LayoutParams.FILL_PARENT));
          */

        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        ImageView view = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("test TEXT BLAB BALB BLA!!!!!!");

        Zoom zoom = new Zoom(this);
        zoom.addView(viewGroup);
        //zoom.addView(textView);
    }


}
