 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */


   class Literal{
      private String value;
      private boolean characterString;
		private int bytesBig;
   	
      public Literal(String value, boolean characterString){
         this.value = value;
         this.characterString = characterString;
			setSize();
      }
   	
      public String toString(){
         return value;
      }
   	
      public boolean isCharacterString(){
         return characterString;
      }
		
		private void setSize(){
			bytesBig = value.substring(3, value.length() - 1).length();
		
			if(characterString)
				bytesBig *= 2;
		}
		
		public int getSize(){
			return bytesBig;
		}
   }