    	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */
   
   
   public abstract class HashableObject{
      private String hashableString = "";
   	
      public String getHashableString(){
         return hashableString;
      }
   	
      protected void setHashableString(String hashableString){
         this.hashableString = hashableString.toUpperCase();
      }
   }
	
   class SICEQU extends HashableObject{
      private String EQULabel;
      private String EQUStringValue;
      private int EQUIntegerValue; 
   	
      public SICEQU(String EQULabel, String EQUStringValue){
         setHashableString(EQULabel);
         this.EQULabel = EQULabel;
         this.EQUStringValue = EQUStringValue;
      }
   	
      public SICEQU(String EQULabel, int EQUValue){
         setHashableString(EQULabel);
         this.EQULabel = EQULabel;
         this.EQUIntegerValue = EQUIntegerValue;
      }
   	
      public String toString(){
         return EQULabel;
      }
   	
      public String getEQULabel(){
         return EQULabel;
      }
   	
      public String getEQUStringValue(){
         return EQUStringValue;
      }
   	
      public int getEQUIntegerValue(){
         return EQUIntegerValue;
      }
   }

   class SICOperation extends HashableObject{
      private String SICInstruction;
      private String correspondingObjectCode;
      private String storageRequired;
      private String miscellaneous;
   	
      private int intStorageRequired;
      //constructors
		protected SICOperation(){
		}
	
      protected SICOperation(String SICInstruction){
         setHashableString(SICInstruction);
         this.SICInstruction = SICInstruction;
      }
   
      private SICOperation(String SICInstruction, String correspondingObjectCode){
         this(SICInstruction);
         this.correspondingObjectCode = correspondingObjectCode;
      }
   
      private SICOperation(String SICInstruction, String correspondingObjectCode, String storageRequired){
         this(SICInstruction, correspondingObjectCode);
         this.storageRequired = storageRequired;
      	
         try{
            intStorageRequired = Integer.parseInt(storageRequired);
         }
            catch(Exception e){}
      }
   
      public SICOperation(String SICInstruction, String correspondingObjectCode, String storageRequired, String miscellaneous){
         this(SICInstruction, correspondingObjectCode, storageRequired);
         this.miscellaneous = miscellaneous;
      }
   	
      public String toString(){
         return SICInstruction;
      }
      //getters
      public String getSICInstruction(){
         return SICInstruction;
      }
   	
      public String getObjectCode(){
         return correspondingObjectCode;
      }
   	
      public int getStorageRequired(){
         return intStorageRequired;
      }
   	
      public String getMiscellaneous(){
         return miscellaneous;
      }
      //setters
      public void setSICInstruction(String SICInstruction){
         this.SICInstruction = SICInstruction;
      }
   	
      public void setObjectCode(String correspondingObjectCode){
         this.correspondingObjectCode = correspondingObjectCode;
      }
   	
      public void setStorageRequired(String storageRequired){
         this.storageRequired = storageRequired;
      }
   	
      public void setMiscellaneous(String miscellaneous){
         this.miscellaneous = miscellaneous;
      }		  
   }

   class SICLabel extends HashableObject{
      private String SICLabel;
      private String miscellaneous;
      private boolean labelOnly;
      private int location;
      //constructors
      public SICLabel(String SICLabel){
         setHashableString(SICLabel);
         this.SICLabel = SICLabel;
         labelOnly = true;
      }
   
      public SICLabel(String SICLabel, String stringLocation){
         this(SICLabel);
         location = Integer.parseInt(stringLocation.trim());
         labelOnly = false;
      }
   	
      public SICLabel(String SICLabel, int location){
         this(SICLabel);
         this.location = location;
         labelOnly = false;
      }
   	
      public String toString(){
         return SICLabel;
      }
      //setters
      public void setLabelOnly(boolean labelOnly){
         this.labelOnly = labelOnly;
      }
   
      public void setSICLabel(String SICLabel){
         this.SICLabel = SICLabel;
      }
   	
      public void setLocation(String stringLocation){
         location = Integer.parseInt(stringLocation.trim());
      }
   	
      public void setMiscellaneous(String miscellaneous){
         this.miscellaneous = miscellaneous;
      }
      //getters
      public boolean isLabelOnly(){
         return labelOnly;
      }
   
   
      public String getSICLabel(){
         return SICLabel;
      }
   	
      public int getLocation(){
         return location;
      }
   	
      public String getMiscellaneous(){
         return miscellaneous;
      }
   }