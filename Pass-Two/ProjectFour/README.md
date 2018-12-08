# Assembler

Pass Two

The purpose of this project is creating address lines as well as object code to write them into a .lst and .obj file. This is Pass 1 and Pass 2 of a SIC/XE assembler.

  AssemblerMain.java file: This file contains the program and the implementation of the program. This file will
  read the input file from the command line.
  GenericHashingTable.java file: This is the base for all the hashed tables.
  HashableObject.java file: This file contains the types of objects accepted by the tables.
  

  Input.txt file: The input file is determined by the user in the command line. The AssemblerMain.java file will read the file. The input file should adhere the following format:

     Col 1-8    Label (optional)
     Col 9      Blank
     Col 10     + or * (optional)
     Col 11-17  Mnemonic
     Col 18     Blank
     Col 19     #, @, = (optional)
     Col 20-29  Operand
     Col 30-31  Blank
     Col 32-80  Comments (optional)
  
  Output files: *.obj and *.lst. 
  The program will print the text file with computer addresses and object code for each instruction.

  Makefile file: The makefile compiles the AssemblerMain file and also runs the other file as an input.
  
  

 