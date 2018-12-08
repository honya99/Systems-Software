     //Honya Elfayoumy
   
   public class NextLineObject {
      private String label = "";
      private String mnemonic = "";
      private String operand = "";
      private String comment = "";

      private String objectCode = "FFF";
      private String location = "000000";

      private String operandAddress = "FFF";

      private boolean isComment = false;
      public boolean duplicateSymbol = false;
      public boolean invalidOperationCode = false;
      public boolean invalidConstantType = false;
      public boolean undefinedSymbol = false;


      public NextLineObject(String nextLine) {
         this.isComment = false;
         this.duplicateSymbol = false;
         this.invalidOperationCode = false;
         this.invalidConstantType = false;
         this.undefinedSymbol = false;
         if (nextLine.charAt(0) == '.') {
            isComment = true;
            comment = nextLine;
            return;
         }

         char[] charArray = nextLine.toCharArray();
         String[] stringSplit = nextLine.split(" ");
         int i = 0;

         if ((charArray[0] > 64 && charArray[0] < 91) || (charArray[0] > 96 && charArray[0] < 123))
            label = stringSplit[0];
         else
            label = "";

         if (charArray.length > 10)
            if ((charArray[11] > 64 && charArray[11] < 91) || (charArray[11] > 96 && charArray[11] < 123))
               for (i = 1; i < stringSplit.length; i++)
                  if (stringSplit[i].compareTo(" ") != 0 && stringSplit[i].compareTo("") != 0 &&
                          stringSplit[i].compareTo("\t") != 0) {
                     mnemonic = stringSplit[i];
                     break;
                  }

         int k = 0;

         for (int j = 18; j < charArray.length; j++) {
            if (j > 18 && j < 31)
               if ((charArray[j] > 64 && charArray[j] < 91) || (charArray[j] > 96 && charArray[j] < 123) || (charArray[j] > 47 && charArray[j] < 58)) {
                  for (k = i + 1; k < stringSplit.length; k++) {
                     if (stringSplit[k].compareTo(" ") != 0 && stringSplit[k].compareTo("") != 0 &&
                             stringSplit[k].compareTo("\t") != 0) {
                        operand = stringSplit[k];
                        break;
                     }
                  }
                  break;
               }
         }

         for (int m = k + 1; m < stringSplit.length; m++)
            if (stringSplit[k].compareTo(" ") != 0 && stringSplit[k].compareTo("") != 0 && stringSplit[k].compareTo("\t") != 0) {
               comment += stringSplit[m] + " ";
               comment.trim();
            }
      }

      public String toString() {
         return location + "   " + objectCode + operandAddress + "       " + label + "  " + "  " + mnemonic + "  " + operand + "  " + comment;
      }

      public String getOperandAddress() {
         return operandAddress;
      }

      public String getTextLine() {
         return objectCode + operandAddress;
      }

      public String getLabel() {
         return label;
      }

      public String getMnemonic() {
         return mnemonic;
      }

      public String getOperand() {
         return operand;
      }

      public String getComment() {
         return comment;
      }

      public boolean isComment() {
         return isComment;
      }

      public String getObjectCode() {
         return objectCode;
      }

      public String getLocation() {
         return location;
      }

      public void setObjectCode(String objectCode) {
         this.objectCode = objectCode.toUpperCase() + "0";
      }

      public void setOperandAddress(String operandAddress) {
         this.operandAddress = operandAddress.toUpperCase();
      }

      public void setLocation(String location) {

         while (true) {
            if (location.length() != 6)
               if (location.length() < 6)
                  location = "0" + location;
               else
                  break;
            else
               break;
         }

         this.location = location.toUpperCase();
      }

      public void setStartObjectCode() {
         objectCode = "   ";
         operandAddress = "   ";
      }

      public void setObjectCodeForWord(String objectCode) {
         operandAddress = "";

         while (true) {
            if (objectCode.length() != 6)
               if (objectCode.length() < 6)
                  objectCode = "0" + objectCode;
               else
                  break;
            else
               break;
         }

         this.objectCode = objectCode;
      }

      public void setObjectCodeForByte(String objectCode) {
         operandAddress = "";

         this.objectCode = objectCode.toUpperCase();
      }

      public void setCommentCode() {
         setStartObjectCode();
      }

      public void setEndCode() {
         setStartObjectCode();
      }

      public boolean isObjectCodeEmpty() {
         if (objectCode == null || objectCode.compareTo("") == 0)
            return true;
         else {
            char[] charArray = objectCode.toCharArray();

            for (char thisChar : charArray)
               if (thisChar != ' ')
                  return false;
         }

         return false;
      }

      public void addN() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[1] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(1), 16) + 2).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addI() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[1] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(1), 16) + 1).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addNI() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[1] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(1), 16) + 3).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addX() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[2] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(2), 16) + 8).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addB() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[2] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(2), 16) + 4).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addP() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[2] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(2), 16) + 2).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void addE() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[2] = Integer.toHexString(Integer.parseInt("" + objectCode.charAt(2), 16) + 1).charAt(0);
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public void Clear() {
         if (!isObjectCodeEmpty()) {
            char[] charArray = objectCode.toCharArray();
            charArray[2] = '\0';
            objectCode = new String(charArray).toUpperCase();
         }
      }

      public boolean noLabelTime() {
         String[] types = {"RESW", "BYTE", "WORD", "RESB"};
         if (getLabel().compareToIgnoreCase("") == 0) {
            String local = getMnemonic();
            for (int i = 0; i < types.length; i++) {
               if (local.equals(types[i])) {
                  return true;
               }
            }
         }
         return false;
      }

      public String simOperand() {
         String moron = "";
         String high = getOperand().toUpperCase();
         for (int i = 0; i < getOperand().length(); i++) {
            if (high.charAt(i) >= 'A' && high.charAt(i) <= 'Z') {
               moron += (Character.toString(high.charAt(i)));
            }
         }
         return moron;
      }

      public boolean isNumOperand() {
         boolean local = true;
         int i = 0;
         String hurt = getOperand().toUpperCase().trim();
         if (hurt.startsWith("#")) {
            i = 1;
         }
         for (; i < hurt.length(); i++) {
            if (hurt.charAt(i) >= '0' && hurt.charAt(i) <= '9') {
               return local;
            } else {
               return false;
            }
         }
         return local;
      }
   }