package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements PlayServices, GameHelper.GameHelperListener{
	private GameHelper gameHelper;
	private final static int UNUSED_REQUEST_CODE = 1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.setup(this);
		incrementAchievement(getReallyBoredAchievementId());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RunnerGame(), config);
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

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void submitScore(int score) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_leaderboard), score);
		}
		// uses a leaderboard called "highscores", change it to yours
	}

	// You can choose to receive the increment as an argument, as opposed to always incrementing by 1
	@Override
	public void incrementAchievement(String achievementID) {
		if (isSignedIn())
			Games.Achievements.increment(gameHelper.getApiClient(), achievementID, 1);
	}

	@Override
	public void unlockAchievement(String achievementID) {
		if (isSignedIn())
			Games.Achievements.unlock(gameHelper.getApiClient(), achievementID);
	}

	@Override
	public void showScores() {
		if (isSignedIn()) {
			startActivityForResult(
					Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
							getString(R.string.leaderboard_leaderboard)), UNUSED_REQUEST_CODE);
		} else if (!gameHelper.isConnecting()) {
			signIn();
		}
	}

	@Override
	public void showAchievements() {
		if (isSignedIn()) {
			startActivityForResult(Games.Achievements.getAchievementsIntent(
					gameHelper.getApiClient()), UNUSED_REQUEST_CODE);
		}
	}

	// Sample achievement id getter
	@Override
	public String getReallyBoredAchievementId() {
		return getString(R.string.achievement_youre_a_winner);
	}


}
