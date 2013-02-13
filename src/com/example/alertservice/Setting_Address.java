package com.example.alertservice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Setting_Address extends Activity
implements AdapterView.OnItemSelectedListener , AdapterView.OnItemClickListener
{
	
	
	Toast mToast;
	private ArrayList<SettingAddress> ArraySet;
	ListView listView;
	Setting_Address self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_address);
        self = this;
    	
        SharedPreferences pref = getSharedPreferences("PrefTest",MODE_PRIVATE);
        int totalNum = pref.getInt("totalAddressNumber", 0);
                	
        
		ArraySet = new ArrayList<SettingAddress>();
		
		if(totalNum>0)
        {
			
			String saved_address[] = pref.getString("AllAddress", "").split(",");
			
			if(saved_address!=null)
			{
				for(int i=0 ; i<saved_address.length ; i=i+2)
				{
					ArraySet.add(new SettingAddress(0, saved_address[i], saved_address[i+1]));
				}
			}
			
	        listView = (ListView)findViewById(R.id.addressList);
			listView.setAdapter(new AddressListViewAdapter        		
			        (self,android.R.layout.simple_list_item_1,ArraySet));
	        //어뎁터까지 위젯에 연결하면 어뎁터에서 항목을 만들어서 제공해주기 때문에 해당
	        //위젯에 항목이 출력됨.
	      
	        listView.setOnItemClickListener(self);
	        listView.setOnItemSelectedListener(self);     
	        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	        
	       
        }
        
        
        
        ImageButton contactBtn = (ImageButton)findViewById(R.id.contactBtn);
        
        contactBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				startActivityForResult(intent, 1001);
				
			}
		});
        
        
        ImageButton fin = (ImageButton)findViewById(R.id.addressFin);
        fin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences pref = getSharedPreferences("PrefTest", MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putInt("totalAddressNumber", ArraySet.size());
				StringBuffer saved_add = new StringBuffer();
				for(int i=0 ; i<ArraySet.size() ; i++)
				{
					saved_add.append(ArraySet.get(i).getName());
					saved_add.append(",");
					saved_add.append(ArraySet.get(i).getpNumber());
					saved_add.append(",");
				}
				
				editor.putString("AllAddress", saved_add.toString());
				editor.commit();
				
				Toast.makeText(self, "저장되었습니다.", Toast.LENGTH_SHORT).show(); 
				
				self.finish();
				
				
			}
		});
        
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                Cursor c = null;
                try {
                    c = getContentResolver().query(uri, new String[] {
            				ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            				ContactsContract.CommonDataKinds.Phone.NUMBER,
            				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
                            null, null, null);
                    if (c != null && c.moveToFirst()) {
                        int id = c.getInt(0);
                        String name = c.getString(2);
                        String pNumber = c.getString(1);
                        
                        boolean isadded=false;
                       if(ArraySet.size()>0)
                       {
                    	   for(int i=0 ; i<ArraySet.size();i++)
                    	   {
                    		   if(ArraySet.get(i).getId()==id)
                    		   {
                    			   isadded=true;
                    		   }
                    	   }
                       }
                        
                       if(!isadded)
                       {
                    	   ArraySet.add(new SettingAddress(id, name, pNumber));
           				
	           				listView = (ListView)findViewById(R.id.addressList);
	           				listView.setAdapter(new AddressListViewAdapter        		
	           				        (self,android.R.layout.simple_list_item_1,ArraySet));
	           		        //어뎁터까지 위젯에 연결하면 어뎁터에서 항목을 만들어서 제공해주기 때문에 해당
	           		        //위젯에 항목이 출력됨.
	           		      
	           		        listView.setOnItemClickListener(self);
	           		        listView.setOnItemSelectedListener(self);     
	           		        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                       }
                        
                       /* if (mToast != null) {
                            mToast.cancel();
                        }
                        String txt = ":\n" + uri + "\nid: " + id
                        		+ "\nname: " +name
                        		+ "\nphoneNumber: "+pNumber;
                        		
                        mToast = Toast.makeText(this, txt, Toast.LENGTH_LONG);
                        mToast.show();*/
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
            }
        }
		
		
		
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub		
		ArraySet.remove(arg2);
		listView.setAdapter(new AddressListViewAdapter        		
			        (self,android.R.layout.simple_list_item_1,ArraySet));
		
		
		
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
}
