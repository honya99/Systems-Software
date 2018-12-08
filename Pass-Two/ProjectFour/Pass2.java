  //Honya Elfayoumy

   import java.io.*;
   import java.util.*;  
	
   public class Pass2 extends Pass1{
      File file;
      Scanner input;
      public PrintWriter objectCodeOutput;
   	
      ArrayList<String> objectFile = new ArrayList();
      int locationOfLastExecution = 1;
      int lineOfObjectCode = 3;
      int lineNumber = 1;
   	
      String startAddress = "";
      String nextLocation = "000000";
   
      public Pass2(String arg, SICOperationsTable operationsTable){
         super(arg, operationsTable);
      	
         super.main(arg);
         this.main(arg);
      }
   	
      public String main(String arg){
         pass2Splash(arg);
         super.splash(arg);
      	
         try{
            file = new File(arg);
            input = new Scanner(file);
         }
            catch(FileNotFoundException e){
               System.out.println("File could not be found.");
               System.exit(-4);
            }
      	
         thisLine = new NextLineObject(input.nextLine());
      		
         if(thisLine.getMnemonic().compareToIgnoreCase("START") == 0){
            thisLine.setLocation("" + Integer.parseInt(thisLine.getOperand().trim()));
            startAddress = thisLine.getOperand().trim();
            thisLine.setStartObjectCode();
            printToFile(thisLine, lineNumber++);
         	
            while(true){
               if(startAddress.length() != 6)
                  if(startAddress.length() < 6)
                     startAddress = "0" + startAddress;
                  else
                     break;
               else
                  break;
            }
            thisLine = new NextLineObject(input.nextLine());
         }
         else{
            startAddress = "000000";
         }
      		
         objectFile.add(startAddress);
         objectFile.add("000000");
         
         while(thisLine.getMnemonic().compareToIgnoreCase("END") != 0){
            if(!thisLine.isComment()){
               thisLine.setLocation(Integer.toHexString(locationList.poll()));
               //System.out.println(thisLine.getMnemonic());
               if(operationsTable.find(thisLine.getMnemonic()) != -1){
                 // System.out.println("HERE " + thisLine.getMnemonic() + " " + thisLine.getOperand());
                  thisLine.setObjectCode(opList.poll());
                  if (thisLine.getMnemonic().equals("RSUB")) {
                            thisLine.setOperandAddress("000");
                  } else

                  if (thisLine.isNumOperand()) {
                     String check="";
                   //  System.out.println("HERE3 " + thisLine.getMnemonic());
                     if (thisLine.getOperand().contains("#")){
                        check = (Integer.toHexString(Integer.parseInt(thisLine.getOperand().substring(1,thisLine.getOperand().length()),16)));
                        //System.out.println("HERE4 " +  check.length());
                     }else{
                        check= (Integer.toHexString(Integer.parseInt(thisLine.getOperand().substring(0,thisLine.getOperand().length()),16)));
                        //System.out.println("HERE4 " +  check.length());
                     }
                     int i = check.length();

                     String togo = "";
                     while( i < 3){
                        togo += "0";
                        i++;
                     }
                     thisLine.setOperandAddress( togo + check);


               } else if(thisLine.getMnemonic().endsWith("R")){

                     String[] ouch = thisLine.getOperand().trim().split(",");
                     int a = operationsTable.find(ouch[0]);
                     int b = operationsTable.find(ouch[1]);
                     String fp = ((SICInstruction)operationsTable.getObjectAt(a)).getOPCode();
                     String sp = ((SICInstruction)operationsTable.getObjectAt(b)).getOPCode();
                     thisLine.setOperandAddress(fp + sp);
                     thisLine.Clear();
                     //System.out.println("HERE2 " + thisLine.getOperandAddress());

                  } else if (hasSymbol(thisLine.getOperand())){

                     int searchSYMTAB = symbolTable.find(thisLine.simOperand().trim());
                     if(searchSYMTAB != -1){
                        int PCRelativeAddressing = ((labelObject)symbolTable.getObjectAt(searchSYMTAB)).getLocation() - locationList.peek();
                     	if (!thisLine.getMnemonic().contains("+")) {
                           if (PCRelativeAddressing < 2047 && PCRelativeAddressing > -2048) {
                              String hexString = Integer.toHexString(PCRelativeAddressing);
                              thisLine.addP();
                              if (PCRelativeAddressing >= 0) {
                                 while (hexString.length() < 3)
                                    hexString = "0" + hexString;
                                 thisLine.setOperandAddress(hexString);
                              } else
                                 thisLine.setOperandAddress(hexString.substring(hexString.length() - 3, hexString.length()));
                           } else {
                              int baseInt = ((labelObject)symbolTable.getObjectAt(symbolTable.getLastestBase())).getLocation();
                              int mainInt = ((labelObject)symbolTable.getObjectAt(searchSYMTAB)).getLocation();
                              String goodNow = Integer.toHexString(mainInt - baseInt);
                              while (goodNow.length() < 3)
                                 goodNow = "0" + goodNow;
                              thisLine.setOperandAddress(goodNow);
                              thisLine.addB();
                           }
                        } else{
                           int a = ((labelObject)symbolTable.getObjectAt(searchSYMTAB)).getLocation();
                           String localhex = Integer.toHexString(a);
                           if (localhex.length() <  5) {
                              String temp = "0";
                              for (int i = localhex.length(); i < 5; i++) {
                                 localhex = temp + localhex;
                              }
                           }
                           thisLine.setOperandAddress(localhex);
                        }
                     }
                     else{
                        if(thisLine.getOperand().trim().indexOf(',') != -1){
                           String[] splitOperand = thisLine.getOperand().trim().split(",");

                           if(splitOperand[1].compareToIgnoreCase("X") == 0){ // Indexing
                              if(symbolTable.find(splitOperand[0]) != -1){
                                 int PCRelativeAddressing = ((labelObject)symbolTable.getObjectAt(symbolTable.find(splitOperand[0]))).getLocation() - locationList.peek();
                              
                                 if(PCRelativeAddressing < 2047 && PCRelativeAddressing > -2048){
                                    String hexString = Integer.toHexString(PCRelativeAddressing);
												thisLine.addP();
                                 
                                    if(PCRelativeAddressing >= 0){
                                    
                                       while(hexString.length() < 3)
                                          hexString = "0" + hexString;
                                    
                                       thisLine.setOperandAddress(hexString);
                                    }
                                    else
                                       thisLine.setOperandAddress(hexString.substring(hexString.length() - 3, hexString.length()));
                                 }
                                 else{
                                    int baseInt = ((labelObject)symbolTable.getObjectAt(symbolTable.getLastestBase())).getLocation();
                                    int mainInt = ((labelObject)symbolTable.getObjectAt(searchSYMTAB)).getLocation();
                                    String goodNow = Integer.toHexString(mainInt - baseInt);
                                    while (goodNow.length() < 3)
                                       goodNow = "0" + goodNow;
                                    thisLine.setOperandAddress(goodNow);
                                    thisLine.addB();

                                 }
                              }
										
										thisLine.addX();
                           }
                           else{}
                        }
                        else{
                           thisLine.setOperandAddress("000");
                           thisLine.undefinedSymbol = true;
                        }
                     }
                  }
                  else{
                  	/////////////////////
                     thisLine.setOperandAddress("" + 5);
                  }
                  if(thisLine.getMnemonic().compareToIgnoreCase("BASE") != 0 || thisLine.getMnemonic().compareToIgnoreCase("RSUB") !=0 ) {
                  if (!thisLine.getMnemonic().endsWith("R")) {
                        if (thisLine.getOperand().contains("#")) {
                           thisLine.addI();
                        } else if ((thisLine.getOperand().contains("@"))) {
                           thisLine.addN();
                        } else {
                           thisLine.addNI();
                        }
                     }
                     if (((thisLine.getMnemonic().contains("+")))) {
                        thisLine.addE();
                     }
                  }
                  lineOfObjectCode++;
                  objectFile.add(thisLine.getTextLine());
               }
               else if(thisLine.getMnemonic().compareToIgnoreCase("BYTE") == 0 || thisLine.getMnemonic().compareToIgnoreCase("WORD") == 0){
                  if(thisLine.getMnemonic().compareToIgnoreCase("BYTE") == 0){
                     String constantObjectCode = "";
                  
                     String constant = thisLine.getOperand().trim();
                     char constantType = constant.charAt(0);
                     int constantIndex = constant.indexOf("'");
                     String constantSubstring = constant.substring(constantIndex + 1, constant.length() - 1);
                  
                     if(constantType == 'C' || constantType == 'c'){
                        char[] substringArray = constantSubstring.toCharArray();
                     	
                        for(char thisChar: substringArray)
                           constantObjectCode += (Integer.toHexString(((int)thisChar)));
                     }
                     else if(constantType == 'x' || constantType == 'X')
                        constantObjectCode = constantSubstring;
                  		
                     thisLine.setObjectCodeForByte(constantObjectCode);
                  }
                  else{
                     int objectCode = (int)Long.parseLong(thisLine.getOperand().trim(),16);
                     String onGo = Integer.toHexString(objectCode);
                     if(onGo.length() > 6){
                        onGo = onGo.substring(onGo.length()-6,onGo.length());
                     }
                  thisLine.setObjectCodeForWord(onGo);
                  }
               	
                  lineOfObjectCode++;
                  objectFile.add(thisLine.getTextLine());
               }
               else if(thisLine.getMnemonic().compareToIgnoreCase("LTORG") == 0){
                  thisLine.setCommentCode();
               }
               else if(thisLine.getMnemonic().compareToIgnoreCase("RESW") == 0){
                  String hexLocation = Integer.toHexString(locationList.peek());
               
                  while(true){
                     if(hexLocation.length() != 6)
                        if(hexLocation.length() < 6)
                           hexLocation = "0" + hexLocation;
                        else
                           break;
                     else
                        break;
                  }
               
                  objectFile.add("!");
                  objectFile.add(hexLocation);
                  objectFile.add("000000");
                  locationOfLastExecution = lineOfObjectCode + 1;
                  lineOfObjectCode += 3;
               }
            }else if(thisLine.getMnemonic().compareToIgnoreCase("BASE") == 0){
               thisLine.setCommentCode();
            } else{
               thisLine.setCommentCode();
            }
         	
            printToFile(thisLine, lineNumber++);
            thisLine = new NextLineObject(input.nextLine());
            thisLine.undefinedSymbol = false;
         }
      	
         thisLine.setEndCode();
         thisLine.setLocation(Integer.toHexString(locationList.poll()));
         printToFile(thisLine, lineNumber);
      	
         printObjectCode();
      	
         return arg;
      }
   	
      public boolean hasSymbol(String operand){
         char[] operandArray = operand.trim().toCharArray();
      
         for(char thisChar: operandArray)
            if(thisChar > 57 && thisChar < 48)
               return false;
      
         return true;
      }
   	
      public void printObjectCode(){
         objectFile.set(locationOfLastExecution, startAddress);
         String[] objectFileArray = objectFile.toArray(new String[1]);
      	
         for(String thisStringInObjectFile: objectFileArray)
            objectCodeOutput.println(thisStringInObjectFile);
      		
         objectCodeOutput.println("!");
      }
   	
      public String pass2Splash(String arg){
         String newFileName = createObjectFile(arg);
      
         try{
            objectCodeOutput = new PrintWriter(new FileWriter(newFileName), true);
         }
            catch(Exception e){}
         finally{
            return newFileName.substring(0, newFileName.indexOf("."));
         }
      }
   	
      public String createObjectFile(String arg){
         String newFileName = "";
      
         try{
            if(arg.indexOf(".") != -1)
               new File(newFileName = arg.substring(0, arg.indexOf(".")) + ".obj").createNewFile();
            	
            else 
               new File(newFileName = arg + ".obj").createNewFile();     
         }
            catch(Exception e){
               e.printStackTrace();
               System.out.println("\nAn error has occurred. This program will now exit.");
            }
         finally{
            return newFileName;
         }
      }
   }