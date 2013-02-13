package com.example.alertservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class CustDlg extends Activity {

	
	CustDlg self;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_dlg);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED 
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD 
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON 
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        
        self = this;
        ImageButton ok1 = (ImageButton)findViewById(R.id.imageButton1);
        ImageButton cancel1 = (ImageButton)findViewById(R.id.imageButton2);


        ok1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {

		        self.setResult(RESULT_OK);
				self.finish();
				
			}
		});
        
        cancel1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				self.finish();
				
			}
		});
        
    }
}
