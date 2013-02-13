package com.example.alertservice;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class Mainpage extends TabActivity

 {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        
        
        TabHost tabHost = getTabHost();
        
        tabHost.addTab(tabHost.newTabSpec("tag1")
          .setIndicator("긴급",getResources().getDrawable(R.drawable.emergen_icon))
          .setContent(new Intent(this, Firstpage.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tag2")
          .setIndicator("설정",getResources().getDrawable(R.drawable.set_icon))
          .setContent(new Intent(this, MainSetting.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tag3")
          .setIndicator("도움말",getResources().getDrawable(R.drawable.help_icon))
          .setContent(new Intent(this, MainHelp.class)));
        
        Intent i = new Intent(this, MainLocation.class);
        
        tabHost.addTab(tabHost.newTabSpec("tag4")
                .setIndicator("위치",getResources().getDrawable(R.drawable.gps_icon))
                .setContent(i));                
        
       
		tabHost.addTab(tabHost.newTabSpec("tag5")
                .setIndicator("종료",getResources().getDrawable(R.drawable.end_icon))           
                .setContent(new Intent(this, MainShutDown.class)));
             
        tabHost.setCurrentTab(0);

        
        
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
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
				dialog.cancel(); 
			} 
		});
 
		d.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mainpage, menu);
        return true;
    }
	
		
	
}
