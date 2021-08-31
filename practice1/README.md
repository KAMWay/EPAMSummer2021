Practical task №1 

_______________________

Note: You are not allowed to use any reference types apart from those from java.lang package.

Task 1

_______________________

Class name: com.epam.rd.java.basic.practice1.Part1

-----------------------

Write a class that implements functionality of displaying a string “Hello, World” in console.

Write a command file (bat file for Windows or shell script for Linux) that compiles and runs the application from console.

Save the command file in the src folder of the project.

_______________________

Task 2

-----------------------

Class name: com.epam.rd.java.basic.practice1.Part2



Write a class that implements functionality of addition of numbers passed to the application as the command line parameters.

_______________________

Task 3

_______________________

Class name: com.epam.rd.java.basic.practice1.Part3

_______________________

Write a class that implements functionality of displaying of the command line parameters in console (display using a space between them), the result should not end with a space.

_______________________

Task 4

_______________________

Class name: com.epam.rd.java.basic.practice1.Part4

_______________________

Write a class that implements functionality of defining the greatest common divisor of two whole positive numbers passed to the application as the command line parameters.

_______________________

Task 5

_______________________

Class name: com.epam.rd.java.basic.practice1.Part5

_______________________

Write a class that implements functionality of defining the sum of the digits of a whole positive number passed to the application as the command line parameter.

_______________________

Task 6

_______________________

Class name: com.epam.rd.java.basic.practice1.Part6

_______________________

Write a class that creates an array from n elements and fills it with an ascending sequence of prime numbers (2, 3, 5, 7 …). The n number should be passed as the command line parameter.

_______________________

Task 7

_______________________

Class name: com.epam.rd.java.basic.practice1.Part7

_______________________

Columns of spreadsheets (like Excel) have letter numbering in the form of latin capital letters (sequentially, from left to right):

A, B, ..., Y, Z, AA, AB, ..., AY, AZ, BA, BB, …, etc.

At the same time, each column has its sequence number: A - 1; B - 2; ... ; Y - 25; Z - 26; AA - 27; AB - 28; …, etc.

Write a class that contains the following three methods:

- a method of defining the column sequence number by its letter number 

[ public static int str2int(String number): A => 1; B => 2; ...; Z => 26; AA => 27; AB => 28; ... ];

- a method of defining the column letter number by its sequence number

[ public static String int2str(int number): 1 => A; 2 => B; ...; 26 => Z; 27 ==> AA; 28 ==> AB; ... ];

- using the column letter number, write a method of defining the number of the column that is placed on the right side of the given one.

[ public static String rightColumn(String number): A => B; B => C; ...; Y => Z; Z => AA; AA => AB; …].

The methods functionality is to be checked using the following data: 

—————————————————————————————————————

A ==> 1 ==> A

B ==> 2 ==> B

Z ==> 26 ==> Z

AA ==> 27 ==> AA

AZ ==> 52 ==> AZ

BA ==> 53 ==> BA

ZZ ==> 702 ==> ZZ

AAA ==> 703 ==> AAA

—————————————————————————————————————

_______________________

Notes.

1.  The result should be presented as a project named Practice1.

2.  The root package for all the classes and other packages (if needed): com.epam.rd.java.basic.practice1

3.  Additionally, add Demo class to your root package that demonstrates the actions of all the 7 subtasks.