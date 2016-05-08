
* Estimation: before looking at the old code:
	* How long do you think it will take you to complete this new feature?
		* I estimate this will take no longr than 30 minutes.
	* How many files will you need to add or update? Why?
		* I need to add the additional images to be used.
		* Updated files: MenuBarFactory. This feature was already included in the original completed code.
Review: after completing the feature:
	* How long did it take you to complete this new feature?
		* This feature took little more than 30 minutes. 
	* How many files did you need to add or update? Why?
		* I changed many of the image loading methods to just use the correct file path within the local directory to avoid complications. Other files did not really need to be changed as this was coded into the functionality the first time.
	* Did you get it completely right on the first try?
		* Yes, it just took some debugging to fix the file pathing for image loading.

Analysis: what do you feel this exercise reveals about your project's design and documentation?
	* Was it as good (or bad) as you remembered?
		* Our design is not super robust, but many of the functionalities are encapsulated well within their respective classes and locations. The Constants also single out where images can be added and located, which created an easy and central access point.
	* What could be improved?
		* Our code could include fewer hardcoded strings and overall be tidier in terms of linking the frontend and backend (i.e. using the observer/observable model) but all in all the functionality still works.
	* What would it have been like if you were not familiar with the code at all?
		* This code would have been difficult to run. Tracing the code, however, you can find the options in the MenuBar created in the MenuBarFactory, and the file paths and image nages located within the Constants folder. All in all, this code was very easy to modify, and things were easy to locate, exemplifying, to some extent, the maintainability of our code.