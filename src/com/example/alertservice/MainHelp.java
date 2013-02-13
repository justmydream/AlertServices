package com.example.alertservice;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.widget.ImageView;

public class MainHelp extends Activity {
	ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_help);
        imageView = (ImageView) findViewById(R.id.imageView1);
   //     imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);  
        imageView.setAdjustViewBounds(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_help, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	Builder d = new AlertDialog.Builder(this);
    	 
		d.setMessage("���� �����Ͻðڽ��ϱ�?");
 
		d.setPositiveButton("����", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) { 
				// process��ü ���� 
				finish(); 
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