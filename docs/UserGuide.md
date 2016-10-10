# User Guide

* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
1. Download the latest `SmartScheduler.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for the program.
3. Double-click the file to start the app. The GUI should appear in a few seconds. 
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window. 
5. Some example commands you can try:
   * **`show`**` today` : lists all tasks scheduled for today
   * **`add`**` buy eggs by 5pm today` : 
     remind yourself to get some eggs by 5pm today
   * **`delete`**` 1` : deletes the first task shown in the to-do list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional parameters.
> * Items with `...` after them can have multiple instances.
> * Flexible ordering of parameters.

#### Adding a task : `add`
Adds a task, deadline, event or a floating task to the list. <br>
Format: `add TASKNAME [at/from TIMEDATE] [by/to TIMEDATE] [p/PRIORITY]`

> * For tasks and events, at least one of the two TIMEDATE values must be included.<br>
> * For floating tasks, none of the TIMEDATE values are specified.<br>
> * If the taskname contains keywords like "add", the user can enter it using the escape character '\'.
* `add Shop \at Topshop at 5pm`
> * Priority must be low/med/high. If priority is not specified, default priority of low is set.
> * If START_TIMEDATE exceeds END_TIMEDATE, the addition of task will not be successful.

Examples: 
* `add Buy eggs at 5pm 13/09/2016 p/high`
* `add Meeting from 13/09/2016 5pm to 13/09/2016 7pm p/med`
* `add Pay bills by 13/09/2016 5pm`
* `add Do laundry p/low`

#### Displaying tasks : `show`
Displays tasks and their indexes in the specified timeframe.<br>
Format: `show TYPE`

> * TYPE can be replaced with p/high, p/med, p/low, complete, all, overdue, floating, today, tomorrow, upcoming, or any specific date.
> * p/high, p/med, p/low stand for tasks with high priority, medium priority and low priority respectively.
> * Except for show complete, all other inputs for TYPE will only display the incomplete tasks, i.e., the tasks which have not been marked as done.

Examples:
* `show p/high`<br>

> * Lists all tasks marked as high priority

* `show tomorrow`<br>

> * Lists all tasks for tomorrow

* `show 30/10/2016`<br>

> * Lists all the tasks scheduled on 30th October, 2016

#### Searching for tasks : `find`
Lists tasks whose names match the given input.<br>
Format: `find SEARCHSTRING`

> * The search is case insensitive. e.g `buy` will match `Buy`
> * Wild cards can be indicated with an asterisk `*` e.g. `B* eggs` will match `Buy eggs`
> * Only the name is searched.
> * Tasks containing the entered text will be matched e.g. `Buy` will match `Must buy eggs`

Examples: 
* `find b*y`<br>

> * Returns both `buy` and `buy eggs`

#### Deleting a task : `delete`
Deletes the specified task.<br>
Format: `delete INDEX/TASKNAME`

> * Deletes the task at the specified index
> * If TASKNAME is entered instead, and only one task matches the name, it will be deleted. 
> * If TASKNAME is entered, and there are multiple tasks with matching names, then they are all displayed along with their indices. In this scenario, the user can only proceed with deletion using the index of the appropriate task.

Examples:
* `delete 1`

> * Deletes task at index 1

* `delete b*y`

> * If only 1 task matches the entered text, it is deleted. Otherwise, the tasks matching `b*y*` will be listed for the user to choose from.

#### Updating a task : `update`
Updates information for a task referred by its index.<br>
Format: `update INDEX [TASKNAME] [at/from [START_TIME][START_DATE]] [to/by [END_TIME][END_DATE]] [p/PRIORITY]`

> * Replaces the entered information for the task at the specified index
> * If updated START_TIME/DATE exceeds END_TIME/DATE of the task, the update will not be successful

Examples:
* `update 1 at 13/09/2016 5pm`<br>

> * `Meeting from 13/09/2016 4pm to 13/09/2016 6pm` will be replaced with `Meeting at 13/09/2016 5pm`

* `update 1 from 13/09/2016 4pm to 13/09/2016 6pm`

> * `Meeting at 13/09/2016 5pm` will be replaced with `Meeting from 13/09/2016 4pm to 13/09/2016 6pm`

* `update 1 p/high`<br>

> * The priority of the task at index 1 is updated to high.

#### Marking a task as complete : `done`
Marks a task as complete.<br>
Format: `done INDEX/TASKNAME`

> * Marks the task at the specified index as complete
> * If TASKNAME is entered, tasks are sought out in the same way the `find` command does. If only one task is found, it will be marked as done. Otherwise, matching tasks and their indices are displayed. The user can then proceed with marking a task as complete using the index of the appropriate task.

Examples:
* `done 1`
* `done b*y`

#### Setting the storage location: `setstorage`
Sets the data storage location <br>
Format: `setstorage FILEPATH`

#### Undo previous action(s): `undo`
Undo the latest change made to the task list.
Format: `undo`

> * Allows undo to up to 3 previous changes to the task list

#### Redo previous action(s): `redo`
Redo the latest change that was reverted using undo.
Format: `redo`

> * Allows redo to up to 3 consecutive undo operations done
> * Does not work if the undo command was not used just before this command

#### Viewing help : `help`
Format: `help`

> * Help is also shown if you enter an incorrect command e.g. `abcd`.

#### Clear all tasks : `clear`
Clears all tasks from the to-do list.<br>
Format: `clear`

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
The task manager data is saved on the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SmartScheduler folder.
       
## Command Summary

Command | Format  
-------- | :--------
add | `add TASKNAME [at/from TIMEDATE] [to/by TIMEDATE] [p/PRIORITY]`
show | `show [TYPE]`
find | `find SEARCHSTRING`
delete | `delete INDEX/TASKNAME`
update | `update INDEX [TASKNAME] [at/from [START_TIME][START_DATE]] [to/by [END_TIME][END_DATE]] [p/PRIORITY]`
done | `done INDEX/TASKNAME`
setstorage | `setstorage FILEPATH`
undo | `undo`
redo | `redo`
help | `help`
clear | `clear`
exit | `exit`
