# RunnerGame

<img src='https://bettercodehub.com/edge/badge/diogomotapinto/RunnerGame?branch=master&token=9663dbc582b445b7bcf545095b01846171153ad5'>

|Table of Contents|
|:---------------:|
|[Introduction](#introduction)|
|[Setup/Installation ](#setup/installation)|
|[Package and Class Diagram](#package-and-class-diagram)|
|[Desing Decisions](#design-decisions)|
|[Design Patterns](#design-patterns)|
|[GUI](#gui)|
|[Considerations](#considerations)|

## Introduction

In this project we are going to make a game like a endless runner where the more time you spent alive the more rewards you get.
The project will be using LibGdx to help us make a better closs platform game and we will add some features later like a database with Firebase and Google Play Games API in order to manage games services and configure metadata for authorizing and authenticating in our game.


## Setup/Installation 

* ### To install in your Android device
1. Download the .apk [here] (https://drive.google.com/open?id=1BW6xXyQi1L-81G1K1c5cBdyu6COrSmh2) .
2. Install the .apk.


* ### To install the desktop app
 1. Download the .jar [here] (https://drive.google.com/open?id=1ezaNQjUotT0H41VbviDVH9qCewwHPYRh) .
 2. Run by clicking on it.


* ### Install the development environment
1. Clone or Download the repository and open it with Android Studio.
2. Change the workspace to the android/assets folder.
3. Change the file local.properties and put your android sdk path.
4. To install the game just run on your android device or emulator. 
You can also run it in Desktop Mode but you won't be able to use the Google Play Services

## Architecture Desing

## Package and Class Diagram

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/body.png)

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/model.png)

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/game.png)

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/screens.png)

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/view.png)






### Design Decisions

* Model View Controller is the architecture that we are going to use to make the game.

### Design Patterns

* State Pattern - Used to control the flow of the game. The state of the hero let us know if he is dead,
and in that case the game is over. It is also used to test some elements of the game properly by knowing the state of the hero.

* Null Object Pattern - Used to prevent null checks on the Desktop launcher because the desktop
does not use the Google Play Services.

* Game Loop - Used by default by the framework LibGdx that we use.

* Template Method - Used in the desing of some elements of the game, for example all bodies are subclasses of an abstract class EntityBody.

* Object Pool - Used to manage the bullets in the game.

* Factory - Used to get the Entity to be drawn according to the type received.


## GUI

### Game Menu

In order to start the game just press anywhere on the screen.

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/StartMenuScreen.jpeg)


### Game

The game is all about catching or shooting the gold coins in the air that will appear in random positions of the map.
The more you catch the highest score you'll get but be careful because the enemy will steal points from you so don't touch him.
The left side of the screen is used to jumping and the right side to shoot you also have to tilt your device in order to run forward.
The controls on the Desktop app are space to run,  press 's' to shoot and and up key to jump.


![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/game.jpeg)


### Game Over

The left button is used to restart the game, the middle one is to check the achievements you got and
the right button is to check you highest score achieved and compare to the other users.

 ![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/gameOverScreen.jpeg)
 
 


### Leaderboard

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/LeaderBoardScreen.jpeg)

### Achievements

![alt text](https://github.com/diogomotapinto/RunnerGame/blob/finalRelease/android/assets/AchievementsScreen.jpeg)



## Test 

* Test if the hero dies in case he goes bellow the ground.
* Test if the hero is affected by an impulse and running properly.
* Test if an hero is affected by an impulse and jumping properly.
* Test if the score does not change randomly.

## Considerations

* ### Lessons learned 
    With this projected we learned how to make an Android application using the some design patterns 
    to simplify our life and make the clone cleaner and simpler for someone to understands what it does
    and for us to use it.
    We also learned how to find a solution for our problems efficiently because we worked with a framework 
    that none of us knew and that required us to look for the best solution possible to fix our problems 
    and make our vision possible.
    
* ### Overall time spent developing
    Around 100 hours evenly distributed by all the elements of the group.
    
    
 * ### Major difficulties along the way
    The principle difficulty of this project was implementing the Google Play Services, 
    this was possible with some research and a tutorial made by two students of our College.
    [Setup Google Play Services in LibGDX](https://noprogramming.xyz/2017/setup-google-play-services-in-libgdx).
    
  * ### Notes
    The BaseGameUtils package was not made by us, it is library we used to implement the Google Play Services.
