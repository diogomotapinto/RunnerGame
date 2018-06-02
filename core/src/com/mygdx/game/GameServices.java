package com.mygdx.game;

/**
 * The Game Services used in the Game.
 */
public interface GameServices {

    /**
     * Sign In User in the Game Services.
     */
    void signIn();

    /**
     * Sign Out User in the Game Services.
     */
    void signOut();

    /**
     * @return If the User is signed in return true, false otherwise.
     */
    boolean isSignedIn();

    /**
     * Submit the scores of the given Level to the Game Services.
     *
     * @param score The score to update with.
     */
    void submitScore(int score);

    /**
     * Increments the Achievements with the given ID.
     *
     * @param achievementID Achievement's ID.
     */
    void incrementAchievement(String achievementID);

    /**
     * Unlocks the Achievement with the given ID.
     *
     * @param achievementID Achievement's ID.
     */
    void unlockAchievement(String achievementID);

    /**
     * Gets the scores of the given level and displays them through google's default widget.
     *
     * @param score achieved in the game.
     */
    void showScores(int score);

    /**
     * Gets the achievements and displays them through google's default widget.
     */
    void showAchievements();


}