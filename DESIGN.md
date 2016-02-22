Introduction
------------
The goal of this project is to create an environment that can read in SLogo commands and display their output. We will use a model-view-controller in order to implement this: the model (i.e. back-end) will be completely separate from the view (i.e. front-end), and they will only interact through the controller. The model part of the program will be the most flexible, since it needs to allow for changes or updates in the way that inputs are handled, or in the amount of valid inputs. However, this will be implemented using an abstract superclass that will be closed, and subclasses that can later be added for other commands. The controller should also be open, since every new addition to the model needs to be updated in the controller as well. The view will be closed, since it will contain general methods that do not need to be changed. Additions to the view can be made through new classes that will contain these; however, the main view will be closed. The read-eval-print loop will be implemented in the controller.

Design Overview
------------
We utilized the model-view-controller model as an abstract design for our project. The “view” can be broadly described as the “front-end” of the project, responsible for the graphical interface for the program. The “model” represents the “back-end” of the program, accounting for the algorithmic logic which needs to be applied in order to change the view, i.e. responsible for implementing the “rules” of the program. Finally the “controller” is responsible for transmitting information from “view” to “model” and visa versa. The controller essentially connects the front and back-end of the design, which allows for developers to work on the View and Model in isolation and minimizes dependencies between the two.

The four APIs we intend to create can be classified by the information transmitted: front-end to front-end, front-end to back-end, back-end to front-end, and back-end to back-end. Each of these four APIs describe a relationship between the three components of the Model-View-Controller model. The front-end to front-end API encapsulates functionality for when the “view” needs to update itself, i.e. when the front-end needs to be able to change how it looks after it has received information from the controller. The front-end to back-end API encapsulates information transfer from the “view” to the “controller,” i.e. when the front end needs to send information to the back-end. The information is first passed from the view to the controller, which passes the information to the back-end. Once the data reaches the back-end, the back-end to back-end API allows the “model” to update itself, allowing it to perform the calculations which determine “what happens” in the simulation of the logic based on user input. Finally, once the model has finished its calculations, it transmits the new info which is used to update the view utilizing the back-end to front-end API.
	
The “view” represents the program’s literal user interface; in Slogo, that encapsulates what the user is able to view when they decide to run the program. The “view” allows for the front-end to be manipulated in isolation of the back-end by transmitting information to the “controller,” which in turn passes that information to the back-end. The “view” is unable to access information from the “model” and ought to be developed in isolation from it. In Slogo, the view is in charge of displaying the pane containing the program itself, the toolbar, including the buttons needed to allow for user interactions, and the three major UI boxes: a turtle box, which displays the turtle inside of its playground, an input box, where the user types their commands, and an output box, which outputs a history of turtle commands which the user has inputted in the past. The output box reflects the “parsed” command which the back-end has to feed to the “view” utilizing the controller.
	
The “controller” allows for information to be transmitted from the “view” to the “model” and visa versa. When a user inputs a command, the “controller” sends that string to the “model”, which parses it, and sends the turtle command information back up to the “controller.” Once the controller receives the turtle command information, it is able to update the “view” based on the new information. The “controller” is crucial for connecting the front and back end for this project because it allows for user-inputted data to be sent to the back-end, allows for the back-end to parse it in isolation, and allows for the back-end to communicate its parsed information back to the “view” in order to update the turtle. 
The “model” serves as the algorithmic behemoth in our program; it receives information from the front-end through the controller, and based on the input, utilizes the program’s logic in order to return a unique output. The “model” is unable to access information from the “view” and ought to be developed in isolation from it. In Slogo, the model is in charge of parsing the user’s commands into turtle commands and updating the position of the Turtle as a result of the command. Once it is able to store/update the “new” position of the turtle, it sends that information back up to the “controller” to be sent to the “view,” in order to reflect the change in position of the turtle on the screen for the user to observe.

The image below maps out our implementation of the model-view-controller design for Slogo. 
	
The “view” only has one class, the MainView class. The MainView class contains an instance of the MainController class in order to allow for data to be transmitted from the “view” to “controller,” allowing for front-end to back-end API. In addition, it contains a variety of instance variables and methods which allows for front-end to front-end API. For example, the program is contained within a Pane, myPrimaryPane. The methods createPaneElement() and placePaneElement() allow for front-end elements to be added to the “view,” depicting front-end to front-end API functionality. 
	
The “controller” also only has one class, the MainController class. The MainController class has an instance of both the MainModel and MainView in order to allow for information to be transmitted from the front to back-end and visa versa, allowing for front-end to back-end API and back-end to front-end API. It also has an instance of a Timeline, which allows for the controller to send information from the front-end to the back-end, and then send it back up. The MainController has a variety of setter methods which manipulate the front-end view of the program, for example, setTurtleColor(). The readCommand() method allows for text commands which the user inputs into the input UI box to be sent to the MainModel to be parsed, part of the front-end to back-end API. After the information has been parsed and processed by the “model,” the updateDisplay() and refreshDisplay() methods allow this information to be sent back up to the view in order to manipulate the graphical interface of the program, part of the back-end to front-end API. 
	
The “model” encapsulates a multitude of classes. The MainModel class contains instances of the MainController class, the Parser class, the History Class, the TurtlePlayground class, and the Variables class. The MainController object, myController allows for back-end to front-end API, transmitting information from the algorithmic back-end to the front end in order to update the view based on user input. The Parser class allows for the parsing of string commands which the user inputs through the view through the parseCommands() method. The History class keeps track of the turtle commands which the user has inputted in chronological order, stored as a queue. The getHistory() method returns the queue of turtle commands, while the add() method adds a command to the queue. The TurtlePlayground class contains an instance of the Turtle class in addition to the boundary coordinates for the turtle’s playground, the UI box in which the turtle moves around. The turtle class contains instances of the turtle’s coordinates, allowing it to be “self-aware” of where it is within the larger turtle playground. In addition, it contains a boolean, penDown, which determines whether the turtle should leave a line while moving around in the turtle playground.  Finally, the Variables class contains a Map which maps text commands to variable values. These methods are part of the back-end to back-end API, allowing for the back-end to send information within itslef in order to compute the proper algorithmic outcome of a user’s inputs.
	
The MainModel class acts as a “wrapper” to these five other major classes in the “model” of this design. Rather than implementing all of the functionality encapsulated in those classes within the MainModel, we decided to delineate out functionality to these five other classes. The methods in the MainModel class are getters which allow for developers to access these five other classes, allowing for back-end to back-end API.

API’s
------------
**FRONT-END internal:**
* one class for now, closed but 
* display(Stage stage)*
  * Scene myScene=new Scene(myPrimaryRoot);
  * Add scene dimensions
  * stage.setScene(myScene)
  * stage.show();
* initRoot()*
  * Calls button creating functions--wrapper class
  * createPaneElement();
  * placePaneElement();
* Node createPaneElement(String title, ActionEvent event);
* placePaneElement(Node element, Position pos);
  * myPrimaryPane.set();
* addPaneElement(Pane, Node)*
* formatPaneElement(Map<String, String>)*
* Group myRoot
* Pane myPane
* making buttons/combo boxes
* selection of history code
* errors/exceptions
* primaryroot -> primary pane ->all the stuff
  * toolbar
  * turtle
  * command, input
*properties/etc
* Action Events:
  * setTurtleColor(Color color)
  * updateModel()


**external:** 
* Controller myController
* addCommand(String input)
* setTurtleImage(Color color)
* setBGColor(Color color)
* setTurtleLocation()

**BACK-END**
**internal:**

**external:**
* Controller
* updateTurtleInfo()

User Interface
------------
The program has four major components: three UI boxes and a toolbar. The turtle playground is contained in the bottom-left half of the program; this UI box contains the turtle and depicts it moving around. The input box and output box share the bottom-right half of the program. The input UI box allows the user to input string commands to move the turtle around in the turtle playground. The output UI box prints out the relevant turtle command “translation” based on the string which the user inputted. The output box prints out the results of back-end computation. The toolbar is above these three UI boxes; it contains a variety of buttons which allows the user to manipulate the front-end of the program, for example, by allowing them to step through the commands which the user has inputted.

The primary method in which the user will interact with the program is through entering text commands in order to move the turtle. The user inputs these commands in one of three UI boxes, the command input box. Once the user inputs a command, the back-end parses it in order to determine where the turtle will move. Once it has parsed the String command and has calculated the path of the turtle, the “model” sends that information back up to the view through the controller, resulting in the parsed command being displayed in the output UI box and the turtle moving in the turtle playground, the third major UI box.
In addition to inputting commands to the input UI box, the user also interacts with the program through a series of buttons contained within the toolbar. These buttons allow the user to interact and manipulate the front-end of the program, for example, by allowing them to step through commands, select Slogo file, or change the color of the turtle.
The program also will throw a variety of errors to the user. There are three major classes of errors: errors in parsing, out of bounds error, and an error in Slogo file. An error in parsing will be thrown if the user inputs a command with incorrect or unrecognized syntax: this can happen if the user misspells a command or enters a command which does not exist or if the user does not have the adequate parameters to run a command. The out of bounds error is thrown when the turtle is instructed to move outside of the boundaries of the turtle playground. The error in slogo file is thrown when the user attempts to load a file which has an error, either because it has incorrect syntax or because it is an empty file.

API Details 
------------
**Front-end to front-end**
* The MainView class contains everything that the user sees and interacts with. It allows for the creation of these elements, and exchange of information that does not require assistance from the back-end. It does not manipulate any data itself, but merely represents it. The MainView class will send information to the MainController (and eventually MainModel class), which will appropriately update that data, and return it to the MainView class to be displayed.
* This API supports all features that have no need to work with the either the MainController or MainModel classes. Everything in the front-end to front-end API is strictly to be used for the MainView class only.
* We have decided to structure the front-end of our program this way because it takes away many responsibilities, which increases our flexibility and decreases our dependencies. 
  * There are cases where information doesn’t necessarily have to be sent to the back-end, such as the user selecting a new background or pen color, but if we allow this change to exist only in the front-end, then there will not only be two methods that do the same thing, but also inconsistencies between the information that exists among our three main classes.
* The front-end to front-end API is relatively closed. Based on the way we have designed it, we don’t foresee the need for expansion. Its expectations, power, and responsibilites are clear and limited.
* Potential exceptions include trying to create displays elements for settings that don’t exist or placing them in illegal positions, such as positions that are off the screen or overlapping previously created elements.
  * Our front-end to front-end API is relatively simple and the need for throwing exceptions is not nearly as important as other API’s.

**Front-end to back-end**
* This API supports essentially all the features as most of the features involve changing aspects of the back-end. The resources used are in the main package and the model package, though mostly involving the MainController and MainModel classes.
* MainController is intended to be the main handler for manipulating all the relevant information stored in the model, and will be extended to include additional requirements by specifying new functions which toggle or send new information to the model. 
* The design’s key goals is to keep distinct MVC components which separates the information and the display. The MainController class acts as the intermediary and highway of information exchange between the two. 
* The relevant methods include various setters within the MainController which call corresponding update methods within the Model (by doing myModel.<command>). An example of this would be setTurtleColor, which would call myModel.getTurtlePlayground.getTurtle.setColor(). 
* Exceptions to be thrown would be invalid inputs, like a turtle color that does not exist within JavaFX, or null pointer exceptions, for example if a turtle’s attributes do not exist or cannot be changed to a certain value.

**Back-end to front-end**
* This API will pass information from the model so that it can be displayed. It uses the model package and the main package, mostly through the maincontroller.
* The controller will use the method refreshDisplay to get the information from the model and pass it into the display. Since all of the changes will be previously done in the back-end, the refresh method will call on getters for the objects in the model, in order to get the information that needs to be displayed.
* The goal of having this is to find a way to return whatever information was passed from the interface (this would be the “print” part of the read-eval-print loop).
* Exceptions would come from the back-end and be passed to the front-end.

**Back-end to back-end**
* This API links the pieces used in the back-end (Turtle, turtleplayground, parser, etc). The resources it will use are in the model package. This will be used to parse and interpret the user input, and to check that these are properly formatted.
* The main class will be CommandParser, which will have instances of the other objects. Turtle and turtleplayground will be used to control the turtle, history will be used to keep track of the commands that have already been executed, and variables will keep track of the variables and their expressions. We created these classes in order to keep the components separate and to encapsulate details that would not be necessary in other parts of the program. The abstract action class will be extended to create new actions to be executed based on the input commands. This allows for extension in the future (such as new types of commands).
* The purpose of this API is to evaluate the commands in a way that is flexible and extendable.
* Relevant methods are getters/setters in all classes, and other methods that control the objects (e.g. turnTurtle, etc). 
* Exceptions would be thrown in the commandparser class if the input is incorrect, or if the turtle is out of bounds.

API Example Code
------------
**Sequence of code for the use case:**
* MainView’s inputBox element is set on action to read the content text when the user presses enter which tells the MainController to handle it (runCommand)
* MainController’s runCommand function calls the Model to parse and run the command (readCommand) and then updates the UI elements (refreshDisplay)
  * readCommand will use the Parser contained within the Model and change the coordinates in the Turtle (located within the TurtlePlayground). Then it will create a collection of coordinates that is added to the History that it returns to the Controller
  * the controller’s refreshDisplay function will translate the collection of turtle coordinates to draw out the lines by connecting the dots for the coordinates and update the MainView’s UI element accordingly

**Sequence of code for front-end for creating text input box:**
* MainView’s primaryStage is set to the stage given in the constructor and MainController is set through setController
* initializeRoot creates the primaryRoot on which the scene is anchored
  * calls createTextInputBox function which creates the input box and calls MainController to take in the input on user pressing enter
  * adds input box to primaryRoot
* display function creates a scene anchored on primaryRoot, sets the scene on primaryStage, and displays the scene

**Sequence of code for back-end for moving the turtle forward by 50 pixels.**
* MainView’s inputBox element is set on action to read the content text when the user presses enter which tells the MainController to handle it (runCommand)
* MainController’s runCommand function calls the Model to parse the string input.	
* MainModel readCommand method takes in the string as a parameter in order to calculate the algorithmic output as a result of that input.
  * The CommandParser class parses the string using the parse method into the function call and its parameters.
  * The CommandParser class then uses the ParseCommands method in order to call the right instance of the action class.
* Once the correct action class is initialized, the rules() method in that action class is called. The action class is an abstract super class which extends into sub-classes for different functions within Slogo.
  * Every action class has an instance of a TurtlePlayground. The rules() method interacts with the TurtlePlayground in order to move the turtle forward by 50 pixels.
* The TurtlePlayground has an instance of the Turtle class, in addition to instances of its width and height.
  * The Turtle class has an instance of the turtle’s x and y coordinates, in addition to a float representing its orientation and booleans about whether the pen is down and whether the turtle is visible.
  * The rules() method calls the moveTurtle() method, utilizing “50” from the parsed string as its parameter.
  * The moveTurtle() method changes the x and y Coordinate of the turtle as per its orientation as long as it is within the boundaries of the playground.
  * It does this by utilizing the inBounds() method, which checks if the updates x and y coordinate are within the Width and Height boundaries provided in the TurtlePlayground class. 
  * If the new coordinates are within bounds, the moveTurtle() method updates the turtle’s position, effectively moving it forward by 50 pixels.

**Sequence of code for selecting and running a command from the history, as well as updating the history in general**
* An element that exists in MainView’s myHistoryBox is selected by the user and inputBox fills with that chunk of code, via the front-end to front-end API.
* The user presses enter and that code that exists in the box runs as it normally would (see above use case examples for more detail).
* MainModel adds the code that it has received from MainController to its myHistory collection using its add(String command) method, and the code that was just run now exists as the most recently run code. The history will be represented as a queue, so everything that previously existed in myHistory is remains in its correct relative position.
  * We may need to consider limiting the size of myHistory such that only x number of elements can be recalled at once.
* Upon updating, myController can see this change by calling the getHistory() method, and pass the changes along to MainView. 
  * The user sees that the myHistoryBox has grown by one element and every previous entry has been bumped down one position. 
* Note: if a user selects code from myHistoryBox and runs it, will appear twice in myHistoryBox. The old code is not deleted or moved. So as an extreme edge case, the user could have a history of fifty or more elements where every element in myHistoryBox and myHistory is identical.

Design Considerations
------------
controller responsibilities: We discussed the importance of having a controller class, and how much should be done within this class as opposed to in the mainview class. An advantage of having methods such as setTurtleColor, etc inside the mainview class would be that these would be encapsulated within the view and thus not accessible to any other classes. However, a disadvantage would be that the view would need to do a lot of the work in terms of processing and accessing data from the back-end. This would mean that the view would not be closed, since with each addition of a new feature, this class would have to change. Thus, we decided that all of these methods would be part of the controller class, in order to provide more flexibility as well as a more structurally sound design, which properly separates the front and back ends.

turtle vs turtleplayground: The distinction between turtle and turtleplayground was a decision that we made based on the fact that the turtle’s path drawing needs to be independent from the turtle itself. Furthermore, this increases the flexibility of our code, allowing for future extensions that will let the playground have different properties. The cons of having distinct playground and turtle objects is that (at least for this sprint), it is not really necessary to have a distinction between the two, and the addition of an object (especially since it contains an instance of the other) could complicate things.

inheritance: we finally decided to have abstract classes for exceptions and errors, and for commands so that we can extend these later. The pros of having superclasses are that these are flexible and can be extended. However, the cons are that these will take more effort/space. We discussed the possibility of using inheritance in other parts of the program, but decided against it because we only need one of each class, and the view does not need that kind of flexibility.

Team Responsibilities
------------
Srikar and Huijia will work on the back-end part of the program, which consists of the model and its associated classes. Sam and Frank will work on the front-end, which is the view class. Since the controller is part of both front and back end, we will meet and write it together.
