package com.example.alertservice;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.YuvImage;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;


public class MainLocation extends MapActivity
{
  MapView mapView;
  MapController mapCtrl;
  //��ġ������ �����ϴ� �ٿ�

  String locationProvider;
    //��ġ ���� �Ŵ��� ��ü

    LocationManager locationManager;
    Location location;
    //���� ������ ���� �浵 GeoPoint�� �����ڷ� ������ ��

    int latitude, longitude;
    
    TextView locationText;
    
    Button locationButton1;
    Button locationButton2;
    Button locationButton3;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_location);
        
    locationButton1 = (Button)findViewById(R.id.locationButton1);
        locationButton2 = (Button)findViewById(R.id.locationButton2);
        locationButton3 = (Button)findViewById(R.id.locationButton3);
        locationText = (TextView)findViewById(R.id.locationText);
        
       locationButton1.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {	
				 locationText.setText("����");
				 locationButton1.setBackgroundColor(Color.GRAY);
				 locationButton2.setBackgroundColor(Color.BLACK);
				 locationButton3.setBackgroundColor(Color.BLACK);
				 mapView.setSatellite(false); //��������				
				 }
		});
        locationButton2.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {	
				locationText.setText("����");
				 locationButton1.setBackgroundColor(Color.BLACK);
				 locationButton2.setBackgroundColor(Color.GRAY);
				 locationButton3.setBackgroundColor(Color.BLACK);
				mapView.setSatellite(true);  //��������			
			}
		});locationButton3.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				locationText.setText("�� ��ġ");
				 locationButton1.setBackgroundColor(Color.BLACK);
				 locationButton2.setBackgroundColor(Color.BLACK);
				 locationButton3.setBackgroundColor(Color.GRAY);
				GeoPoint gPoint3=new GeoPoint(latitude, longitude);
			    mapCtrl.animateTo(gPoint3);//������ġ
			}
		});

        //MapView ��ü�� ������ ������
        mapView=(MapView)findViewById(R.id.mapView);
        //�ܱ��
        mapView.setBuiltInZoomControls(true);
        //���� �浵�� �Է��ϱ� ���� ��ü �����ϱ�
        GeoPoint gPoint=new GeoPoint((int)(37.566912*1E6), 126978517);
        //��ġ �������� ���� ��Ʈ�ѷ� ��ü
        mapCtrl=mapView.getController();
        //������ ���� ��Ÿ���� �ִ� �߽����� GeoPoint ��ü�� ���� �ȴ�� ����
        mapCtrl.setCenter(gPoint);
        //������ ����
        mapCtrl.setZoom(16);     

        //��ġ ���� �Ŵ��� ��ü ������
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //��ġ���� ������ ������ provider=null�̶�� ������ ������ manifest���� ���Ѽ����� �ȵ��ִ� ���̴�.
        //�����ڴ� GPS, Network ���߿� 1������ String���� ���´�.

        locationProvider = locationManager.getBestProvider(new Criteria(), true);

        Toast.makeText(this, "��ġ���� ������:"+locationProvider, 0).show();
        //���� �ֱ��� Location ��ü ������
        Location location = locationManager.getLastKnownLocation(locationProvider);
        

        if(location != null ){

         //�Ǽ��� ������� ���� �浵���� GeoPoint ��ü�� �����ڷ� ������ �� �ִ� ���·� ��ȯ
         latitude = (int)(location.getLatitude()*1000000);
         longitude = (int)(location.getLongitude()*1000000);
        }
    }
    
    @Override
    protected void onResume() {
     super.onResume();
     locationManager.requestLocationUpdates(locationProvider, 5000, 10, listener);//5��, 10���� ����     

    }

    @Override
    protected void onPause() {
         super.onPause();
         locationManager.removeUpdates(listener);
    }   

    //locationListener��ü �����
    LocationListener listener = new LocationListener() {
   //���°� �ٲ���� ��

   public void onStatusChanged(String provider, int status, Bundle extras) {
    String msg = "";
    switch(status){
    case LocationProvider.OUT_OF_SERVICE :
     Toast.makeText(MainLocation.this, "��ġ ������ �̿��� �� �����ϴ�.", 0).show();
     break;
    case LocationProvider.TEMPORARILY_UNAVAILABLE :
     Toast.makeText(MainLocation.this, "�Ͻ������� �̿��� �� �� �����ϴ�.", 0).show();
     break;
    case LocationProvider.AVAILABLE :
     Toast.makeText(MainLocation.this, "��ġ ������ �̿� �����մϴ�.", 0).show();
     break;
    }

   }
   //�����ڰ� ���� �����ϰ� �Ǿ��� ��
   public void onProviderEnabled(String provider) {
    Toast.makeText(MainLocation.this, "��ġ ������ �̿� �����մϴ�.", 0).show();
   }  
   //�����ڰ� ���� ���ϰ� �Ǿ��� ��
   public void onProviderDisabled(String provider) {
    Toast.makeText(MainLocation.this, "��ġ ������ �̿��� �� �����ϴ�.", 0).show();
   }
   //��ġ ������ �ٲ������ ȣ��Ǵ� �޼ҵ�
   public void onLocationChanged(Location location) {
    //������ ������ġ �����
    mapView.removeAllViews();   
    //������Ʈ �� ��ġ���� �Է¹ޱ�
    latitude = (int)(location.getLatitude()*1000000);
         longitude = (int)(location.getLongitude()*1000000);
         GeoPoint gPoint3=new GeoPoint(latitude, longitude);

      //�ʺ信 �̹����� ����غ���
    //ImageView ��ü ����
    ImageView image2=new ImageView(MainLocation.this);
    image2.setImageResource(R.drawable.ic_launcher);
    //�ʺ信 �̹��� ��ġ�� ���� ������ ���� LayoutParams ��ü �����ϱ�
    MapView.LayoutParams lp=new MapView.LayoutParams
      (LayoutParams.WRAP_CONTENT,  // ������ ��
      LayoutParams.WRAP_CONTENT,   // ������ ����
      gPoint3, //�̹����� ��Ÿ�� ��ġ
      MapView.LayoutParams.CENTER); //�߾ӿ� ����
    //�ʺ信 �̹��� ����ϱ�
    mapView.addView(image2, lp);

    //�»�ܿ� ���ڿ� ��ġ�ϱ�
    TextView text=new TextView(MainLocation.this);
    text.setText("������ġ");
    text.setTextSize(30);
    text.setTextColor(Color.RED);
    //���ڿ��� ��ġ�ϱ� ���� ���̾ƿ��Ķ� ��ü����
    MapView.LayoutParams lp2=new MapView.LayoutParams
      (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,  gPoint3, //��ġ
      MapView.LayoutParams.LEFT | MapView.LayoutParams.TOP);
    //TextView ��ü ��ġ�ϱ�
    mapView.addView(text, lp2);
    mapCtrl.animateTo(gPoint3);    
    }
  }; 
 /* //�ɼ� �޴� �����
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
   menu.add(0,1,0,"����");
   menu.add(0,2,0,"����");
   menu.add(0,3,0,"������ġ");
   return true;
  }
  //�ɼǸ޴��� ���õǾ����� ����Ǵ� �޼ҵ�
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
   //������ �ɼ� �޴� ���̵� �о�ͼ� �ٸ� ������ �ϰ� �Ѵ�.
   switch(item.getItemId()){
   case 1: 
    mapView.setSatellite(false); //��������
    break;
   case 2:
    mapView.setSatellite(true);  //��������
    break;  
   case 3:
	   GeoPoint gPoint3=new GeoPoint(latitude, longitude);

    //wlehfmf ���� ��ġ�� ǥ��

    mapCtrl.animateTo(gPoint3);




    break;
   }
   return true;
  }*/
  //�߻� �޼ҵ� �������̵� 
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

  @Override
  protected boolean isRouteDisplayed() {
	  return false;
  }
  
}



