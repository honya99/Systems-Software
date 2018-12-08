    //Honya Elfayoumy

   public class AssemblerMain{
      public static void main(String[] args){
         if(args.length == 0){
            System.out.println("No file name was passed.");
            System.exit(-1);
         }
         else{ 
            Pass2 twoPass = new Pass2(args[0], new SICOperationsTable());
         }
      }
   }