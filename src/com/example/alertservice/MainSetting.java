package com.example.alertservice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.TextSize;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainSetting extends Activity 
implements AdapterView.OnItemSelectedListener , AdapterView.OnItemClickListener {
	
	private ArrayList<SettingData> ArraySet;

	ListView listView;
	
	MainSetting self;
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	EditText smsText;	
	String helpstr= null;	
	boolean backSet = false; //백그라운드체크
    boolean locationSet = false; //내위치보내기체크
    boolean sendSet = false; //발신전체크
    boolean recSet = false;  //녹음하기체크
    int recTime=10000;//녹음시간
    int timeWhich=0;
    int delayTime=10000;//녹음시간
    int delaytimeWhich=0;
   


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);
       
        self=this;/*
        SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
        
        ArraySet = new ArrayList<SettingData>();
        ArraySet.add(new SettingData("백그라운드 실행",
        		"작업이 종료되어도 실행됩니다.",pref.getBoolean("back", false)));
        ArraySet.add(new SettingData("보낼메세지 내용",
        		"상대방에게 보내는 메세지를 설정할 수 있습니다.",false));
        ArraySet.add(new SettingData("내 위치 보내기",
        		"메세지에 추가로 자신의 위치를 보낼 수 있습니다.",pref.getBoolean("location", false)));
        ArraySet.add(new SettingData("발신전 확인",
        		"메세지 보낼 때, 확인 메세지를 띠웁니다.",pref.getBoolean("send", false)));
        ArraySet.add(new SettingData("상대방 추가",
        		"메세지 받는 사람을 추가할 수 있습니다.",false));
        ArraySet.add(new SettingData("민감도 설정",
        		"흔들기의 세기 반응을 조절할 수 있습니다.",true));
        ArraySet.add(new SettingData("소리감지 설정",
        		"주변 소리 감지 세기 반응을 조절할 수 있습니다.",false));        
        ArraySet.add(new SettingData("녹음하기",
        		"긴급 실행시 녹음기능이 추가 됩니다.",pref.getBoolean("rec", false)));            

        listView = (ListView)findViewById(R.id.settingList);
      //  cb = (CheckBox)findViewById(R.id.settingCheck);                  
        
        listView.setAdapter(new MainSettingAdapter        		
        (this,android.R.layout.simple_list_item_1,ArraySet));
        //어뎁터까지 위젯에 연결하면 어뎁터에서 항목을 만들어서 제공해주기 때문에 해당
        //위젯에 항목이 출력됨.
      
        listView.setOnItemClickListener(this);
        listView.setOnItemSelectedListener(this);     
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); */
          
                
    }
    
    @Override
    protected void onResume() {
    	SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
        
        ArraySet = new ArrayList<SettingData>();
        ArraySet.add(new SettingData("백그라운드 실행",
        		"작업이 종료되어도 실행됩니다.",pref.getBoolean("back", false)));
        ArraySet.add(new SettingData("보낼메세지 내용",
        		"상대방에게 보내는 메세지를 설정할 수 있습니다.",false));
        ArraySet.add(new SettingData("내 위치 보내기",
        		"메세지에 추가로 자신의 위치를 보낼 수 있습니다.",pref.getBoolean("location", false)));
        ArraySet.add(new SettingData("발신전 확인",
        		"메세지 보낼 때, 확인 메세지를 띠웁니다.",pref.getBoolean("send", false)));
        ArraySet.add(new SettingData("상대방 추가",
        		"메세지 받는 사람을 추가할 수 있습니다.",false));
        ArraySet.add(new SettingData("민감도 설정",
        		"흔들기의 세기 반응을 조절할 수 있습니다.",true));
        ArraySet.add(new SettingData("소리감지 설정",
        		"주변 소리 감지 세기 반응을 조절할 수 있습니다.",false));        
        ArraySet.add(new SettingData("녹음하기",
        		"긴급 실행시 녹음기능이 추가 됩니다.",pref.getBoolean("rec", false)));
        ArraySet.add(new SettingData("녹음시간 설정",
        		"자동 녹음 시간을 조절할 수 있습니다.",false));
        ArraySet.add(new SettingData("메세지 전송지연시간 설정",
        		"메세지가 전송된후 설정된 시간만큼 메세지가 보내지지 않습니다.",false));


        listView = (ListView)findViewById(R.id.settingList);
      //  cb = (CheckBox)findViewById(R.id.settingCheck);                  
        
        listView.setAdapter(new MainSettingAdapter        		
        (this,android.R.layout.simple_list_item_1,ArraySet));
        //어뎁터까지 위젯에 연결하면 어뎁터에서 항목을 만들어서 제공해주기 때문에 해당
        //위젯에 항목이 출력됨.
      
        listView.setOnItemClickListener(this);
        listView.setOnItemSelectedListener(this);     
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    	
    	
    	super.onResume();
    }
    
    
    
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    	// TODO Auto-generated method stub
    	/*if(arg2==2){
    	String tv = ((SettingData)arg0.getItemAtPosition(arg2)).getMain_Title();
    	Toast.makeText(MainSetting.this, tv, Toast.LENGTH_SHORT).show();}

    */ 
    	SharedPreferences pref1 = getSharedPreferences("PrefTest",MODE_PRIVATE);
    	if(pref1.getBoolean("service_check", false)==true)
    	{
    		Toast.makeText(MainSetting.this, "서비스를 정지후 설정을 바꾸십시오.", Toast.LENGTH_SHORT).show();
    		return;
    	}
    		
   
    	//================체크박스 값유지==============
    	if(arg2==0){ //백그라운드
    		SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    		 SharedPreferences.Editor editor = pref.edit();
    		     		    		
        			 if(ArraySet.get(arg2).isSettingcheck()==true)
        			 {
        				backSet = false;
        			 }
        			 else if(ArraySet.get(arg2).isSettingcheck()==false)
        			 {        		   			
    		   			backSet=true;
       		    		Toast.makeText(MainSetting.this, "백그라운드 활성화가 되었습니다.", Toast.LENGTH_SHORT).show();
        			 }
    				 editor.putBoolean("back", backSet);
    				 editor.commit();
    				 listView.setAdapter(new MainSettingAdapter        		
    					        (this,android.R.layout.simple_list_item_1,ArraySet));
        		  }
    	if(arg2==2){ //내위치보내기
   		 SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
   		 SharedPreferences.Editor editor = pref.edit();
   		 
       			 if(ArraySet.get(arg2).isSettingcheck()==true){
       				 locationSet = false;   		    	
       			 }
	   		    	else if(ArraySet.get(arg2).isSettingcheck()==false)
	   		    	{        		   			
	       		    	locationSet=true;
	          		    Toast.makeText(MainSetting.this, "내 위치 보내기가 활성화 되었습니다.", Toast.LENGTH_SHORT).show();          		    		
	   		    	}
       			editor.putBoolean("location", locationSet);
      		    editor.commit();  
      		    listView.setAdapter(new MainSettingAdapter        		
				        (this,android.R.layout.simple_list_item_1,ArraySet));
       		  }
    	if(arg2==3){ //발신전확인
      		 SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
      		 SharedPreferences.Editor editor = pref.edit();
          			 if(ArraySet.get(arg2).isSettingcheck()==true)
          			 {
          				 sendSet = false;      		    	
          			 }
          			 else if(ArraySet.get(arg2).isSettingcheck()==false)
          			 {        		   			
	      		    		sendSet=true; 
	         		    	Toast.makeText(MainSetting.this, "발신전 확인이 활성화 되었습니다.", Toast.LENGTH_SHORT).show();          		    		
          			 }

       		   		editor.putBoolean("send", sendSet);
          		    editor.commit();
          		  listView.setAdapter(new MainSettingAdapter        		
  				        (this,android.R.layout.simple_list_item_1,ArraySet));
          		  }
    	if(arg2==7){ //녹음
     		 SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
     		 SharedPreferences.Editor editor = pref.edit();
         			 if(ArraySet.get(arg2).isSettingcheck()==true){
         				 recSet = false;   
         		    		System.out.println("녹음체크선택");         		    		
         			 }
     		    	else if(ArraySet.get(arg2).isSettingcheck()==false){        		   			
     		    		recSet=true;
        		    		Toast.makeText(MainSetting.this, "녹음기능이 활성화 되었습니다.", Toast.LENGTH_SHORT).show();    
        		    		System.out.println("녹음체크해제");
     		    		}

     				 editor.putBoolean("rec", recSet);
     		    		editor.commit(); 
     		    		 listView.setAdapter(new MainSettingAdapter        		
     	  				        (this,android.R.layout.simple_list_item_1,ArraySet));
         		  }
	//==========================================
   


    	//메세지 설정 	
    	if(arg2==1){
    		
    		
    		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
    	    final View layout = inflater.inflate(R.layout.setting_sms,(ViewGroup)findViewById(R.layout.setting_sms));
    		EditText smsText = (EditText)layout.findViewById(R.id.smsText);
    		SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
	  		SharedPreferences.Editor editor = pref.edit();
	  		String msgNew = smsText.getText().toString();
	  		if(pref.getString("editText", null)==null)
	  		{
		  		editor.putString("editText", msgNew);
		  		editor.commit();
	  		}
    		 String msg = pref.getString("editText", "");
    		 if(msg!=null)
    			 smsText.setText(msg);    

        		Builder dlg= new AlertDialog.Builder(this);
        		dlg.setTitle("보낼 메세지 내용")        		
        		.setView(layout)
        		.setPositiveButton("저장", new DialogInterface.OnClickListener() {
        			
        			public void onClick(DialogInterface dialog, int which) {
        				  SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
        	    		  SharedPreferences.Editor editor = pref.edit();
        				EditText smsText = (EditText)layout.findViewById(R.id.smsText);
        	    		String msgNew = smsText.getText().toString();
        	    		editor.putString("editText", msgNew);
        	    		editor.commit();       				
        				        				
        				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
        				
        			}
        		})
        		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
        			
        			public void onClick(DialogInterface dialog, int which) {
        				// TODO Auto-generated method stub
        				
        			}
        		}).show();
        
        	
    	}

    	//연락처 추가
    	if(arg2==4)
    	{
    		Intent intent = new Intent(self, Setting_Address.class);
    		self.startActivity(intent);
    		 Toast t =Toast.makeText(this, "주소를 삭제하려면 리스트를 누르세요.", 1);
 	    	t.setGravity(Gravity.TOP, 0, 300);
 	    	t.show();
    		
    		
    	}
    	
    	//진동감지 민감도 설정
    	if(arg2==5)
    	{
    		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
    	    final View layout = inflater.inflate(R.layout.setting_acceleraometer_sensitivity,(ViewGroup)findViewById(R.layout.setting_acceleraometer_sensitivity));

    	    //시크바 텍스트 초기화
    	    final SeekBar accelseekbar = (SeekBar)layout.findViewById(R.id.seekBar1);
    	    final TextView statetxt = (TextView)layout.findViewById(R.id.textView1);
    	    
    	    SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    	    int initNum = pref.getInt("accelvalue", 10);
    	    accelseekbar.setMax(50);
    	    accelseekbar.setProgress(initNum);
    	    
    	    statetxt.setText("민감도 설정 : "+ initNum);
    	    
    	    accelseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					
					statetxt.setText("민감도 설정 : "+ progress);
					
				}
			});
    	    
    	    
    		Builder dlg= new AlertDialog.Builder(this);
    		dlg.setTitle("진동 민감도 설정")
    		
    		.setView(layout)
    		.setPositiveButton("확인", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				
    				int customNum = accelseekbar.getProgress();
    				
    				SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    				SharedPreferences.Editor editor = pref.edit();
    				editor.putInt("accelvalue", customNum);
	   	    		editor.commit();       				
   				        				
   				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
    				
    				
    			}
    		})
    		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				
    			}
    		}).show();
    	}
    	
    	
    	//소리감지 민감도
    	if(arg2==6)
    	{
    		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
    	    View layout = inflater.inflate(R.layout.setting_sound_sensitivity,(ViewGroup)findViewById(R.layout.setting_sound_sensitivity));

    	    //시크바 텍스트 초기화
    	    final SeekBar soundseekbar = (SeekBar)layout.findViewById(R.id.seekBar1);
    	    final TextView statetxt = (TextView)layout.findViewById(R.id.textView3);
    	    
    	    SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    	    int initNum = pref.getInt("soundvalue", 80);
    	    soundseekbar.setMax(120);
    	    soundseekbar.setProgress(initNum);
    	    
    	    statetxt.setText("데시벨 : "+ initNum);
    	    
    	    soundseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					
					statetxt.setText("데시벨 : "+ progress);
					
				}
			});
    	    
    	    
    	    
    		Builder dlg= new AlertDialog.Builder(this);
    		dlg.setTitle("소리 민감도 설정")
    	
    		.setView(layout)
    		.setPositiveButton("확인", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				
    				int customNum = soundseekbar.getProgress();
    				
    				SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    				SharedPreferences.Editor editor = pref.edit();
    				editor.putInt("soundvalue", customNum);
	   	    		editor.commit();       				
   				        				
   				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
    				
    				
    			}
    		})
    		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				
    			}
    		}).show();
    		
    		
    		
    	}
    	
    	//녹음시간설정
    	if(arg2==8){
    		SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    	 int tSB=pref.getInt("timeSetButton", timeWhich);
			
    		
    		final String timeSet[] ={"10초","30초","1분","5분","10분"};
    		  
    		Builder dlg= new AlertDialog.Builder(this);
    		dlg.setTitle("녹음 시간 설정");    		
			dlg.setSingleChoiceItems(timeSet, tSB, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					if(which==0){
						recTime=10000;
						timeWhich=0;
					}
					else if(which==1){
						recTime=30000;
						timeWhich=1;
					}
					else if(which==2){
						recTime=60000;
						timeWhich=2;
					}
					else if(which==3){
						recTime=300000; 		
						timeWhich=3;
					}
					else{
						recTime = 600000;
						timeWhich=4;
					}
					SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.putInt("timeSetButton", timeWhich);
					editor.putInt("timevalue", recTime);
					editor.commit();
				}
			})
    		.setPositiveButton("확인", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {   				
    			    				
    				SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    				SharedPreferences.Editor editor = pref.edit();
    				
					editor.putInt("timevalue", recTime);
	   	    		editor.commit();       				
   				        				
   				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
    				
    				
    			}
    		})
    		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				
    			}
    		}).show();
    		
    	}
    	
    	//메세지 전송지연시간
    	if(arg2==9){
    		SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    	 int tSB=pref.getInt("delaysetting", delaytimeWhich);
			
    		
    		final String timeSet[] ={"10초","30초","1분","5분","10분"};
    		  
    		Builder dlg= new AlertDialog.Builder(this);
    		dlg.setTitle("메세지 지연시간 설정");    		
			dlg.setSingleChoiceItems(timeSet, tSB, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					if(which==0){
						delayTime=10000;
						delaytimeWhich=0;
					}
					else if(which==1){
						delayTime=30000;
						delaytimeWhich=1;
					}
					else if(which==2){
						delayTime=60000;
						delaytimeWhich=2;
					}
					else if(which==3){
						delayTime=300000; 		
						delaytimeWhich=3;
					}
					else{
						delayTime = 600000;
						delaytimeWhich=4;
					}
					SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.putInt("smsdelay", delayTime);
					editor.putInt("delaysetting", delaytimeWhich);
					editor.commit();
				}
			})
    		.setPositiveButton("확인", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {   				
    			    				
    				SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
    				SharedPreferences.Editor editor = pref.edit();
    				
					editor.putInt("smsdelay", delayTime);
	   	    		editor.commit();       				
   				        				
   				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
    				
    				
    			}
    		})
    		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				
    			}
    		}).show();
    		
    	}
    	
    	
    	
    	
    	

    	boolean tv =((SettingData)arg0.getItemAtPosition(arg2)).isSettingcheck();
    	if(tv==true){	
    		((SettingData)arg0.getItemAtPosition(arg2)).setSettingcheck(false);
    		}
    	else{
    		((SettingData)arg0.getItemAtPosition(arg2)).setSettingcheck(true); 	
    	} 
   
    	
    	

    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
    		long arg3) {
    	// TODO Auto-generated method stub
    /*	selection.setText(items[arg2]);*/
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    	// TODO Auto-generated method stub
  /*  	selection.setText("");*/
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

}

    