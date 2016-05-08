###Estimation

* I expect it will not take me very long (maybe an hour) to implement these additions

* I will need to update the turtle playground (which is where the bounds are checked), and create new files for each new command, as well as update the properties file with all the possible actions (which is used to determine if a token from the parser is an action).

###Review

* It took a little under an hour to complete this.

* I update the turtle playground and the possible actions resource file, and added the three commands, but also made a new abstract borderhandler class, and three subclasses (for each type of border). This was to keep the design consistent and to avoid having some sort of indicator value and if-statements in the method that checked the bounds.

* I did not get it right at first, having to decide whether or not I wanted to move the width and height into the new borderhandlers, and based on this, what kind of constructor these would have. I decided not to, and the constructor now takes no parameters to make it easier to make new handlers in the command parser.

###Analysis

* This shows that the design of commands and parsing was very good, since all I had to do was make the new classes and add them to the properties file. The documentation made this evident.

* This exercise revealed that the turtle playground class could have been split up into several classes that had specific responsibilities (to follow the single responsibility principle more closely) such as border handling, turtle movement, etc.

* If I had not been familiar with the code, it would have probably taken me a while to find the class where the borders are set, and I could have forgotten to add the new classes to the properties file. However, given the extensive documentation, I would have figured it out without too much difficulty.
