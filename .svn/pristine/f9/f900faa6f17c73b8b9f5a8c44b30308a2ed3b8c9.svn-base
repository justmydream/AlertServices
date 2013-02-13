package com.example.alertservice;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	
	MainActivity self;
	//
	//안드로이드는 UI를 바꿀때 핸들러를 통해서만
	Handler handle = new Handler();
	Timer timer;
	
 //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self=this;
        
        this.timer = new Timer();
        timer.schedule(new Task(), 3000);	//1은 1/1000초		//스케쥴링


    	
        
    }
    
    class Task extends TimerTask
    {
		@Override
		public void run() {
			//UI쓰레드 (onCreate() 메소드 호출이 끝날때까지 )에서만 화면 구성이 가능하다
			//따라서 나중에 별도로 UI변경을 진행하고자 한다면 UI쓰래드에 삽입되어 UI를 구성하는 코드를 사용한다.
			//Handler 객체를 사용하여 삽입
			
			
			//handle.post 언제든지 화면 변경이 가능하다
			handle.post(new Runnable(){

				public void run() {
					//내부에 부하가 많이 걸리는 코드를 작성하면 화면이 버벅댄다.
					makeUI();
				}
			});
			
		}
    	
    }
     
    public void makeUI()
    {   

        Intent i = new Intent(self, Mainpage.class);
		self.startActivity(i);
		self.finish();
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	Builder d = new AlertDialog.Builder(this);
    	 
		d.setMessage("정말 종료하시겠습니까?");
 
		d.setPositiveButton("종료", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) { 
				// process전체 종료 
				timer.cancel();
				self.finish(); 
			} 
		}); 
		d.setNegativeButton("취소", new DialogInterface.OnClickListener() {
 
			public void onClick(DialogInterface dialog, int which) { 
				dialog.cancel(); 
			} 
		});
 
		d.show();
    }

}
