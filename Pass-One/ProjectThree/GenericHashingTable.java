 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */


   public abstract class GenericHashingTable{
      private int ARRAYSIZE;		// Constant for array length; prime number
      final static int DOUBLEHASHINGCONSTANT = 83; // Constant used for double hashing; prime number
   	
      private int numberOfItems = 0;
   	
      //private int j = 0;
      
      public HashableObject[] myArray;
      
      //public HashableObject[] reference;
   	
      public GenericHashingTable(){
         this(97);
      }
   	
      public GenericHashingTable(int arraySize){
         this.ARRAYSIZE = arraySize;
         myArray = new HashableObject[arraySize];
      }
   	
      public HashableObject[] getArray(){
         return myArray;
      }
     
      public int insert(HashableObject myObject){
        // reference[j++] = myObject;
         int hashValue = getHashValue(myObject.getHashableString());
      
         while(myArray[hashValue] != null){
            hashValue += getStepValue(myObject.getHashableString());			//for collision
            hashValue %= ARRAYSIZE;							//to wraparound
         }
      	
         myArray[hashValue] = myObject;		//references entire string into storage
      	
         ++numberOfItems;
      	
         return hashValue;    
      }
		
		public HashableObject getObjectAt(int index){
			return myArray[index];
		}
   	
      public int find(HashableObject myObject){
         int hashValue = getHashValue(myObject.getHashableString());
      
         while(myArray[hashValue] != null){
            if(myArray[hashValue].getHashableString().compareTo(myObject.getHashableString()) == 0)
               return hashValue;
         	
            hashValue += getStepValue(myObject.getHashableString());
            hashValue %= ARRAYSIZE;
         }
      
         return -1;
      }
   	
      public int find(String string){
         int hashValue = getHashValue(string.toUpperCase());
      	
         while(myArray[hashValue] != null){
            if(myArray[hashValue].getHashableString().compareTo(string.toUpperCase()) == 0)
               return hashValue;
         	
            hashValue += getStepValue(string.toUpperCase());
            hashValue %= ARRAYSIZE;
         }
      
         return -1;
      }
   	
      public void displayTable(){
         System.out.println("Table: ");
         for(int j = 0; j < ARRAYSIZE; j++){
            if(myArray[j] != null)
               System.out.println(myArray[j] + " ");
            else
               System.out.println("** ");
         }
         System.out.println();
      }
   	
      public int getHashValue(String hashableString){
         int hashValue = 0;
      
         for(int j = 0; j < hashableString.length(); j++){
            int letter = hashableString.toUpperCase().charAt(j);				//ASCII code (char)
            hashValue = (hashValue * 26 + letter) % ARRAYSIZE; //modular to ARRAYSIZE
         }
      
         return hashValue;
      }
   	
      public int getStepValue(String hashableString){
         return DOUBLEHASHINGCONSTANT - (getHashValue(hashableString) % DOUBLEHASHINGCONSTANT); //Double hash
      }
   	
      public int getNumberOfItems(){
         return numberOfItems;
      }
		
		public int getArraySize(){
			return ARRAYSIZE;
		}
   }