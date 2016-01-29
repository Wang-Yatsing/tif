package com.WYX.tif;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.graphics.*;
import java.util.*;

public class MainActivity extends Activity
{
	static int step=3;
	static boolean isRed=true;
	static t[] T;
	static boolean over=true;
	static TextView ok;
	static ArrayList<Integer> clickable;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
		clickable=new ArrayList<Integer>();
		ok=(TextView)findViewById(R.id.ok);
		ok.setBackgroundColor(Color.GRAY);
		ok.setText("開始 遊戲");
		ok.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					clickable.clear();
					if(over){
						for(int i=0;i<T.length;i++){
							T[i].clear();
							clickable.add(T[i].xulie);
						}
						ok.setBackgroundColor(Color.RED);
						ok.setText("輪到 紅方");
						isRed=true;
						over=false;
						step=3;
					}else if(step!=3){
						isRed=!isRed;
						if(isRed){
							ok.setBackgroundColor(Color.RED);
							ok.setText("輪到 紅方");
						}else{
							ok.setBackgroundColor(Color.BLUE);
							ok.setText("輪到 藍方");
						}
						for(int i=0;i<T.length;i++){
							if(!T[i].clicked){
								clickable.add(T[i].xulie);
							}
						}
						step=3;
					}
				}
			});
		t t1=new t((TextView)findViewById(R.id.t1),new int[]{1},0);
		t t2=new t((TextView)findViewById(R.id.t2),new int[]{0,2,7},1);
		t t3=new t((TextView)findViewById(R.id.t3),new int[]{1,3},2);
		t t4=new t((TextView)findViewById(R.id.t4),new int[]{2,4,9},3);
		t t5=new t((TextView)findViewById(R.id.t5),new int[]{3,5},4);
		t t6=new t((TextView)findViewById(R.id.t6),new int[]{4,6,11},5);
		t t7=new t((TextView)findViewById(R.id.t7),new int[]{5},6);
		t t8=new t((TextView)findViewById(R.id.t8),new int[]{1,8},7);
		t t9=new t((TextView)findViewById(R.id.t9),new int[]{7,9,12},8);
		t t10=new t((TextView)findViewById(R.id.t10),new int[]{3,8,10},9);
		t t11=new t((TextView)findViewById(R.id.t11),new int[]{9,11,14},10);
		t t12=new t((TextView)findViewById(R.id.t12),new int[]{5,10},11);
		t t13=new t((TextView)findViewById(R.id.t13),new int[]{8,13},12);
		t t14=new t((TextView)findViewById(R.id.t14),new int[]{12,14,15},13);
		t t15=new t((TextView)findViewById(R.id.t15),new int[]{10,13},14);
		t t16=new t((TextView)findViewById(R.id.t16),new int[]{13},15);
		T=new t[]{t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16};
    }
}
class t{
	TextView t;
	int[] ns;
	boolean clicked;
	int xulie;
	t(TextView tv,int[] ns,int xulie){
		this.ns=ns;
		this.t=tv;
		this.xulie=xulie;
		set();
	}
	private void check(boolean isRed){
		int j=0;
		for(int i=0;i<MainActivity.T.length;i++){
			if(MainActivity.T[i].clicked){
				j++;
			}
		}
		if(j==16){
			MainActivity.over=true;
			MainActivity.ok.setBackgroundColor(Color.GRAY);
			if(isRed){
				MainActivity.ok.setText("紅方 失敗!");
			}else{
				MainActivity.ok.setText("藍方 失敗!");
			}
		}
	}
	public void set(){
		clear();
		t.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					if((!clicked)&&MainActivity.step>0&&MainActivity.clickable.contains(xulie)){
						clicked=true;
						if(MainActivity.isRed){
							t.setBackgroundColor(Color.RED);
						}else{
							t.setBackgroundColor(Color.BLUE);
						}
						if(MainActivity.step==3){MainActivity.clickable.clear();}
						else {MainActivity.clickable.remove((Object) xulie);}
						for(int i=0;i<ns.length;i++){
							if(!MainActivity.T[ns[i]].clicked){
								MainActivity.clickable.add(ns[i]);
							}else{
								MainActivity.clickable.remove((Object)ns[i]);
							}
						}
						MainActivity.step--;
						check(MainActivity.isRed);
					}
				}
			});
	}
	public void clear(){
		clicked=false;
		t.setBackgroundColor(Color.GRAY);
	}
}
