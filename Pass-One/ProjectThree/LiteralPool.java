 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */


   class LiteralPool{
      private Literal[] literalPool = new Literal[0];
   
      public int addLiteral(Literal literal) throws LiteralException{
         if(!literal.isCharacterString()){
            if(((literal.toString().length() - 4) % 2) != 0)
            	throw new LiteralException();
         }
      		
         Literal[] newLiteralList = new Literal[literalPool.length + 1];
      
         for(int i = 0; i < literalPool.length; i++){
            newLiteralList[i] = literalPool[i];
         }
      	
         newLiteralList[literalPool.length] = literal;
         literalPool = newLiteralList;
      	
         return literalPool.length;
      }
   	
      public Literal printLiteralPool(){
			Literal returnLiteral = literalPool[literalPool.length - 1];
			
			Literal[] newLiteralList = new Literal[literalPool.length - 1];
			
			for(int i = 0; i < literalPool.length - 1; i++){
				newLiteralList[i] = literalPool[i];
			}
			
			literalPool = newLiteralList;
			
			return returnLiteral;
      }
		
		public boolean literalsToPrint(){
			if(literalPool.length == 0)
				return false;
				
			else
				return true;
		}
   }
	
	class LiteralException extends MyException{
		public LiteralException(){
			super("UNACCEPTABLE FORMAT - This literal is not a hex byte.");
		}
	}