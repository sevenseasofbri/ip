# User Guide
- [User Guide](#user-guide)
  * [Requirements](#requirements)
  * [Quick Start](#quick-start)
  * [Features](#features)
    + [Print command summary: `help`](#print-command-summary---help-)
    + [Add a to-do task: `todo`](#add-a-to-do-task---todo-)
    + [Add a deadline: `deadline`](#add-a-deadline---deadline-)
    + [Add an event: `event`](#add-an-event---event-)
    + [List all tasks: `list`](#list-all-tasks---list-)
    + [Mark a task as done: `done`](#mark-a-task-as-done---done-)
    + [Delete a task: `delete`](#delete-a-task---delete-)
    + [Locate a task by a keyword: `find`](#locate-a-task-by-a-keyword---find-)
    + [Exit the application: `bye`](#exit-the-application---bye-)
    + [Saving the data](#saving-the-data)
  * [Command Summary](#command-summary)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>
## Requirements
Ensure you have Java **11** or higher installed on your computer.

## Quick Start
* Download the ip.jar file from the releases.
* Copy the file to a folder where you would want to store the task list data.
* Double click jar file to start the application. If this does not open the app
then open the terminal and make sure you are in the folder with the jar file. Type
the following command: `java -jar ip.jar`
* Once the app starts, type a command in the command line and press _enter_ to execute it.
* Some example commands (does not cover all commands):
  1. `list`: List all contacts
  2. `help`: Display command summary.
  3. `todo Buy groceries.`: Adds a task with `Buy groceries.` as description.
  4. `done 1`: Marks 1st task in the list as done.
  5. `delete 2`: Deletes the 2nd task in the list.
  6. `bye`: Exits the app
* The features section below has details of **all** commands.
 
## Features 

### Print command summary: `help`
Prints list of commands and their respective formats.

#### Usage

#### `help`
A list of available commands are printed out.

Example of usage:

`help`

Expected outcome:
```
____________________________________________________________
Here's a command summary to help you out! You may also refer to sevenseasofbri.github.io/ip for the User Guide ;)
COMMAND SUMMARY
Note: Text in "<>" are parameters to be specified by you!
1. Add a to-do task: todo <description>
2. Add a deadline: deadline <description> /by <yyyy-mm-dd>
3. Add an event: event <description> /at <yyyy-mm-dd>
4. List all tasks: list
5. Mark a task as done: done <task number>
6. Delete a task: delete <task number>
7. Locate a task by a keyword: find <keyword>
8. Exit the application: bye
____________________________________________________________
```

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

## Command Summary
Command | Format | Example
:------------: | :-------------: | :-------------:
todo | `todo <description>` | `todo Read book.` 
deadline | `deadline <description> /by <yyyy-mm-dd>` | `deadline Finish exam revision. /by 2020-10-10`
event | `event <description> /at <yyyy-mm-dd>` | `event CS2113T Final Exam. /at 2020-12-01`
list | `list` | `list`
done | `done <task number>` | `done 2`
delete | `delete <task number>` | `delete 1`
find | `find <keyword>` | `find exam`
bye | `bye` | `bye`
