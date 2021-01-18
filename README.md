# The-Easy-Animator

The Application has been divided into three parts that is Model, View and Controller. We are using the mvc controller design pattern to achieve the goal of creating the 
Easy animator where we create , edit or move animation on the board. 

First of all we created the Interfaces and classes of the paramters that represent the shape. Thats is the Color and Position class with their own representive 
interfaces which implement the methods with them. The Color class gets the rgb value of the shape that is in the red, green and blue of the shape to represent them.
 And in the end the Position class represents the position of the shape in the board at some x and y values.

After this we created the Shape interface which represent the all the shapes such as rectangles and oval. Implementing this interface is the AShape which basically the abstract class
which has the common methods for all the shapes such as toString or equals methods which are part of every class. Extending this class is the shape Rectangle and Oval which we need 
to create the shape animation which their own changing and editing in the AnimationController and AnimationView.

Moreoever we Created the IMotion interface which represents motion of the shape between two time frames. The Motion class has functionality of showing the startshape and endshape from 
the start time to endtime. Thus we have methods in that output the toString or equals method for the shape between two interval of the time.
Also added keyframe class which represents the keyframe.

Now we finally initialize the model of the application that is the AnimationModel Interface which the methods for creating and editing the Shapes or the Animation. The class AnimationBuild 
implements this interface. In the class we used three arraylist, first arraylist represents the name of the shape. While the second arraylist has list of motions for that shape and the 
third arraylist represent the keyframe. 

Using this three values we create the methods and implement them with toString methods with the accessibility of the Motion Class. Moreover we implemented the builder class in it also.

Also developed animation controller which controls the program to spit out the visual grapahics. Also created IViewAnimation class with common methods to implement for the 
text, svg and visual view. Futhermore developed the edit animation functanility class called visual view edit which has visualizes the editing panel where the 
user can edit the animation.

In the end we have the main class Excellence which parses throught the files and outputs the type of animation the user wants.
