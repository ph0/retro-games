# Makefile
# Project: TOH, Being done in Java 6 on Windows XP machine
# Written by, Sohail Qayum Malik[sqm@hackers.pk]

.PHONY					: all clean  

all					: Hanoi.class .\Hanoi\mouseClickControls.class .\Hanoi\mouseMovementControls.class .\Hanoi\gameBoard.class


Hanoi.class				: Hanoi.java .\Hanoi\mouseClickControls.class .\Hanoi\mouseMovementControls.class .\Hanoi\gameBoard.class
					javac $< -d .\


.\Hanoi\mouseClickControls.class	: mouseClickControls.java
					javac $< -d .\


.\Hanoi\mouseMovementControls.class	: mouseMovementControls.java
					javac $< -d .\


.\Hanoi\gameBoard.class			: gameBoard.java
					javac $< -d .\


clean					: 			
					del .\Hanoi\*.class
					rmdir .\Hanoi
					del *.class

