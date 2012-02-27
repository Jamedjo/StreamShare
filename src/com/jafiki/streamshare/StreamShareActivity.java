package com.jafiki.streamshare;

import com.jafiki.streamshare.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StreamShareActivity extends Activity {
    /** Called when the activity is first created. */
	TextView UriTextBox;
	Button BtShareText;
	Button BtShareHtml;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);
        
        UriTextBox = (TextView)findViewById(R.id.uri_textbox);

        BtShareHtml = (Button)this.findViewById(R.id.bt_share_html);
	    BtShareHtml.setEnabled(false);
        BtShareHtml.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	String body =  getString(R.string.sharetextprefix) + "<a href='" + UriTextBox.getText() + "'>" + UriTextBox.getText() + "</a>" + getString(R.string.sharetextpostfix);
            	share("text/html",body,true);
            }
          });
        //BtShareHtml.setVisibility(View.GONE);
        
        BtShareText = (Button)this.findViewById(R.id.bt_share_text);
	    BtShareText.setEnabled(false);
        BtShareText.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	String body = getString(R.string.sharetextprefix) + UriTextBox.getText() + getString(R.string.sharetextpostfix);
            	share("text/plain",body,false);
            }
          });
        
        Intent intent = getIntent();
	    // check if this intent is started via custom scheme link
	    if (Intent.ACTION_VIEW.equals(intent.getAction())) {
	      Uri uri = intent.getData();
	      String sURI = uri.toString();
	      UriTextBox.setText(sURI);
	      BtShareText.setEnabled(true);
	      BtShareHtml.setEnabled(true);
	    }
     
    }
    
    private void share(String contentType, String body,Boolean html){
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    	sharingIntent.setType(contentType);
    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.sharesubject));
    	if(html){
        	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));
    	} else {
        	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
    	}
    	startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));    	
    }
}