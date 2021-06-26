package com.skripsi.pendeteksikebakaran.framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

import java.util.HashMap;

public class FragmentActivityFramework extends FragmentActivity {

	protected Activity a;
	protected HashMap<String, String> user_session;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeSystem();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

	private void initializeSystem() {
		a = this;
	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_OK);
		overridePendingTransition(0, 0);finish();
	}
}