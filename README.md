# Payment Tracker
## The task
Write a program that keeps a record of payments. Each payment includes a currency and an amount. Data should be kept in memory (please don’t introduce any database engines).

The program should output a list of all the currency and amounts to the console once per minute. The input can be typed into the command line with possibility to be automated in the future, and optionally also be loaded from a file when starting up.

### Sample input:
![Sample input image](https://i.imgur.com/b40MIdS.png)

### Sample output:
![Sample output image](https://i.imgur.com/Mti3UM8.png)

## Detailed requirements:
When your Java program is run, a filename can be optionally specified. The format of the file will be one or more lines with Currency Code Amount like in the Sample Input above, where the currency may be any uppercase 3 letter code, such as USD, HKD, RMB, NZD, GBP etc. The user can then enter more lines into the console by typing a currency and amount and pressing enter. Once per minute, the output showing the net amounts of each currency should be displayed. If the net amount is 0, that currency should not be displayed. 
When the user types "quit", the program should exit.

You may need to make some assumptions. For example, if the user enters invalid input, you can choose to display an error message or quit the program. For each assumption you make, write it down in a readme.txt and include it when you submit the project.

Things you may consider using:
• Unit testing
• Threadsafe code
• Documentation
• Programming patterns

Please put your code in a bitbucket/github repository. We should be able to build and run your program easily (you may wish to use Maven, Ant, etc). Include instructions on how to run your program.

## Optional bonus question
Allow each currency to have the exchange rate compared to USD configured. When you display the output, write the USD equivalent amount next to it, for example:

![Optional bonus image](https://i.imgur.com/JU2ilqS.png)
