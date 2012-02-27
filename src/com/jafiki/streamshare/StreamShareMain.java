package com.jafiki.streamshare;

import com.jafiki.streamshare.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class StreamShareMain extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
  
        TextView textcontent = (TextView)findViewById(R.id.instructions_textbox_c);
        textcontent.setMovementMethod(LinkMovementMethod.getInstance());

    }
}