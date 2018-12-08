 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */
   
   
   
   import java.io.*;  
	
	class SICLabelTable extends GenericHashingTable{
   
      public void insert(SICLabel myObject) throws LabelAlreadyDefined{
         int hashValue = getHashValue(myObject.getHashableString());
      
         if(myObject.isLabelOnly() != true){
            if((find(myObject)) != -1){
					throw new LabelAlreadyDefined("ERROR " + myObject +
                  " already exists at location " + hashValue);

				}
            
            else{
               int storedLocation = super.insert(myObject);
            }
         }
         
         else{
            if(find(myObject) != -1)
               System.out.println(myObject + " found at location " +
                  hashValue + " with value " + ((SICLabel)myArray[hashValue]).getLocation());
            
            else
               System.out.println("ERROR " + myObject + " not found");
         }
      
      }

      
		public void displaySymbolTableForProject3(PrintWriter out, String[] start, int[] help, String[] localAdd,String[] bigArray){
          out.println();
			out.println();
			out.println("Table  Location   Label  Address   Use   Csect");
			out.println("-----  --------   -----  -------   ---   -----");
         for(int j = 0; j < getArraySize(); j++){
            if(myArray[j] != null){
                int x = 0;
                    for (x = 0; x < getArraySize(); x++){
                        if (myArray[j].getHashableString().equals(start[x]))
                            break;
                    }
            if(help[x] == 0) {
                if ((bigArray[x].equals("START"))) {
                    out.print(j + "     " + "\t\t" + myArray[j] + "\t");
                    out.printf("%s",localAdd[x] );
                    out.println();
                }else{
                    out.print(j + "     " + "\t\t" + myArray[j] + "\t");
                    out.printf("%1$05X", ((SICLabel) myArray[j]).getLocation());
                    out.println();
                }
            }
				}
         }
		}
      
   }
