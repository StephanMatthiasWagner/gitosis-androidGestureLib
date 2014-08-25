package com.wagner.android.gesturelib;

import android.text.Editable;

/**
 * Created by Little on 23.08.2014.
 */
public class DummyLib {

    private static final String dummyString = "someDummyString";

    public String callDummyLib(final String message){
        return "DummyLib Got the following message:" + message;

    }

   public Editable callDummyLib(final Editable aEditableMessage){
      aEditableMessage.append(", is the Message that DummyLib proceded");
      return aEditableMessage;

   }
}
