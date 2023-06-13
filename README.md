# Java To-Do List
This program is a simple command-line to-do list application in Java that allows the user to manipulate the list and its items.

Input a task to append it to the list. You can also input slash-commands.

Commands :

- `/add {task}` : appends the task to the list
- `/insert {index} {task}` : inserts the task to the given index
- `/del {index}` : deletes the task at the given index
- `/edit {index} {task}` : replace the task's text at the given index with the new given text
- `/done {index}` : marks or unmarks the task at the given index as done
- `/! {index}` : marks or unmarks the task at the given index as important
- `/clear` : clears the entire list
- `/save {listname}` : saves the current list of task with their properties to a list of the given name ; each time you change the current list, changes will be saved ; the default list at program launch is `todo`
- `/load {listname}` : opens the list with the specified name, and sets it as the current list

Note : indexes in commands start from 1.

You can also directly open a list by passing its name as an argument at launch.
List names cannot contain underscores `_` ; hyphens `-` will be used instead.
