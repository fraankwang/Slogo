API_EXERCISE.md

## 1. Critique a previous API
http://www.cs.duke.edu/courses/compsci308/spring16/classwork/06_slogo_api/cellsociety_apis/
api_cellsociety_team07.txt

### Animal Class

* Should not be part of API: all methods are useful because the Animal API is designed to be accessed extensively within the Grids
* External API: all getters
* Internal API: all setters
 
### Fish Class
* Should not be part of API: the constructor should definitely be part of the API. There are no other methods in this class

### Shark Class
* Should not be part of API: isStarving is very similar to timeToBreed within the Animal class. This could be refactored into a health getter method.
* External API: all, because Shark is an object used extensively within the Grid and its attributes are used for calculations and algorithmic placements
* Internal API: none, all external use

### Cell Class
* Should not be part of API: all methods should be part of this class. This is a superclass for the main components that each Grid uses. Therefore all methods are for external use and not for internal calculation.
* External API: all
* Internal API: none


### Grid Class
* Should not be part of API: All methods should be part of API because they are all crucial for either animating the simulation or rendering the GUI.
* External API: All methods are external.
* Internal API:  setParams 

### Fire Grid
* Should not be part of API: All methods should be part of API because generateGrid(), generateGivenGrid(), and setParams() are all called in order to generate the simulation specific simulation.
* External API: All external.
* Internal API:  No internal.

### Game of Life Grid
* Should not be part of API: All methods should be part of API because generateGrid(), generateGivenGrid(), and setParams() are all called in order to generate the simulation specific simulation.
* External API: All external.
* Internal API:  No internal.

### Predator Prey Grid
* Should not be part of API: setParamsToAnimal() should not be public because it is only used within the class.
* External API: generateGrid(), generateGivenGrid(),
* Internal API:  getEmptyNeighbors, getFishNeighbors, putAnimal, and moveAnimal

### Segregation Grid
* Should not be part of API: All methods should be part of API because generateGrid(), generateGivenGrid(), and setParams() are all called in order to generate the simulation specific simulation.
* External API: All external.
* Internal API:  No internal.

### Sugarscape Grid
* Should not be part of API: All methods should be part of API because the constructor and setParams() are called in order to generate the simulation specific simulation.
* External API: All external.
* Internal API:  No internal


### ContentRetriever  class
* Should not be part of API: buildRow, which makes an int[]. This should be private.
* External API: all getters and ispreconfigured. These will be used to build the grid, etc.
* Internal API: none, since the point of this class is to get content for other classes.

           
### BorderPane class
* Should not be part of API: constant
* External API: launchGUI, since it’s needed for other things
* Internal API: GUI, because it might be extended to add other features
       
 
### PaceButton class

* Should not be part of API: none
* External API: none
* Internal API: PaceButton, because it is used in the GUI

### Spashscreen class

* Should not be part of API: 
* External API:
* Internal API: SplashScreen because it is used in the GUI


### Toolbar class
* Should not be part of API: iscreated, which can be checked with != null. addButtons, createToolbar which should be private.
* External API: none
* Internal API: Toolbar, because it is created and passed on to something else.



