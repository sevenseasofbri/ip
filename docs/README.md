# User Guide

## Features 

### Add a to-do task: `todo`
Adds a simple to-do task to the task list.

#### Usage

#### `todo <description>` 
A task with the description specified is added to the task list.

Example of usage: 

`todo Read book.`

Expected outcome:
```
____________________________________________________________
Added:[T][✘]  Read book.
Now you have 1 task in the list :D
____________________________________________________________
```
### Add a deadline: `deadline`
Adds a deadline type task to the task list.

#### Usage

#### `deadline <description> /by <yyyy-mm-dd>` 
The deadline with the description and due date specified as yyyy-mm-dd is added to the task list.

Example of usage: 

`deadline Finish exam revision. /by 2020-10-10`

Expected outcome:
```
____________________________________________________________
Added:[D][✘]  Finish exam revision. (by:Oct 10 2020)
Now you have 2 tasks in the list :D
____________________________________________________________
```
### Add an event: `event`
Adds an event type task to the task list.

#### Usage

#### `event <description> /at <yyyy-mm-dd>` 
The event with the description and date specified as yyyy-mm-dd is added to the task list.

Example of usage: 

`event CS2113T Final Exam. /at 2020-12-01`

Expected outcome:
```
____________________________________________________________
Added:[E][✘]  CS2113T Final Exam. (at:Dec 1 2020)
Now you have 3 tasks in the list :D
____________________________________________________________
```

### List all tasks: `list`
Lists all tasks in the task list with completion status.

#### Usage

#### `list` 
The tasks are listed down on the screen in the order in which they were added along with completion status (depicted as a ✘ or ✓ ).

Example of usage: 

`list`

Expected outcome:
```
_______________________~Your List~__________________________
1. [T][✘]  Read book.
2. [D][✘]  Finish exam revision. (by:Oct 10 2020)
3. [E][✘]  CS2113T Final Exam. (at:Dec 1 2020)
____________________________________________________________
```

### Mark a task as done: `done`
Change the completion status of a task to done/complete.

#### Usage

#### `done <task number>` 
Marks the task specified by the task number as done (✘ to ✓).

Example of usage: 

`done 2`

Expected outcome:
```
____________________________________________________________
Awesome! I've marked this task as done:
[D][✓] Finish exam revision.(by:Oct 10 2020)
Only 2 to go! ;)
____________________________________________________________
```

### Delete a task: `delete`
Delete a task from the task list.

#### Usage

#### `delete <task number>` 
Deletes the task specified by the task number from the list.

Example of usage: 

`delete 1`

Expected outcome:
```
____________________________________________________________
Noted! I've removed this task: 
[T][✘] Read book.
Now you have 2 tasks in the list :D
____________________________________________________________
```

### Locate a task by a keyword: `find`
Obtain a list of tasks with task descriptions matching search keyword.

#### Usage

#### `find <keyword>` 
Finds and lists tasks whose descriptions contain the keyword specified.

Example of usage: 

`find exam`

Expected outcome:
```
______________________~Tasks Found~_________________________
1. [D][✓] Finish exam revision.(by:Oct 10 2020)
____________________________________________________________
```

### Exit the application: `bye`
Exits the program.

#### Usage

#### `bye` 
Finishes execution of program and closes application.

Example of usage: 

`bye`

Expected outcome:
```
____________________________________________________________
Farewell. Until next time my dude.
____________________________________________________________
```

### Saving the data
Task data is saved automatically in the hard disk for each change in the data.
There is no need to save data manually. This data is loaded the next time the 
application is opened.