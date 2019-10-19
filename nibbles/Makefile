# Makefile
# Written by, Sohail Qayum Malik

all				: Nibbles\NibblesConstants.class benchmark\benchmark.class Nibbles\NibblesKeyBoardControls.class Nibbles\NibblesRandomEventGenerator.class Nibbles\NibblesBox.class Nibbles\NibblesGraphicsFunctions.class Nibbles\NibblesFunctions.class Nibbles.class

Nibbles\NibblesConstants.class 	: NibblesConstants.java
											javac $< -d ./
	
benchmark\benchmark.class	: benchmark.java
											javac $< -d ./ 

Nibbles\NibblesKeyBoardControls.class	: NibblesKeyBoardControls.java
											javac $< -d ./
										
Nibbles\NibblesRandomEventGenerator.class	: NibblesRandomEventGenerator.java
											javac $< -d ./
											
Nibbles\NibblesBox.class					: NibblesBox.java
											javac $< -d ./
											
Nibbles\NibblesGraphicsFunctions.class		: NibblesGraphicsFunctions.java
											javac $< -d ./

Nibbles\NibblesFunctions.class				: NibblesFunctions.java
											javac $< -d ./
								 								
Nibbles.class								: Nibbles.java
											javac $<
						
clean										:
											del Nibbles.class
											del /Q /F	.\Nibbles\*
											del /Q /F .\benchmark\*
											rmdir .\Nibbles
											rmdir .\benchmark
								

							
