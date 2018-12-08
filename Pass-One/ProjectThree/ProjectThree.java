 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */



import java.io.*;
import java.util.*;

public class ProjectThree {
    public static void main(String[] args) {
        new Pass1().main(args);
    }
}

class Pass1 {
    private File file;
    private Scanner input;

    private PrintWriter output;

    private SICOperationsTable SICOperations = new SICOperationsTable();
    private SICLabelTable SICLabel = new SICLabelTable();
    private SICEQUTable SICEQU;

    //private Global wordAddress;

    private int lineNumber = 0;
    private int location = 0;
    private int startingAddress = 0;
    private int programLength;

    private boolean literalPoolEmptied = false;

    private LiteralPool literalPool = new LiteralPool();

    public Pass1() {
        SICEQU = SICOperations.getEQUTable();
    }

    public static String STAD;

    public void main(String[] args) {
        String string;

        splash(args);

        printLineNumber();
        string = input.nextLine();

        legal = new int[100];
        labelArray = new String[100];
        for (int b = 0; b < legal.length; b++) {
            legal[b] = 5;
        }

        try {
            readLine(string);
            printLocation(string);
        } catch (Exception e) {
        }

        while (input.hasNextLine()) {
            string = input.nextLine();

            printLineNumber();

            if (!isComment(string)) {
                try {
                    printLocation(string);
                    readLine(string);
                } catch (MyException e) {
                    String[] z = SplitString.splitString(string);
                    if (z[1].equals("WORD")){
                        location+=3;
                    }else if (z[1].equals("RESW")){
                        location+=3*Integer.parseInt(z[2]);
                    }else if(z[1].equals("BYTE")) {
                        location += 1;
                    }else if(z[1].equals("RESB")){
                        location+=Integer.parseInt(z[2]);
                    }else if(SICOperations.find(z[1]) != -1) {
                        location += ((SICOperation) SICOperations.getObjectAt(SICOperations.find(z[1]))).getStorageRequired();
                    }
                    printError(e);
                }
            } else
                printComment(string);
        }

        SICLabel.displaySymbolTableForProject3(output, labelArray, legal, localAdd, stringArray);
        programLength = location - startingAddress;
    }

    public void printError(MyException e) {
        output.println(e);
    }

    public static boolean isComment(String string) {
        if ((new String("" + string.charAt(0))).compareTo(".") == 0)
            return true;

        return false;
    }

    public static boolean labelGiven(String string) {
        if ((new String("" + string.charAt(0))).compareTo(" ") == 0 || (new String("" + string.charAt(0))).compareTo("\t") == 0)
            return false;

        return true;
    }

    public void splash(String[] args) {
        String newFileName = openFilesAndScanners(args);

        String assemblerReport = new String("ASSEMBLER REPORT");
        String dashes = new String("----------------");

        String columnHeaders = new String("     Loc   Object Code       Source Code");
        String columnDashes = new String("     ----   -----------       -----------");

        try {
            output = new PrintWriter(new FileWriter(newFileName), true);

            output.println(assemblerReport);
            output.println(dashes);
            output.println(columnHeaders);
            output.println(columnDashes);
        } catch (Exception e) {
        }
    }

    public String openFilesAndScanners(String[] args) {
        String newFileName = "";

        try {
            file = new File(args[0]);            //Creates a File object from file directory
            input = new Scanner(file);        //Creates Scanner object from file object

            if (args[0].indexOf(".") != -1)
                new File(newFileName = args[0].substring(0, args[0].indexOf(".")) + ".lst").createNewFile();

            else
                new File(newFileName = args[0] + ".lst").createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nAn error has occurred. This program will now exit.");
        }

        return newFileName;
    }

    private int k = 0;

    boolean[] array = new boolean[100];
    String[] stringArray = new String[100];
    int[] legal = new int[100];
    String[] labelArray = new String[100];
    String[] localAdd = new String[100];

    public void readLine(String string) throws LabelAlreadyDefined, OPCodeNotFound, LiteralException {
        String[] splitString;
        splitString = SplitString.splitString(string);
        stringArray[k] = splitString[1];
        labelArray[k] = splitString[0];
        if (labelGiven(string)) {
            array[k] = true;
            SICLabel.insert(new SICLabel(splitString[0], location));
        } else {
            array[k] = false;
        }

        if (SICOperations.find(splitString[1]) != -1) {
            location += ((SICOperation) SICOperations.getObjectAt(SICOperations.find(splitString[1]))).getStorageRequired();
        }else if (splitString[1].compareToIgnoreCase("START") == 0 && lineNumber == 1) {
            try {
                STAD = splitString[2];
                location = Integer.parseInt(splitString[2].trim(), 16);
                if (splitString[0].equals("")) {
                }
            } catch (Exception e) {
            }
        } else if (splitString[1].compareToIgnoreCase("WORD") == 0) {
            if (splitString[0].compareToIgnoreCase("") == 0) {
                int z = 0;
                for (z = k; z > 0; z--) {
                    if (!(stringArray[z].equals("WORD"))) {
                        break;
                    }
                }
                if (z == 1) {
                    output.println("Erorr Undefined Label " + Integer.toHexString(location));
                } else if (array[z + 1] == false) {  //or false? //nvm
                    output.println("Erorr Undefined Label " + Integer.toHexString(location));
                }
            }
            location += 3;
        } else if (splitString[1].compareToIgnoreCase("RESW") == 0) {
            try {

            } catch (Exception e) {

            }finally {
                int numberOfReserveWords = 3 * Integer.parseInt(splitString[2]);
                location += numberOfReserveWords;
            }
        } else if (splitString[1].compareToIgnoreCase("BYTE") == 0) {
            if (splitString[0].compareToIgnoreCase("") == 0) {
                int z = 0;
                for (z = k; z > 0; z--) {
                    if (!(stringArray[z].equals("BYTE"))) {
                        break;
                    }
                }
                if (z == 1) {
                    output.println("Erorr Undefined Label " + Integer.toHexString(location));
                } else if (array[z + 1] == false) {  //or false? //nvm
                    output.println("Erorr Undefined Label " + Integer.toHexString(location));
                }
            }
            if (splitString[2].indexOf("'") != -1) {
                if (new String("" + splitString[2].charAt(splitString[2].indexOf("'") - 1)).compareToIgnoreCase("C") == 0) {
                    location += (2 * (splitString[2].length() - 3));
                } else if (new String("" + splitString[2].charAt(splitString[2].indexOf("'") - 1)).compareToIgnoreCase("X") == 0) {
                    location += (splitString[2].length() - 3);
                }
            }else {
                location++;
            }
        } else if (splitString[1].compareToIgnoreCase("RESB") == 0) {
            try {
                location += Integer.parseInt(splitString[2]);
            } catch (Exception e) {
            }
        } else if (splitString[1].compareToIgnoreCase("BASE") == 0) {
        } else if (splitString[1].compareToIgnoreCase("EQU") == 0) {
        } else if (splitString[1].compareToIgnoreCase("JLTF") == 0) {
        } else if (splitString[1].compareToIgnoreCase("LDTF") == 0) {
        } else if (splitString[1].compareToIgnoreCase("LTORG") == 0) {
            while (literalPool.literalsToPrint()) {
                Literal thisLiteral = literalPool.printLiteralPool();
                printLineNumber();
                location += thisLiteral.getSize();
                printLocation("\t\t\t" + thisLiteral.toString());
            }
            literalPoolEmptied = true;
        } else if (splitString[2].indexOf("=") != -1) {
            if (splitString[2].toUpperCase().indexOf("X") != -1) {
                literalPool.addLiteral(new Literal(splitString[2], false));
                literalPoolEmptied = false;
            } else if (splitString[2].toUpperCase().indexOf("C") != -1) {
                literalPool.addLiteral(new Literal(splitString[2], true));
                literalPoolEmptied = false;
            }
        } else if (splitString[1].compareToIgnoreCase("END") == 0)
            return;
        else {
            throw new OPCodeNotFound();
        }
        int temp = SICOperations.find(splitString[1]);
        if (!(dupulicate(labelArray, k, splitString[0]))) {
            if (splitString[0].equals("") && temp == -1) {
                legal[k] = 1;
            } else if (splitString[0].equals("") && temp != -1) {
                legal[k] = 1;
            } else if ((!(splitString[0].equals(""))) && splitString[1].equals("START") && temp == -1) {
                legal[k] = 0;
            } else
                legal[k] = 0;
        } else {
            if ((!(splitString[0].equals(""))) && splitString[1].equals("START")) {
                legal[k] = 0;
            } else
                legal[k] = 2;
        }
        localAdd[k] = Integer.toHexString(location);
        k++;
    }

    public boolean dupulicate(String[] legal, int k, String label) {
        for (int j = k; j > 0; j--) {
            if (legal[j].equals(label)) {
                return false;
            }
        }
        return true;
    }


    public void printLineNumber() {
        output.printf("%1$03d- ", ++lineNumber);
    }

    public void printLocation(String string) {
        output.printf("%1$05X", location);
        output.println("                   " + string);
    }

    public void printComment(String string) {
        output.println("                        " + string);
    }
}
