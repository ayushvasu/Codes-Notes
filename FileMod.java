import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileMod {

    public static void main(String[] args) {
      
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        String outputFileName = "/home/impadmin/OutputCheck";
        String inputFileName = "/home/impadmin/Check";
        
        try {
            Scanner scanner = new Scanner(new File(inputFileName));
            StringBuilder outputLine = new StringBuilder();
            
            fw = new FileWriter(outputFileName);
            bw = new BufferedWriter(fw);
                   
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.startsWith("ON")) {
                    if(outputLine.length() > 0)
                    {
                        System.out.println(outputLine.toString());
                        bw.write(outputLine.toString()+"\n");
                        outputLine = new StringBuilder();
                    }
                    outputLine.append(line);
                } else {
                    outputLine.append("~"+line);
                }
            }
            if(outputLine.length() > 0)
            {
                System.out.println(outputLine.toString());
                bw.write(outputLine.toString());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
