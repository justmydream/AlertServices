package com.example.alertservice;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainSettingAdapter  extends ArrayAdapter<SettingData>{
	private ArrayList<SettingData> items;
	CheckBox cb_Check;
	
	public MainSettingAdapter(Context context, int textViewResourceId,
			ArrayList<SettingData> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v= convertView;
		if(v==null){
			LayoutInflater vi=(LayoutInflater)getContext().
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.main_setting, null);
			}
		SettingData settingdata = items.get(position);
		if(settingdata != null){
			TextView tv_Main = (TextView)v.findViewById(R.id.settingMenu);
			TextView tv_Sub = (TextView)v.findViewById(R.id.settingMunuInfo);
			cb_Check = (CheckBox)v.findViewById(R.id.settingCheck);
			
			tv_Main.setText(settingdata.getMain_Title());
			tv_Sub.setText(settingdata.getSub_Title());
			cb_Check.setClickable(false);
			cb_Check.setFocusable(false);	
			
			
    		 
    			
		   cb_Check.setChecked(items.get(position).isSettingcheck());	
		   
		   
			   
		   }
		
			if(position==1|position==4|position==5|position==6|position==8|position==9){				
				cb_Check.setVisibility(View.INVISIBLE);
			}
			else if(position==0){
				cb_Check.setVisibility(View.VISIBLE);
			}
		return v;
	}
}

class SettingData{
	private String Main_Title;
	private String Sub_Title;
	private boolean settingcheck;
	


	public SettingData(String main_Title, String sub_Title, boolean settingcheck) {
		
		this.setMain_Title(main_Title);
		this.setSub_Title(sub_Title);
		this.setSettingcheck(settingcheck);
	}
	public String getMain_Title() {
		return Main_Title;
	}
	public void setMain_Title(String main_Title) {
		Main_Title = main_Title;
	}
	public String getSub_Title() {
		return Sub_Title;
	}
	public void setSub_Title(String sub_Title) {
		Sub_Title = sub_Title;
	}
	public boolean isSettingcheck() {
		return settingcheck;
	}
	public void setSettingcheck(boolean settingcheck) {
		this.settingcheck = settingcheck;
	}
	
	
	
	
}

