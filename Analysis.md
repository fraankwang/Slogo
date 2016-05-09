CompSci 308: Slogo Masterpiece
===================


###Estimation: before looking at the old code:
> - How long do you think it will take you to complete this new feature?

I do not think it will take me long to complete this feature because I do not need to modify any already existing architecture (1 hour).

> - How many files will you need to add or update? Why?
I think I need to create three classes to the action hierarchy in order to implement the three new functionalities. I think I


###Review: after completing the feature:
> - How long did it take you to complete this new feature?
 
In total, it took me around two hours to complete this new feature.
> - How many files did you need to add or update? Why?

I added four class files and modified two existing classes in order to implement the new window behavior commands. Although the original project had not implemented window behavior functionalities, it had assumed the logic of the "fence" command: the user could only move the turtle upto the boundaries of the turtle playground. In order to implement all three Slogo functions, I had to add three corresponding classes to the action inheritance hierachy. These classes extended the TurtleCommands abstract class which further extended from the Action abstract class. As a result, these function utilized the action inheritance hierarchy in implementing the rules() method required by the Action class while having access to the TurtlePlayground from the TurtleCommands class. I did not have to modify the existing design architecture for the framework because the commands only needed access to the TurtlePlayground. 

In addition, I created a new enumeration class, WindowBorderBehavior, which contained the three possible window behavior options: window, fence, and wrap. I created this enumeration because I decided to implement the new functionality through utilizing the states design pattern in TurtlePlayground. Rather than creating new classes to represent each new behavior, I believe that my utilization of this design pattern allowed for a mere modification of only the TurtlePlayground class and the possibleactions.properties resource file. 

In order to implement the functionality of the newly created action classes through its rules() method, I had to manipulate only the TurtlePlayground class: I added a private instance of a WindowBorderBehavior and modified the setTurtleCoordinates() method. The setTurtleCoordinates() method originally moved the turtle depending on whether the new position commanded by the actor was within the boundaries of the playground (assumed the logic of the fence state). As a result, I changed this method into a switch statement which updated the turtle's coordinatees based on the playground's current WindowBorderBehavior state. As a result, the rules() method for each respective WindowBorderBehavior merely sets the WindowBorderBehavior state of the TurtlePlayground.

Finally, I had to modify the possibleactions.property resource file in order to allow for the CommandParser to construct the relevant Action for the new functions. I had to add the (key) user inputted string as the key to (value) the file path of the action. 


> - Did you get it completely right on the first try?

It took me a while to remember that I had to update the resources file. Although the exception generated helped me identify the source of error quicker by identifying it as a problem with parsing. I completely forgot about having to update the resource file for reflection to work.


###Analysis: what do you feel this exercise reveals about your project's design and documentation?
> - Was it as good (or bad) as you remembered?

I believe that this exercise demonstrated the modularity and extensibility of the design of our project: the existing architecture provides sufficient flexibility in order to incorporate new functionality (much of it was detailed in the Review above). Although the project had not considered window border behavior as a relevant design parameter, I was able to incoporate the new functions within the existing infrastructure. I only had to create four classes (one of which is an enumeration) and modify two files. 


> - What could be improved?
There ought to have been more delegation in order to narrow the scope of classes in order to ensure any lack of dependencies. Although our design was modular, the TurtlePlayground class ought to further delegate functionality to closed compositional element who can store, manage, and update their own information (Example: Delegating bounds functionality to a BoundsHandler object).

> - What would it have been like if you were not familiar with the code at all?
I believe that adding this functionality would not have been difficult due to the design on the back-end. Due to the lack of dependencies between objects on the back-end, there is a great amount of modularity and flexibility in the number of different methods and design patterns a particular function can be implemented in.


