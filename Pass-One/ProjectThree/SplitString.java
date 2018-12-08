  	 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */
   
   
   class SplitString{
      public static String[] splitString(String string){
          String boy  = string.trim();
         String[] splitString = boy.split("\\s+", 0);

         String newString = new String();
			String literalString = "";
			String commentString = "";
			int firstTick = string.indexOf("'");
			int literalIndex = 2;
			
			if(firstTick != -1){
				firstTick++;
				
				if(string.indexOf("=") != -1)
					literalIndex = 3;
					
				literalString = string.substring(firstTick - literalIndex, string.indexOf("'", firstTick) + 1);
				commentString = string.substring(string.indexOf("'", firstTick) + 1, string.length());
				commentString = commentString.trim();
			}
      	
         for(String myString: splitString)
            if(myString.compareTo("") != 0)
               newString = newString + myString.trim() + " ";
      	
         splitString = newString.split(" ", 0);
         newString = "";
      	
         for(String myString: splitString)
            if(myString.compareTo("") != 0)
               newString = newString + myString.trim() + " ";
         splitString = newString.split(" ");
         newString = "";
      	
         for(int i = 3; i < splitString.length; i++)
            if(splitString[i].compareTo("") != 0)
               newString = newString + splitString[i] + " ";
         String[] returnString = new String[4];
         if(labelGiven(string) && splitString.length >= 3){
            returnString[0] = splitString[0];
           	returnString[1] = splitString[1];
           	returnString[2] = splitString[2];
           	returnString[3] = newString;
			}
         else if(splitString.length == 2){
            returnString[0] = "";
            returnString[1] = splitString[0];
            returnString[2] = splitString[1];
            returnString[3] = newString;
         }
         else{
            returnString[0] = "";
            returnString[1] = splitString[0];
            returnString[2] = "";
            returnString[3] = newString;
         }
			
			if(literalString.compareTo("") != 0){
				returnString[0] = splitString[0];
				returnString[1] = splitString[1];
				returnString[2] = literalString;
				returnString[3] = commentString;
			}
       
         return returnString;
      }
		
		public static boolean labelGiven(String string){
         if((new String("" + string.charAt(0))).compareTo(" ") == 0 || (new String("" + string.charAt(0))).compareTo("\t") == 0)
            return false;
      
         return true;
      }
   }
