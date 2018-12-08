Pass One

The purpose of this project is to implement pass 1 of the assembler. Pass 1 constructs a symbol table,
the addresses associated with each instruction and the addresses of each label. The program will implement functions 
that are used in SIC/XE. There are errors thrown if an instruction is not found or if a label is duplicated.

  ProjectThree.java file: This file contains the program and the implementation of the program. This file will
  read the input file from the command line.
  GenericHashingTable.java file: This is the base for all the hashed tables.
  HashableObject.java file: This file contains the types of objects accepted by the tables.
  

  Input.txt file: The input file is determined by the user in the command line. The Project3.java file will read the file.
  
  The output file will be a *.lst file.

  Makefile file: The makefile compiles the ProjectThree file and also runs the other file as an input.