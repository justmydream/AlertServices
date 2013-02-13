package com.example.alertservice;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainShutDown extends Activity {
MainShutDown self;
Builder d;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shut_down);
        self=this;
    	Builder d = new AlertDialog.Builder(this);
   	 
		d.setMessage("정말 종료하시겠습니까?");
 
		d.setPositiveButton("종료", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) { 
				// process전체 종료 
				finish(); 
			} 
		}); 
		d.setNegativeButton("취소", new DialogInterface.OnClickListener() {
 
			public void onClick(DialogInterface dialog, int which) { 
				
				Intent intent = new Intent(self,Mainpage.class);
				dialog.cancel();
				startActivity(intent);
				self.finish();
			} 			
		
		});
		d.setCancelable(false);
		d.show();
		
        
        
   
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_shut_down, menu);
        return true;
    }    
   
}
