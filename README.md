![New](https://user-images.githubusercontent.com/57855070/72677249-0f5c2a00-3aa3-11ea-9093-3e35b6520854.png)

This project is the construction of one game with two modes of automatic play and manual play.
The game board is built from a graph based on the follwing project:

https://github.com/TomLatin/Object-Oriented-Project-2

In addition, there are "fruits" and "robots" on the game board.
In the manual game, the player's goal is to get the robots to eat as much "fruit" as possible with their robots, while in the automatic game the player watches a game automatically run by an algorithm TSP.
There are 24 stages in the game that are numbered from 0 to 23 when the amount of fruits and robots is different in each game,At the beginning of each game you can choose what stage the player wants to play.

In our game we booted the robots to be superheroes and the fruits to be power stone.

![image](https://user-images.githubusercontent.com/57855070/72686656-b373be80-3aff-11ea-9fb5-a46c44d2dbcc.png)

![image](https://user-images.githubusercontent.com/57855070/72686583-f84b2580-3afe-11ea-8fae-fb86c09cc557.png)

in addition to the main classes that we have int the project that we based on

In addition to the main departments of the project on which we were based, other major class were added:

## elementsInTheGame:
We added an interface that the same elements found on the game board will implement, this interface inherits the node_data interface (which is in the project we were based on).

The three main functions of the interface are:
* Save from a string that is in JSON format
* Determines an image for an object
* Returns the image of the object

## Robot:
Each robot has the following components:
* id: Robot Number
* value: The amount of fruit the robot was able to collect
* src: The vertex from which the robot came out
* dest: The vertex to which the robot will go
* speed: The speed of the robot
* pos: The location of the robot
* Pic: The image that represents the robot
* path: The robot's way to the fruits
* robotFruit: The fruits of the robot

The main function of the department is insert data from JSON file that came from the server into object Robot 

## Robot contain:
Represents a collection of robots. We chose to represent the collection in an array of robots.
This class gets the stage the player chooses and resets the parameters according to that stage.This is done by a constructor that receives the stage number.

The main functions of the department:
* initToServer: Insert data Robot the server,The function receives an array of integers.
* init: insert data into object RobotContain from the server. 
* getNumOfRobots: the function return the num of robots.

## Fruit:
Each Fruit has the following components:
* value: The value of the Fruit
* type: The type of the Fruit, can be -1 or 1
* Point3D pos: The location of the Fruit in the graph
* Pic: the picture of the Fruit

The main function of the department is insert data from JSON file that came from the server into object Fruit 

## Fruit contain:
Represents a collection of fruits. We chose to represent the collection in an array of fruits.
This class gets the stage the player chooses and resets the parameters according to that stage.This is done by a constructor that receives the stage number.

The main functions of the department:
* init: insert data into object FruitsContain from the server. 
* getNumOfFruits: the function return the num of fruits.

## My game GUI:
In this department all the GUI is managed and also the manual game management is in this department.
There are 2 THREDS in this department that are responsible for the visibility of the game in front of the player and the other one for KML files that we will detail.

## My game algo:
In this class all the automatic game algorithm is found, we are based on an algorithm called TSP.
We also use the dijkstra algorithm to calculate the shortest path.

Our algorithm idea is that every robot will have a list of way that is updated according to the new fruits added to the game board, the lists of robots will not overlap.
Adding a new fruit to a robot list will be after testing that pays to add the fruit to the list.

## KML logger:
With this class we can represent the fruits and robots in Google Earth and also see the whole course of the game displayed there continuously.
To do this, we used:
https://labs.micromata.de/java-api-for-kml/

![WhatsApp Image 2020-01-19 at 22 00 47](https://user-images.githubusercontent.com/57855070/72687532-482dea80-3b07-11ea-8c4f-2e2b7b5fd2e7.jpeg)















