# RunnerGame


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

###Game

![Alt text] (/Android/assets/GameGui.jpg?raw=true)

###Game Menu

![Alt text] (/Android/assets/GameMenu.jpg?raw=true)

###Leaderboard

![Alt text] (/Android/assets/GameLeaderboard.jpg?raw=true)