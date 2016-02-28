## API Review ##
srt15, jcj26

My discussion will focus specifically on my team's front-end to front-end API
**Part 1**
1. Our API is flexible in the sense that it will be easy to add control elements. However, the front-end to front-end is relatively closed because of its simplicity - we want it to be doing as little work and know as little about itself as possible.
2. There are certain features that we are required to build that do not need to be manipulated in any shape or form. For lack of a better word things just "happen", there is no black box. For instance, when the user clicks on an old command and it fills the text box, this is happening entirely on the front end. This encapsulation is the entire purpose of our front-end to front-end API.
3. As I mentioned before, this API is simple, so we don't expect there to be any errors. We may try and make it so that a programmer can't create elements for attributes that don't exist, but this seems like a low priority issue.
4. Our API design is good because it is so simple, which in my opinion is a big part of the criteria for good API design. It is bad because it doesn't have much room for extension (i.e., it is closed), and it is pretty dependent on our controller class.

**Part 2**
1. N/A
2. The user clicks on any variable, command, or history and it needs to show up in the command box. The user changes either the background color, pen color, or image and this change is reflected immediately but does not effect what is currently on the playground. A programmer wants to add more functionality to the user interface. Potentially, duplicate histories and command are detected and only a single representation shows up in their respective boxes.
3. I'm most excited to work on creating essentially links to code and seeing them fill the command-entry box, but have this operation be entirely hidden form other parts of our code.
4. I'm worried about the user being able to programmatically change things that are currently available in the UI. This might leave us with two methods, one in the front end and one in the back end, that do exactly the same thing.