package com.harlan.libs.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.harlan.libs.R;
import com.harlan.libs.utils.ToastUtil;
import com.harlan.libs.view.viewgroup.ArcMenu;
import com.harlan.libs.view.viewgroup.ArcMenu.OnItemClickListener;

public class ArcMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arcmenu);
		
		ArcMenu arc = (ArcMenu) findViewById(R.id.arc);
		
		arc.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onClick(View view, int position) {
				ToastUtil.Short(getApplicationContext(), view.toString() + ":" + position);	
			}
		});
	}
}
