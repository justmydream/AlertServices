package com.example.alertservice;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class AddressListViewAdapter extends ArrayAdapter<SettingAddress>{
	
	private ArrayList<SettingAddress> items;
	SettingAddress settingaddress;
	ListView listView;
	

	public AddressListViewAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}


	public AddressListViewAdapter(Context context, int textViewResourceId, ArrayList<SettingAddress> items) {
		super(context, textViewResourceId, items);
		
		this.items = items;
		
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v= convertView;
		if(v==null){
			LayoutInflater vi=(LayoutInflater)getContext().
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.address, null);
			}
		
		settingaddress = items.get(position);
		
		if(settingaddress != null){
			TextView tv_name = (TextView)v.findViewById(R.id.settingName);
			TextView tv_PNumber = (TextView)v.findViewById(R.id.settingPNumber);
			tv_name.setText(settingaddress.getName());
			tv_PNumber.setText(settingaddress.getpNumber());
			   
		   }
		return v;
	}

}







class SettingAddress{
	private int id;
	private String name;
	private String pNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpNumber() {
		return pNumber;
	}
	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	public SettingAddress(int id, String name, String pNumber) {
		super();
		this.id = id;
		this.name = name;
		this.pNumber = pNumber;
	}
	
}
