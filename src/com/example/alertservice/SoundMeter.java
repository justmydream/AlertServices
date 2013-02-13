package com.example.alertservice;

import android.media.MediaRecorder;
import android.util.Log;



// 0 ���� 120 ����
public class SoundMeter
{
  private MediaRecorder mRecorder;
  private boolean started;

  public SoundMeter()
  {
    this.mRecorder = new MediaRecorder();
    this.started = false;
  }

  public double getAmplitude()
  {
    double d=0;
    if (this.started)
    {
      d = this.mRecorder.getMaxAmplitude() / 135.0D;
    }
      return d;
  }

  public void release()
  {
    this.mRecorder.release();
  }
  
  public void start()
  {
	  if(this.started)
		  return;
    try
    {
      this.mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
      this.mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      this.mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
      this.mRecorder.setOutputFile("/dev/null");
      this.mRecorder.prepare();
      this.mRecorder.start();
      this.started = true;
      return;
    }
    catch (Exception localException)
    {
        localException.printStackTrace();
     //   Log.i("Tag", localException.getMessage());
    }
  }

  public void stop()
  {
    if(this.started==false)
    	return;
    try
    {
      this.mRecorder.stop();
      this.mRecorder.reset();
      this.started = false;
      return;
    }
    catch (Exception localException)
    {
        localException.printStackTrace();
        Log.i("Tag", localException.getMessage());
    }
  }

	public boolean isStarted() {
		return started;
	}
	
	public void setStarted(boolean started) {
		this.started = started;
	}
  
  
  
  
}