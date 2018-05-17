package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements GameHelper.GameHelperListener{
	private GameHelper gameHelper;
	private final static int UNUSED_REQUEST_CODE = 1;
	private PlayServices playServices;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.setup(this);
		//incrementAchievement(getAchievementId());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		playServices = new PlayServices(this, gameHelper);
		initialize(new RunnerGame(playServices), config);
	}

	@Override
	public void onSignInFailed() {

	}

	@Override
	public void onSignInSucceeded() {

	}



	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}







}
