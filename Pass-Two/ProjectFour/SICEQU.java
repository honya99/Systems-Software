  //Honya Elfayoumy

   public class SICEQU extends HashableObject{
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
