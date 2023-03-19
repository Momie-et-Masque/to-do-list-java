# to-do-list-java
This program is a simple command-line to-do list application in Java that allows the user to add, edit and delete tasks.

The program uses an ArrayList object to store the tasks and a Scanner object to read user input from the command line.

The main method uses a while loop to continuously prompt the user for input and display the current list of tasks. If the user enters a command (starting with a forward slash), the program parses the command and calls the appropriate method (appendTask, editTask, or deleteTask) with the appropriate arguments. If the user enters a regular task (not starting with a forward slash), the program adds the task to the ArrayList using the appendTask method.

The appendTask method simply adds a task to the end of the ArrayList.

The editTask method is not currently used in the program, so it can be removed.

The deleteTask method attempts to parse the indexStr argument as an integer and removes the task at that index (adjusted for zero-based indexing) from the ArrayList. If an exception is thrown (indicating that the argument was not an integer), the program simply ignores the command and continues.

The lastArg method is a utility method that extracts the last argument from a command string (split by spaces). It is used to extract the task description from the "/add" command.

Overall, this program provides a basic framework for a to-do list application, but it is missing several important features such as saving tasks between sessions and providing more advanced editing capabilities.