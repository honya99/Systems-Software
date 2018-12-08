  //Honya Elfayoumy

   public abstract class HashableObject{
      private String hashableString = "";
   	
      public String getHashableString(){
         return hashableString;
      }
   	
      protected void setHashableString(String hashableString){
         this.hashableString = hashableString.toUpperCase();
      }
		
		public String toString(){
			return hashableString;
		}
   }