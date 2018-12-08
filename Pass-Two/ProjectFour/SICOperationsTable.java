     //Honya Elfayoumy

   class SICOperationsTable extends GenericHashingTable{
      private java.util.Scanner input;
      private java.io.File file;
   	
      private GenericHashingTable SICEQU;
   	
      public SICOperationsTable(){
         this("SicOps.txt");
      }
   
      public SICOperationsTable(String fileLocation){
         super(197);
         SICEQU = new SICEQUTable();
      	
         try{
            file = new java.io.File(fileLocation);			// Create File object from supplied file directory
            input = new java.util.Scanner(file);		// Create Scanner object from File object
         	
            while(input.hasNextLine()){
               readLine(input.nextLine());
            }      
         }
         
            catch(java.io.FileNotFoundException e){
               e.printStackTrace();
               System.out.println("\nAn error has occurred. This program will now exit.");
            }
         	
         finally{
            input.close();
         }
      }
   	
      public void readLine(String string){
			String[] splitString = string.split(" ", 0);
			String[] data = new String[4];
			
			int i = 0;
			
			for(String myString: splitString)
				if(myString.compareTo("") != 0)
					data[i++] = myString;
			
         if(data.length == 2){
            SICEQU.insert(new SICEQU(data[0], data[1]));
         }
         else
            insert(new SICInstruction(data[0], data[1], data[2], data[3]));
      }
   	
      public SICEQUTable getEQUTable(){
         return (SICEQUTable)SICEQU;
      }
   }