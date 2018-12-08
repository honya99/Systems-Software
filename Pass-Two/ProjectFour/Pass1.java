    //Honya Elfayoumy

    import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

   public class Pass1{
      public SICOperationsTable operationsTable;
      public SICEQUTable EQUTable;
      public SICLabelObjectTable symbolTable = new SICLabelObjectTable(100);
   
      public File file;
      public Scanner input;
   	
      public NextLineObject thisLine;
   	
      public int startingAddress = 0;
      public int locationCounter;
      public int programLength;
      public int lineNumber = 1;
      public String baseLabel = "";
      public int goToIT=-1;
   	
      public LinkedBlockingQueue<Integer> locationList = new LinkedBlockingQueue();
      public LinkedBlockingQueue<String> opList = new LinkedBlockingQueue();
   	

      public Pass1(String arg, SICOperationsTable operationsTable){
         this.operationsTable = operationsTable;
         EQUTable = operationsTable.getEQUTable();
      }
   
      public String main(String arg){
         try{
            file = new File(arg);
            input = new Scanner(file);
         }
            catch(FileNotFoundException e){
               System.out.println("File could not be found.");
               System.exit(-3);
            }
      		
         String newFileName = this.splash(arg);
      		
         thisLine = new NextLineObject(input.nextLine());
      		
         if(thisLine.getMnemonic().compareToIgnoreCase("START") == 0){
            startingAddress = Integer.parseInt(thisLine.getOperand().trim(), 16);
            locationCounter = startingAddress;
            printToFile(thisLine, lineNumber++);
            try{
               locationList.put(startingAddress);
            }
               catch(Exception e){}
            thisLine = new NextLineObject(input.nextLine());
         }
         else{
            locationCounter = 0;
         }
      	
         while(thisLine.getMnemonic().compareToIgnoreCase("END") != 0){
            if(!thisLine.isComment()){
               if(thisLine.getLabel() != null){
                  if(thisLine.getLabel().compareTo("") != 0){
                     if(symbolTable.find(thisLine.getLabel()) != -1)
                        thisLine.duplicateSymbol = true;
                     else{
                        symbolTable.insert(new labelObject(thisLine.getLabel().trim(), locationCounter));
							}
                  }
               }
            	
               int foundReturn = operationsTable.find(thisLine.getMnemonic());
               if(foundReturn != -1){
                  locationCounter += ((SICInstruction)operationsTable.getObjectAt(foundReturn)).getByteSize();
                  try{
                     opList.put(((SICInstruction)operationsTable.getObjectAt(foundReturn)).getOPCode());
                  }
                     catch(InterruptedException ie){}
                  thisLine.undefinedSymbol=false;
               }
               else if(thisLine.getMnemonic().compareToIgnoreCase("WORD") == 0)
                  locationCounter += 3;
               	
               else if(thisLine.getMnemonic().compareToIgnoreCase("RESW") == 0)
                  locationCounter += (3 * Integer.parseInt(thisLine.getOperand().trim()));
               	
               else if(thisLine.getMnemonic().compareToIgnoreCase("RESB") == 0)
                  locationCounter += Integer.parseInt(thisLine.getOperand().trim());
               	
               else if(thisLine.getMnemonic().compareToIgnoreCase("BYTE") == 0){
                  String constant = thisLine.getOperand().trim();
                  char constantType = constant.charAt(0);
                  int constantIndex = constant.indexOf("'");
                  String constantSubstring = constant.substring(constantIndex + 1, constant.length() - 1);
               	
                  if(constantType == 'C' || constantType == 'c')
                     locationCounter += constantSubstring.length();
                  else if(constantType == 'x' || constantType == 'X')
                     locationCounter += constantSubstring.length();
                  else
                     thisLine.invalidConstantType = true;
               		
               }
               else if(thisLine.getMnemonic().compareToIgnoreCase("LTORG") == 0){
               }
               else if (thisLine.getMnemonic().compareToIgnoreCase("BASE") == 0){
                     thisLine.undefinedSymbol=false;
                    baseLabel =  thisLine.getOperand().trim();
                     goToIT=1;
               } else
                  thisLine.invalidOperationCode = true;
            		
               try{
                  locationList.put(locationCounter);
               }
                  catch(Exception e){}
            }
            if (goToIT == 1){
               int a = symbolTable.find(baseLabel);
               if (a != -1){
                  symbolTable.setLastestBase(a);
                  goToIT = 0;
               }
            }
            printToFile(thisLine, lineNumber++);
            thisLine = new NextLineObject(input.nextLine());
         }
      	
         try{
            locationList.put(locationCounter);
         }
            catch(Exception e){}
      	
         printToFile(thisLine, lineNumber++);
         programLength = locationCounter - startingAddress;
      	
         return newFileName;
      }
   	
      public java.io.PrintWriter listOutput;
   	
      public void printToFile(NextLineObject thisLine, int lineNumber){
         if(thisLine.duplicateSymbol)
            listOutput.println(lineNumber + "  " + thisLine + "\nERROR: Duplicate Symbol");
         else if(thisLine.invalidOperationCode)
            listOutput.println(lineNumber + "  " + thisLine + "\nERROR: Invalid Operation Code");
         else if(thisLine.invalidConstantType)
            listOutput.println(lineNumber + "  " + thisLine + "\nERROR: Invalid Constant Type");
         else if((thisLine.undefinedSymbol  && operationsTable.find(thisLine.getMnemonic()) == -1) || thisLine.noLabelTime())
            listOutput.println(lineNumber + "  " + thisLine + "\nERROR: Operand Undefined " + thisLine.getMnemonic());
         else
            listOutput.println(lineNumber + "  " + thisLine);
      }
   	
      public String splash(String arg){
         String newFileName = createFile(arg);
      	
         Calendar calendar = new GregorianCalendar();
      
         String stars = new String("***************************************");
         String title = new String("* Honya Elfayoumy: SIX/XE Assembler *");
      	
         String assemblerReport = new String("ASSEMBLER REPORT");
         String dashes = new String("----------------");
      	
         String columnHeaders = new String("     Loc   Object Code       Source Code");
         String columnDashes = new String("     ---   -----------       -----------");
      												      
         try{
            listOutput = new PrintWriter(new FileWriter(newFileName), true);
         	
            listOutput.println(stars);
            listOutput.println(title);
            listOutput.println(stars);
            listOutput.println(assemblerReport);
            listOutput.println(dashes);
            listOutput.println(columnHeaders);
            listOutput.println(columnDashes);
         }
            catch(Exception e){}
         finally{
            return newFileName;
         }
      }
   	
      public String createFile(String arg){
         String newFileName = "";
      
         try{
            if(arg.indexOf(".") != -1)
               new File(newFileName = arg.substring(0, arg.indexOf(".")) + ".lst").createNewFile();
            	
            else 
               new File(newFileName = arg + ".lst").createNewFile();     
         }
            catch(Exception e){
               e.printStackTrace();
               System.out.println("\nAn error has occurred. This program will now exit.");
            }
      		
         return newFileName;
      }
   }