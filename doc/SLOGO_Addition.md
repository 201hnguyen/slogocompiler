CompSci 308: SLogo Addition
===

# Before

### Estimation

 * How long do you think it will take you to complete this new feature?

    I expect to complete this feature in 25~35 minutes.
    
 * How many files will you need to add or update? Why?

    I will need to add two new classes that will implement the stamp and clearstamps command.
    Then, I will need to change the DefinedCommand.properties file so that the the user command can
    be recognized in the Parser class. Then, I will need to add simple changes to the TurtleHistory class
    so that the backend can record all the stamps that need to be drawn. This information will be saved to another field variable
    called 'myStamps'. The frontend will somehow go through this information, but that frontend code will not be
    part of this extension. Thus, I will need to add 2 files and update 2 files.

# After

### Review

 * How long did it take you to complete this new feature?
 
    It took about 15 minutes.

 * How many files did you need to add or update? Why?
 
    I added 2 files and updated 2 files, for the same reason that I wrote in the Estimation part. 

 * Did you get it completely right on the first try? If not, why not?

    I got it right on the first try. It does not show up in the frontend as that was not part of the extension, 
    but the logic were not hard to implement in the backend because that part was already flexble enough.

### Analysis

 * What do you feel this exercise reveals about your project's design and documentation --- was it as good (or bad) as you remembered?
 
    I feel that this exercise reveals the fact that my project's backend part, especially the part related to
    adding new commands and using TurtleHistory class to store all the changes that need to show up in the
    frontend, is very flexible. All I needed to do was add two implementation of the BasicCommandInterface,
    and make a new public method in the TurtleHistory class so that it is possible to add stamps. The factory pattern
    based on reflection is already ready to recognize the command and call the right implementation of the BasicCommandInterface,
    so I did not need to do anything else.

 * What about the design or documentation could be improved?
 
    I think the DefinedCommands.properties file could be improved a bit better, as there might be
    cases in which the command might need to take more or less than just a pre-defined number of parameters.
    In the current design, the number of parameter is pre-defined and only that number of parameters can be used.
    However, I feel that this might be a factor that can reduce the flexibility, and may be improved by making it possible
    to write syntaxes like "2<"(more than 2 parameters), "0<="(any number of parameters), "3<"(less than 3 parameters).

 * What would it have been like if you were not familiar with the code at all?
    
    I would have needed to spend some time trying to understand the TurtleHistory class. However, I feel that the way
    that new commands need to be added is very clear and easy. Thus, even if I wasn't familiar with this code, as long as I have
     an understanding of how reflection works and thus understood that a change in DefinedCommands file and adding that class is all
     that I needed to do, it would have been very easy to add these classes. Thus, understanding TurtleHistory class and adding new methods
     would have been the only hard part.