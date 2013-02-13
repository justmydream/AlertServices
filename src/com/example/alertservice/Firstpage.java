package com.example.alertservice;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Firstpage extends Activity implements View.OnClickListener, OnCompletionListener {
	
	Firstpage self;
	Handler handle = new Handler();
	Timer timer;
	
	SharedPreferences pref ;
	
	//���� �Ŵ���
	SensorManager mSm;
	
	//�������
	SoundMeter sm;
	
	//���� ���࿩��
	boolean service_check=false;
	
	//��׶��� üũ
	boolean doBackground=false;
	
	//����ġüũ
	boolean domyLoc=false;
	
    //�߽���üũ
	boolean popupCheck=false;
    
    //�����ϱ�üũ
	boolean doRecord=false;
	
	//�����ΰ���
	int acceleroVolume;
	
	//�Ҹ��ΰ���
	int soundVolume;
	
	//�Ŀ���ư Ȱ��ȭ ��
	int powerCount=0;
	
	//���� Ȱ��ȭ
	int soundavail=0;
	//���� Ȱ��ȭ
	int accelavail=0;
	//�����ð�����
	int recTimeValue;
	
	//�޼������ް���
	boolean SMSSendable=true;
	//�޼��� ���� ���ɽð�
	long DELAYTIMEVALUE=10000;
	
	boolean cdtIsStarted=false;
	
	boolean smssendfrompower = false;
	
	ImageButton onNoff;
	TextView state;
	
	
	
	

	//������ɼ���
		private static final int REC_STOP = 0;
		  private static final int RECORDING = 1;
		  private static final int PLAY_STOP = 0;
		  private static final int PLAYING = 1;
		  private static final int PLAY_PAUSE = 2;
		  
		  private MediaRecorder mRecorder = null;
		  private MediaPlayer mPlayer = null;
		  private int mRecState = REC_STOP;
		  private int mPlayerState = PLAY_STOP;
		  private SeekBar mRecProgressBar, mPlayProgressBar;
		  private Button mBtnStartRec, mBtnStartPlay, mBtnStopPlay;
		  private String mFilePath, mFileName = "test.amr";
		  private TextView mTvPlayMaxPoint;
		  
		  private int mCurRecTimeMs = 0;
		  private int mCurProgressTimeDisplay = 0;
		  
		  //����ġ������ ����
		    double latPoint,lngPooint;
		    Geocoder geocoder ;
		    String locationProvider;
		    LocationManager locationManager;
		    Location location;
		  
		  // ������ SeekBaró��
		  Handler mProgressHandler = new Handler()
		  {
		    public void handleMessage(Message msg)
		    {
		      mCurRecTimeMs = mCurRecTimeMs + 100;
		      mCurProgressTimeDisplay = mCurProgressTimeDisplay + 100;
		      
		      // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 
		      // SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 
		      if (mCurRecTimeMs < 0)
		      {}
		      // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�
		      // SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 
		      else if (mCurRecTimeMs < 60000)
		      {
		        mRecProgressBar.setProgress(mCurProgressTimeDisplay);
		        mProgressHandler.sendEmptyMessageDelayed(0, 100);
		      }
		      // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 
		      else
		      {
		        mBtnStartRecOnClick();
		      }
		    }
		  };
		  
		  // ����� SeekBar ó��
		  Handler mProgressHandler2 = new Handler()
		  {
		    public void handleMessage(Message msg)
		    {
		      if (mPlayer == null) return;
		      
		      try
		      {
		        if (mPlayer.isPlaying())
		        {
		          mPlayProgressBar.setProgress(mPlayer.getCurrentPosition());
		          mProgressHandler2.sendEmptyMessageDelayed(0, 100);
		        }
		      }
		      catch (IllegalStateException e)
		      {}
		      catch (Exception e)
		      {}
		    }
		  };
		  //��ǥ���� �̿��Ͽ� �ѱ��ּҷ�ó��
		    public String getKoreanAddress(){
		    	StringBuffer koreanAddress = new StringBuffer();  
		    	
		    	try {
		    		List<Address> addresses;
		    		
		    		
		    		//����ġ��������
		            locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		            //��ġ���� ������ ������ provider=null�̶�� ������ ������ manifest���� ���Ѽ����� �ȵ��ִ� ���̴�.
		            //�����ڴ� GPS, Network ���߿� 1������ String���� ���´�.
		            locationProvider = locationManager.getBestProvider(new Criteria(), true);
		            Location location = locationManager.getLastKnownLocation(locationProvider);
		                    
		            geocoder = new Geocoder(this, Locale.KOREA);
		            latPoint= location.getLatitude();
		        	lngPooint=location.getLongitude();
		        	
		        	
					addresses = geocoder.getFromLocation(latPoint, lngPooint, 1);
					for(Address addr: addresses){
						int index = addr.getMaxAddressLineIndex();
						for(int i=0;i<=index;i++){
							koreanAddress.append(addr.getAddressLine(i));
							koreanAddress.append("");
							}
						koreanAddress.append("\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    	
		    		return koreanAddress.toString(); 	    	
		    }
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_firstpage);
        self = this;
        
        
        //ȭ�� ��/���� ����
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
        
        //�����ΰ��� �Ŵ���
        mSm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
        //�Ҹ� �ΰ��� Ŭ����	
        sm = new SoundMeter();
        
        onNoff = (ImageButton)findViewById(R.id.OnAndOffBtn);
        state = (TextView)findViewById(R.id.statetxt);
        
        
        //������� üũ
        pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
  		service_check = pref.getBoolean("service_check", false);
  		
  		
  		
  		if(service_check)
  		{
  			onNoff.setBackgroundResource(R.drawable.stop);
			state.setText("���񽺰� �������Դϴ�.");
  		}
  		else
  		{
  			onNoff.setBackgroundResource(R.drawable.start);
			state.setText("���񽺰� �������Դϴ�.");
  			sm.stop();
			mSm.unregisterListener(mSensorListener);
  		}
  		 		
  		
        
        onNoff.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
								
				if(service_check)
					service_check=false;
				else
					service_check=true;
				
				if(service_check)
				{
					onNoff.setBackgroundResource(R.drawable.stop);
					state.setText("���񽺰� �������Դϴ�.");
					SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
			  		SharedPreferences.Editor editor = pref.edit();
			  		editor.putBoolean("service_check", service_check);
			   		editor.commit();
					sm.start();
					//���� ���� ���ۺκ�
			    	int delay = SensorManager.SENSOR_DELAY_UI;
					mSm.registerListener(mSensorListener, 
							mSm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), delay);
					
					if(doBackground)
						startService(new Intent(self, Firstpage.class));
						
				}
				else
				{
					onNoff.setBackgroundResource(R.drawable.start);
					state.setText("���񽺰� �������Դϴ�.");
					SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
			  		SharedPreferences.Editor editor = pref.edit();
					editor.putBoolean("service_check", service_check);
			   		editor.commit();  
					sm.stop();
					mSm.unregisterListener(mSensorListener);
					stopService(new Intent(self, Firstpage.class));
					
						
				}
			}
		});
        
        
        pref = getSharedPreferences("PrefTest", MODE_PRIVATE);
       /* 
        //���ڸ޼��� ����
        pref.getString("editText", "null");
        
        //��ȭ��ȣ
        pref.getString("AllAddress", "null");
        
        //���� �ΰ���
        pref.getInt("accelvalue", 10);
        
        //�Ҹ� �ΰ���
        pref.getInt("soundvalue", 80);
        
        //��׶���üũ
        pref.getBoolean("back", false);
        //����ġ������üũ
        pref.getBoolean("location", false);
        //�߽���üũ
        pref.getBoolean("send", true);        	
        //�����ϱ�üũ
        pref.getBoolean("rec", false);
        //�����ð� ����
        pref.getInt("timevalue",10000);*/
        
      //����
        mFilePath = SunUtil.makeDir("progress_recorder");
        
        mBtnStartRec = (Button) findViewById(R.id.btnStartRec);
        mBtnStartPlay = (Button) findViewById(R.id.btnStartPlay);
        mBtnStopPlay = (Button) findViewById(R.id.btnStopPlay);
        mRecProgressBar = (SeekBar) findViewById(R.id.recProgressBar);
        mPlayProgressBar = (SeekBar) findViewById(R.id.playProgressBar);
        mTvPlayMaxPoint = (TextView) findViewById(R.id.tvPlayMaxPoint);
        
        mBtnStartRec.setOnClickListener(this);
        mBtnStartPlay.setOnClickListener(this);
        mBtnStopPlay.setOnClickListener(this);
        
        
      //����ġ��������
        /*locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //��ġ���� ������ ������ provider=null�̶�� ������ ������ manifest���� ���Ѽ����� �ȵ��ִ� ���̴�.
        //�����ڴ� GPS, Network ���߿� 1������ String���� ���´�.
        locationProvider = locationManager.getBestProvider(new Criteria(), true);
        Location location = locationManager.getLastKnownLocation(locationProvider);
                
        geocoder = new Geocoder(this, Locale.KOREA);
        latPoint= location.getLatitude();
    	lngPooint=location.getLongitude();*/
        
        
        
        
    }
    
    @Override
    protected void onPause() {    	
    	/*if(ScreenReceiver.wasScreenOn||!ScreenReceiver.wasScreenOn)
    	{
    		Log.i("Tag", "screen turned off");
    		Log.i("Tag", "powerCount"+powerCount);
    		powerCount++;
			if(powerCount>3)
				powerCount=0;
    		
    	}*/
    	
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	//���� �ʱ�ȭ
    	service_check = pref.getBoolean("service_check", false);
  		
  		//�ʱ�ȭ
  		if(service_check)
		{
  			onNoff.setBackgroundResource(R.drawable.stop);
			state.setText("���񽺰� �������Դϴ�.");
		}
  		else
  		{
  			onNoff.setBackgroundResource(R.drawable.start);
			state.setText("���񽺰� �������Դϴ�.");
  		}
    	
    	
    	//��׶���üũ
        doBackground = pref.getBoolean("back", false);
        //����ġ������üũ
        domyLoc = pref.getBoolean("location", false);
        //�߽���üũ
        popupCheck = pref.getBoolean("send", false);        	
        //�����ϱ�üũ
        doRecord = pref.getBoolean("rec", false);
        //�����ΰ���
        acceleroVolume = pref.getInt("accelvalue", 10);
        //�Ҹ��ΰ���
        soundVolume = pref.getInt("soundvalue", 80);
        //�����ð�����
        recTimeValue = pref.getInt("timevalue", 10000);
        //�����̽ð�����
        DELAYTIMEVALUE = pref.getInt("smsdelay", 10000);
        
        
        
    	/*//�Ŀ���ư �����κ�
    	if(ScreenReceiver.wasScreenOn||!ScreenReceiver.wasScreenOn)
    	{
    		Log.i("Tag", "screen turned on");
    		Log.i("Tag", "powerCount"+powerCount);
    		if(powerCount==1)
			{
				Log.i("Tag", "timer start!");
				this.timer = new Timer();
			    timer.schedule(new Task(), 10);
				
				
			}//end of if
    	}*/
    	
    	super.onResume();
    }
    
        
	
    //�޽��� ���� �޼ҵ�
    public void sendSMS()
    {
    	if(SMSSendable==false)
    		return;
    	
    	
    	final SmsManager sms = SmsManager.getDefault();
    	
    	Intent sentIndent = new Intent("ACTION_MSG_SENT");
        final PendingIntent sentIndentPending = 
        	PendingIntent.getBroadcast(this, 0, sentIndent, PendingIntent.FLAG_CANCEL_CURRENT);
        
        Intent receiptIndent  = new Intent("ACTION_MSG_RECEIPT");
        final PendingIntent receiptIndentPending  = 
        	PendingIntent.getBroadcast(this, 0, receiptIndent , PendingIntent.FLAG_CANCEL_CURRENT);
       
        
        String message = pref.getString("editText", "��޻�Ȳ�Դϴ� Ȯ�κ�Ź�����.");
        
     // ������ ��ȭ��ȣ ������
        String destination=pref.getString("AllAddress", null);
        String destinations[]=null;
        if(destination!=null)
        {
        	destinations=destination.split(",");
	        for(int i=1 ; i<destinations.length ;i=i+2)
	        {
	        	sms.sendTextMessage(destinations[i], null,message, 
	            		 sentIndentPending, receiptIndentPending);
	        	
	        }
        }
        else
        {
        	Toast.makeText(self, "��޿���ó�� ����� ����� �����ϴ�.", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(domyLoc)//��ġ������ üũ �ϰ��
        {  String str = "�� ��ġ�� "+getKoreanAddress()+" �ֺ� �Դϴ�."; 
    	
        if(destination!=null)
        {
        	destinations=destination.split(",");
	        for(int i=1 ; i<destinations.length ;i=i+2)
	        {
	        	sms.sendTextMessage(destinations[i], null,str, 
	            		 sentIndentPending, receiptIndentPending);
	        	
	        }
        }
    }
        if(doRecord==true)//�����ϱ� üũ �ϰ��
        {	 sm.stop();		        					 					  
		      mRecState = RECORDING;
		      startRec();
		      updateUI();
		      System.out.println("��������");
		
		      mBtnStartRec.setFocusable(false);
		      mBtnStartRec.setClickable(false);
		      
		     
		      
		      new CountDownTimer(recTimeValue,recTimeValue) { //10000�� 10��.
				
				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFinish() {
					mRecState = REC_STOP;
				      stopRec();
				      updateUI();
				      System.out.println("������");
				      sm.start();
				      mBtnStartRec.setFocusable(true);
				      mBtnStartRec.setClickable(true);
					
				}
			}.start();
        }
        
        Log.i("Tag", "sms is sended!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Toast.makeText(self, "�޼����� �۽ŵǾ����ϴ�.", Toast.LENGTH_SHORT).show();
        SMSSendable=false;
        
        new CountDownTimer(DELAYTIMEVALUE,DELAYTIMEVALUE) {
			@Override
			public void onTick(long arg0) {
			}
			
			@Override
			public void onFinish() {
				SMSSendable=true;
				Log.i("Tag", "send ready!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}.start();
    }
    
   
    //�Ŀ�, ���� ������, ���� ������
    SensorEventListener mSensorListener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if(SMSSendable==false)
				return;
			
			//�Ŀ�����
			//��������
			if(!ScreenReceiver.wasScreenOn)
			{
				Log.i("Tag", "!ScreenReceiver.wasScreenOn");
				final CountDownTimer cdt = new CountDownTimer(2000, 10) {
					@Override
					public void onTick(long millisUntilFinished) {
					}
					
					@Override
					public void onFinish() {
						if(ScreenReceiver.screenCount>=3)
						{
							smssendfrompower=true;
							ScreenReceiver.screenCount=0;
							cdtIsStarted=false;
						}
						else
						{
							cdtIsStarted=false;
							smssendfrompower=false;
						}
					}
				};

				if(cdtIsStarted==true)
				{
					Log.i("Tag", "cdtIsStarted");
					return;
				}
				else
				{
					Log.i("Tag", "notstart");
				}
				
				cdtIsStarted=true;
				cdt.start();
				
				
			}
			
			//�Ŀ���ư���� Ȱ��ȭ ������ ���� ���� 
			if(smssendfrompower)
			{
				Log.i("Tag", "sms");
				if(popupCheck)
				{
					smssendfrompower=false;
					startActivityForResult(new Intent(self, CustDlg.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 1002);
				}
				else
				{
					smssendfrompower=false;
					sendSMS();
				}
				cdtIsStarted=false;
			}
			
			
			
			//�Ҹ����� �κ�
			double cur_volume= sm.getAmplitude();
			if(cur_volume>=soundVolume)
			{
				
				if(soundavail>0)
					return;
				soundavail++;
				//���� ����
				Log.i("Sound", soundVolume+"db ���� ū�Ҹ� ����");
				
				//�˾�üũ�� ���� ����
				if(popupCheck)
				{
					startActivityForResult(new Intent(self, CustDlg.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 1002);
				}
				else
				{
					sendSMS();
					soundavail=0;
				}
				
			}
			
			float[] v = event.values;
			
			float i=0;
			float j=0;
			float k=0;
			switch (event.sensor.getType()) {
			
			case Sensor.TYPE_ACCELEROMETER:
				i=v[0];
				j=v[1];
				k=v[2];
				break;
			}
			
			float f1 = acceleroVolume;
			float f2 = -acceleroVolume;
			if(i<f1&&j>=f2)
				return;
			
			if(accelavail>0)
				return;
			//�˾�üũ�� ���� ����
			if(popupCheck)
			{
				accelavail++;
				startActivityForResult(new Intent(self, CustDlg.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 1002);
			}
			else
			{
				sendSMS();
				accelavail=0;
			}
			Log.i("Accel", "TYPE_ACCELEROMETER : x = " + i + " , y = " + j + " , z = " + k);
			
			//txt4.setText("���ú� " + sm.getAmplitude());
			//txt4.setText("���ú� : " + sm.getAmplitude());
			
			
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
	 // ������ ��ư�� OnClick �̺�Ʈ ������ 
	  public void onClick(View v)
	  {
	    switch(v.getId())
	    {
	      case R.id.btnStartRec:
	        mBtnStartRecOnClick();
	        break;
	      case R.id.btnStartPlay:
	        mBtnStartPlayOnClick();
	        break;
	      case R.id.btnStopPlay:
	        mBtnStopPlayOnClick();
	        break;
	      default:
	        break;
	    }
	  }
	  
	  private void mBtnStartRecOnClick()
	  {
		  if(sm.isStarted()==false){
	    if (mRecState == REC_STOP)
	    {	Toast t =Toast.makeText(this, "�����߿��� ���񽺸� �̿��� �� �����ϴ�.", 1);
	    	t.setGravity(Gravity.TOP, 0, 300);
	    	t.show();
	      mRecState = RECORDING;
	      startRec();
	      onNoff.setClickable(false);
	      onNoff.setFocusable(false);
	      updateUI();
	    }
	    else if (mRecState == RECORDING)
	    {
	      mRecState = REC_STOP;
	      stopRec();
	      onNoff.setClickable(true);
	      onNoff.setFocusable(true);
	      updateUI();
	      
	    }  
	  }	  
		  else{
			  Toast.makeText(this, "�Ҹ������������� ����Ұ�.", 1).show();
		  }
		  
	  }
	  
	  // ��������
	  private void startRec()
	  {
	    mCurRecTimeMs = 0;
	    mCurProgressTimeDisplay = 0;
	    
	    // SeekBar�� ���¸� 0.1���� üũ ����
	    mProgressHandler.sendEmptyMessageDelayed(0, 100);
	    
	    if (mRecorder == null)
	    {
	      mRecorder = new MediaRecorder();
	      mRecorder.reset();
	    }
	    else
	    {
	      mRecorder.reset();
	    }
	    
	    try
	    {
	      mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
	      mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
	      mRecorder.setOutputFile(mFilePath + mFileName);
	      mRecorder.prepare();
	      mRecorder.start();
	    }
	    catch (IllegalStateException e)
	    {
	      Toast.makeText(this, "IllegalStateException", 1).show();
	    }
	    catch (IOException e)
	    {
	      Toast.makeText(this, "IOException", 1).show();
	    }
	  }
	  
	  // ��������
	  private void stopRec()
	  {
	    try
	    {
	      mRecorder.stop();
	    }
	    catch(Exception e)
	    {}
	    finally
	    {
	      mRecorder.release();
	      mRecorder = null;
	    }
	    
	    mCurRecTimeMs = -999;
	    // SeekBar�� ���¸� ��� üũ  
	    mProgressHandler.sendEmptyMessageDelayed(0, 0);
	  }
	  
	  private void mBtnStartPlayOnClick()
	  {
	    if (mPlayerState == PLAY_STOP)
	    {
	      mPlayerState = PLAYING;
	      initMediaPlayer();
	      startPlay();
	      updateUI();
	    }
	    else if (mPlayerState == PLAYING)
	    {
	      mPlayerState = PLAY_PAUSE;
	      pausePlay();
	      updateUI();
	    }
	    else if (mPlayerState == PLAY_PAUSE)
	    {
	      mPlayerState = PLAYING;
	      startPlay();
	      updateUI();
	    }
	  }
	  
	  private void mBtnStopPlayOnClick()
	  {
	    if (mPlayerState == PLAYING || mPlayerState == PLAY_PAUSE)
	    {
	      mPlayerState = PLAY_STOP;
	      stopPlay();
	      releaseMediaPlayer();
	      updateUI();      
	    }
	  }
	  
	  private void initMediaPlayer()
	  {
	    // �̵�� �÷��̾� ����
	    if (mPlayer == null)
	      mPlayer = new MediaPlayer();
	    else
	      mPlayer.reset();
	    
	    mPlayer.setOnCompletionListener(this);
	    String fullFilePath = mFilePath + mFileName;
	    
	    try
	    {
	      mPlayer.setDataSource(fullFilePath);
	      mPlayer.prepare();   
	      int point = mPlayer.getDuration();
	      mPlayProgressBar.setMax(point);
	      
	      int maxMinPoint = point / 1000 / 60;
	      int maxSecPoint = (point / 1000) % 60;
	      String maxMinPointStr = "";
	      String maxSecPointStr = "";
	      
	      if (maxMinPoint < 10)
	        maxMinPointStr = "0" + maxMinPoint + ":";
	      else
	        maxMinPointStr = maxMinPoint + ":";
	      
	      if (maxSecPoint < 10)
	        maxSecPointStr = "0" + maxSecPoint;
	      else
	        maxSecPointStr = String.valueOf(maxSecPoint);
	      
	      mTvPlayMaxPoint.setText(maxMinPointStr + maxSecPointStr);
	      
	      mPlayProgressBar.setProgress(0);
	    }
	    catch(Exception e)
	    {
	      Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
	    }
	  }
	  
	  // ��� ����
	  private void startPlay()
	  {
	    Log.v("ProgressRecorder", "startPlay().....");
	    
	    try
	    {
	      mPlayer.start();
	      
	      // SeekBar�� ���¸� 0.1�ʸ��� üũ      
	      mProgressHandler2.sendEmptyMessageDelayed(0, 100);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      Toast.makeText(this, "error : " + e.getMessage(), 0).show();
	    }
	  }
	  
	  private void pausePlay()
	  {
	    Log.v("ProgressRecorder", "pausePlay().....");
	    
	    // ����� �Ͻ� �����ϰ�
	    mPlayer.pause();
	    
	    // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	  }
	  
	  private void stopPlay()
	  {
	    Log.v("ProgressRecorder", "stopPlay().....");
	    
	    // ����� �����ϰ�
	    mPlayer.stop();
	    
	    // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	  }
	  
	  private void releaseMediaPlayer()
	  {
	    Log.v("ProgressRecorder", "releaseMediaPlayer().....");
	    mPlayer.release();
	    mPlayer = null;
	    mPlayProgressBar.setProgress(0);
	  }
	  
	  public void onCompletion(MediaPlayer mp)
	  {
	    mPlayerState = PLAY_STOP; // ����� �����

	    // ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
	    mProgressHandler2.sendEmptyMessageDelayed(0, 0);
	    
	    updateUI();
	  }
	  
	  private void updateUI()
	  {
	    if (mRecState == REC_STOP) 
	    {
	      mBtnStartRec.setText("����");
	      mRecProgressBar.setProgress(0);
	    }
	    else if (mRecState == RECORDING)
	      mBtnStartRec.setText("�ߴ�");
	    
	    if (mPlayerState == PLAY_STOP)
	    {
	      mBtnStartPlay.setText("���");
	      mPlayProgressBar.setProgress(0);
	    }
	    else if (mPlayerState == PLAYING)
	      mBtnStartPlay.setText("����");
	    else if (mPlayerState == PLAY_PAUSE)
	      mBtnStartPlay.setText("���");
	  }
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		
		case 1002:
			if(resultCode==RESULT_OK)
			{
				sendSMS();
				soundavail=0;
				accelavail=0;
			}
			else
			{
				soundavail=0;
				accelavail=0;
			}
			
			if(smssendfrompower)
			{
				smssendfrompower=false;
			}
			if(cdtIsStarted)
			{
				cdtIsStarted=false;
			}
			break;
		}
				
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

    
    
}//end of act