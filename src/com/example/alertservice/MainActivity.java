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
	//�ȵ���̵�� UI�� �ٲܶ� �ڵ鷯�� ���ؼ���
	Handler handle = new Handler();
	Timer timer;
	
 //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self=this;
        
        this.timer = new Timer();
        timer.schedule(new Task(), 3000);	//1�� 1/1000��		//�����층


    	
        
    }
    
    class Task extends TimerTask
    {
		@Override
		public void run() {
			//UI������ (onCreate() �޼ҵ� ȣ���� ���������� )������ ȭ�� ������ �����ϴ�
			//���� ���߿� ������ UI������ �����ϰ��� �Ѵٸ� UI�����忡 ���ԵǾ� UI�� �����ϴ� �ڵ带 ����Ѵ�.
			//Handler ��ü�� ����Ͽ� ����
			
			
			//handle.post �������� ȭ�� ������ �����ϴ�
			handle.post(new Runnable(){

				public void run() {
					//���ο� ���ϰ� ���� �ɸ��� �ڵ带 �ۼ��ϸ� ȭ���� �������.
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
    	 
		d.setMessage("���� �����Ͻðڽ��ϱ�?");
 
		d.setPositiveButton("����", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) { 
				// process��ü ���� 
				timer.cancel();
				self.finish(); 
			} 
		}); 
		d.setNegativeButton("���", new DialogInterface.OnClickListener() {
 
			public void onClick(DialogInterface dialog, int which) { 
				dialog.cancel(); 
			} 
		});
 
		d.show();
    }

}
