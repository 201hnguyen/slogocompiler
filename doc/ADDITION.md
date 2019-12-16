 # Addition
 
 ## Estimation:
 ### How long I think it will take: 
 
 30 minutes - 1 hour
 
 ### How many files to add and update:
 
 I will need to add 2 files: one for the Stamp command and one for the ClearStamps command. I will also need to change a
 properties file (though I cannot remember the exact name) that defines the commands in order to allow these to be recognized
 as valid commands. Last, I may need to change the TurtleHistory class to process these commands. 
 
 ## Review: after completing the feature:
 ### how long did it take you to complete this new feature?
 It took me around 30 minutes of reading and 15 minutes of coding to complete this feature.
 ### how many files did you need to add or update? Why?
 I updated 5 files: the 4 files I mentioned for the reasons I mentioned above, as well as the `TurtleModel` class in order
 to add a list of stamps that the turtle currently hold.
 ### did you get it completely right on the first try?
 I did not get it on the first try, and took several read-throughs in order to completely get it right.
 ### Analysis: what do you feel this exercise reveals about your project's design and documentation?
 I think that this project reveals that the project's design is fairly easy to understand, although the documentation 
 itself can be improved. A lot of what I understood about the project came through because I had to actually delve into 
 the code and scroll through; I felt that the documentation did not help my understanding that much. 
 ### was it as good (or bad) as you remembered?
 I think it was as good as I remembered it, as things are easy to implement just as they were the first time around.
 ### what could be improved?
 At least on just the features related to this new addition, I don't think much needs to be improved besides the slight
 improvement in documentation. I think that the code was straight-forward enough to provide information about how to 
 implement the feature. However, I did go back through and read some of the code for the Controls, which I contributed to more.
 I think those code can be improved in terms of how the parameters are passed. Currently, they are passed in a 
 `List<Object>` and I found that to be very confusing when trying to read through with a fresh memory. They concealed a lot
 of what the objects themselves should be.
 ### what would it have been like if you were not familiar with the code at all?
 I am actually not too familiar with the implementation of the code, as I worked on a different part of the engine; however,
 I was familiar with the concept about the `TurtleHistory`, `TurtleModel`, and `TurtleView` classes and how they function
 in the code as a whole so it made the task easier. However, had I not been familiar with the code at all, I think that the 
 turtle history class, because it is so central yet has a bit of limited documentation, can be a bit confusing.
 