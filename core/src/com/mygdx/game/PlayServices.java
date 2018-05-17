package com.mygdx.game;


import de.golfgl.gdxgamesvcs.IGameServiceClient;



public interface PlayServices {
        void signIn();
        void signOut();
        boolean isSignedIn();
        void submitScore(int score);
        void incrementAchievement(String achievementID);
        void unlockAchievement(String achievementID);
        void showScores();
        void showAchievements();




        String getReallyBoredAchievementId();
}
