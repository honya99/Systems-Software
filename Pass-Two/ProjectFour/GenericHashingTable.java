//Honya Elfayoumy

public abstract class GenericHashingTable{
    private int ARRAYSIZE;		// Constant for array length; prime number
    final static int DOUBLEHASHINGCONSTANT = 83; // Constant used for double hashing; prime number

    private int numberOfItems = 0;

    public int getLastestBase() {
        return LastestBase;
    }

    public void setLastestBase(int lastestBase) {
        LastestBase = lastestBase;
    }

    private int LastestBase = -1;

    public HashableObject[] myArray;

    public GenericHashingTable(){
        this(97); // Constant used for array length; is a prime number
    }

    public GenericHashingTable(int arraySize){
        this.ARRAYSIZE = getPrimeNumber(arraySize);
        myArray = new HashableObject[ARRAYSIZE];
    }

    public static int getPrimeNumber(int minSize){
        for(int i = minSize; true; i++){
            if(isPrime(i))
                return i;
        }
    }

    // Determines if number is prime
    private static boolean isPrime(int n){
        for(int j = 2; (j*j <= n); j++)
            if( n % j == 0)
                return false;
        return true;
    }

    public HashableObject[] getArray(){
        return myArray;
    }

    public int insert(HashableObject myObject){
        int hashValue = getHashValue(myObject.getHashableString());

        while(myArray[hashValue] != null){
            hashValue += getStepValue(myObject.getHashableString());			// add the step for collision
            hashValue %= ARRAYSIZE;							// for wraparound
        }

        myArray[hashValue] = myObject;		// references the entire string into storage

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

    private int getHashValue(String hashableString){
        int hashValue = 0;

        for(int j = 0; j < hashableString.length(); j++){
            int letter = hashableString.toUpperCase().charAt(j);				// get char ASCII code
            hashValue = (hashValue * 26 + letter) % ARRAYSIZE; // modular to ARRAYSIZE
        }

        return hashValue;
    }

    private int getStepValue(String hashableString){
        return DOUBLEHASHINGCONSTANT - (getHashValue(hashableString) % DOUBLEHASHINGCONSTANT); // Double hash
    }

    public int getNumberOfItems(){
        return numberOfItems;
    }

    public int getArraySize(){
        return ARRAYSIZE;
    }
}
