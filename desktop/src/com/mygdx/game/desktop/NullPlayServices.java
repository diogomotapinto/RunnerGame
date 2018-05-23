package com.mygdx.game.desktop;

import com.mygdx.game.GameServices;

public class NullPlayServices implements GameServices {
    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void submitScore(int score) {

    }

    @Override
    public void incrementAchievement(String achievementID) {

    }

    @Override
    public void unlockAchievement(String achievementID) {

    }

    @Override
    public void showScores(int level) {

    }

    @Override
    public void showAchievements() {

    }

    @Override
    public void rateGame() {

    }
}
