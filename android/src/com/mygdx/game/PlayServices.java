package com.mygdx.game;


import android.app.Activity;

import com.badlogic.gdx.Gdx;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;


/**
 * Class that interacts with the Google Play Services.
 */
class PlayServices implements GameServices {

    /**
     * Code for unused Requests.
     */
    private final static int UNUSED_REQUEST_CODE = 1;

    /**
     * The Play Services' activity class.
     */
    private final Activity activity;

    /**
     * Game Helper used to help with to communicate with Google Play Services.
     */
    private final GameHelper gameHelper;

    /**
     * Play Services class constructor.
     *
     * @param activity   The Activity used in the Play Services.
     * @param gameHelper The GameHelper used in the Play services.
     */
    PlayServices(Activity activity, GameHelper gameHelper) {
        this.activity = activity;
        this.gameHelper = gameHelper;
    }


    @Override
    public void signIn() {
        try {
            activity.runOnUiThread(() -> gameHelper.beginUserInitiatedSignIn());
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }


    @Override
    public void signOut() {
        try {
            activity.runOnUiThread(() -> gameHelper.signOut());
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }


    @Override
    public void unlockAchievement(String achievementID) {
        if (isSignedIn())
            Games.Achievements.unlock(gameHelper.getApiClient(), achievementID);
    }


    @Override
    public void incrementAchievement(String achievementID) {
        if (isSignedIn())
            Games.Achievements.increment(gameHelper.getApiClient(), achievementID, 1);
    }


    @Override
    public void showScores(int level) {
        showScores(getLeaderboardID());
    }

    /**
     * Show the scores of a given leaderboard.
     *
     * @param leaderboardID The leaderboard ID to show the score from.
     */
    private void showScores(String leaderboardID) {
        if (isSignedIn()) {
            activity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    leaderboardID), UNUSED_REQUEST_CODE);
        } else if (!gameHelper.isConnecting()) {
            signIn();
        }
    }


    @Override
    public void showAchievements() {
        if (isSignedIn()) {
            activity.startActivityForResult(Games.Achievements.getAchievementsIntent(
                    gameHelper.getApiClient()), UNUSED_REQUEST_CODE);
        }
    }


    @Override
    public void submitScore(int score) {
        String leaderboardID = getLeaderboardID();
        String achievementID = getAchievementID(score);

        if (leaderboardID != null)
            submitScore(leaderboardID, score);

        if (achievementID != null)
            unlockAchievement(achievementID);
    }

    /**
     * Getter for an achievement's ID.
     *
     * @return The achievement ID.
     */
    private String getAchievementID(int score) {

        if (score == 10) {
            return activity.getString(R.string.achievement_noob);

        } else if (score == 100) {
            return activity.getString(R.string.achievement_wasspoppin);
        } else if (score == 200) {
            return activity.getString(R.string.achievement_fireee);
        }
        return null;
    }

    /**
     * Submit the given score to the given leaderboard.
     *
     * @param leaderboardID Leaderboard ID.
     * @param score         Score to update the Leaderboard.
     */
    private void submitScore(String leaderboardID, int score) {
        if (isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                    leaderboardID, score);
        }
    }

    /**
     * Getter for each level leaderboard.
     *
     * @return the leaderboard ID.
     */
    public String getLeaderboardID() {

        return activity.getString(R.string.leaderboard_leaderboard);

    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }


}