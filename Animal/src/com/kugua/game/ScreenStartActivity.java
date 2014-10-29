package com.kugua.game;

import android.app.Activity;
import android.os.Bundle;

public class ScreenStartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
	}

}
