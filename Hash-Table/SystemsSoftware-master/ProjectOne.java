import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectOne {
    ArrayList<Data> stored;
    PrintWriter writer = null;

    ProjectOne(int sz) {
        stored =  new ArrayList<Data>(sz);
        try {
            writer = new PrintWriter("Honya.txt");
        }
        catch (FileNotFoundException e) {}
    }

    public int search(int hash) {
        int ret = -1;
        for(Data dt : stored) {
            if(dt.getHash() == hash)
                ret = stored.indexOf(dt);
        }
        return ret;
    }

    public void insertInput(String k, String v) {
        //System.out.println("key "+k+" val "+v);
        Data entry = new Data(k, v);
        int pos = search(entry.getHash());
        if (v.equals("")) { //no number
            if(pos == -1) {
                writer.println("ERROR " + k + " not found");
                writer.println("");
            } else { //already stored
                writer.println(k + " found at location " + pos + " with value " + stored.get(pos).getValue());
                writer.println("");
            }
        } else { //if there is number
            if(pos == -1) { //it doesnt exist yet
                stored.add(entry);
                writer.println("stored " + k + " " + v + " at location " + stored.indexOf(entry));
                writer.println("");
            } else { //already stored
                writer.println("ERROR " + k + " already exists at location " + pos);
                writer.println("");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ProjectOne demo = new ProjectOne(1049);
        //accept the input from the console
        //Scanner input = new Scanner(System.in);
        //System.out.println("Enter input file name: ");
        //String ip = input.nextLine();
        //System.out.println(" input file name: "+ip);
        BufferedReader reader;
        if (args.length == 0) {
            System.out.println("ERROR No input file specified.");
            System.exit(1);
        }
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\s+", " "); //make sure there is only a single space between the possible string and number
                if (!line.equals("") && !line.equals(" ")) { //ignore any blank lines if they exist
                    if (line.charAt(0) == ' ') {
                        line = line.substring(1); //make sure to get rid of any preceding spaces
                    }
                    //System.out.println(line);
                    String[] lineSplit = line.split(" "); //string in index 0, optional number in index 1
                    /*for(int i=0; i< lineSplit.length; i++)
                        System.out.println(lineSplit[i]);*/
                    //check if there is a number
                    String key = lineSplit[0];
                    String val = "";
                    if (lineSplit.length > 1) val = lineSplit[1];
                    demo.insertInput(key, val);
                }
            }
            reader.close();
            demo.writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR Input file not found.");
            System.exit(2);
        }
    }
}