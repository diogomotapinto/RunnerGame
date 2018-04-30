# RunnerGame

## Introduction

In this project we ae going to make a game like a endless runner where the more time you spent alive the more rewards you get.
The project will be using LibGdx to help us make a better closs platform game and we will add some features later like a database with Firebase and Google Play Games API in order to manage games services and configure metadata for authorizing and authenticating in our game.

## Architecture Desing


### Desing Decisions

* Model View Controller is the architecture that we are going to use to make the game.

### Expected Design Patterns

*  Singleton - Restricts the instantiation of GameController class

* State Pattern - Will be used to control the Main Menu and the Game flow.

* Null Object Pattern - Used to prevent null checks.

* Game Loop - Used by default by the framework LibGdx that we use.

* Template Method - Used in the desing of some elements of the game, for example all bodies are subclasses of an abstract class EntityBody.


## GUI

### Game

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/master/android/assets/GameGui.jpg)

### Game Menu
![alt text](https://github.com/diogomotapinto/RunnerGame/blob/master/android/assets/GameMenu.jpg)

### Leaderboard

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/master/android/assets/GameLeaderboard.jpg)

The leaderboard it is still subject to many changes because we are going to integrate the android leaderboard api.


## Test 

* Test if the hero dies in case he goes bellow the ground.
* Test if the hero can kill the enemies properly by throwing items at them.
* Test if an enemy can kill the hero by going to an adjacent position or throwing something at him.
* Test if the hero gets his abilities change when he hits milestones or gains rewards by cathcing some items.
