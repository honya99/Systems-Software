    //Honya Elfayoumy
  
   import java.io.File;
   import java.util.Scanner;
   import java.io.FileNotFoundException;

   public class ReadLabelTable{
      SICInstructionHashTable SICInstructions = new SICInstructionHashTable();
   
      public ReadLabelTable(){
         this("SicOps.txt");
      }
      private ReadLabelTable(String arg){
         try{
            File file = new File(arg);
            Scanner input = new Scanner(file);
         
            while(input.hasNextLine()){
               readSICInstruction(input.nextLine());
				}
         }
            catch(FileNotFoundException e){
               System.out.println("The file SicOps.dat could not be found!");
               System.exit(-2);
            }
      }
   
      private void readSICInstruction(String instructionLine){
         String[] splitString = instructionLine.split(" ", 0);
         String[] data = new String[4];
         int i = 0;
      	
         for(String myString: splitString)
            if(myString.compareTo("") != 0)
               data[i++] = myString;
      			
         if(data.length == 2){
            //SICEQU.insert(new SICEQU(data[0], data[1]));
         }
         else{
            SICInstructions.insert(new SICInstruction(data[0], data[1], data[2], data[3]));
			}    
		}
   }