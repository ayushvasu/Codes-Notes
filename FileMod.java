import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMod {

    public static void main(String[] args) {

        String inFolder = "/home/impadmin/eclipse-workspace/TestProgramsCheck/infolder";
        String outFolder = "/home/impadmin/eclipse-workspace/TestProgramsCheck/outfolder";
        String targetFolder = "/home/impadmin/eclipse-workspace/TestProgramsCheck/targetfolder";
        String errorFolder = "/home/impadmin/eclipse-workspace/TestProgramsCheck/errorfolder";

        File inFolderFile = new File(inFolder);
        File outFolderFile = new File(outFolder);
        File targetFolderFile = new File(targetFolder);
        File errorFolderFile = new File(errorFolder);

        if (!inFolderFile.exists() || !outFolderFile.exists() || !targetFolderFile.exists() || !errorFolderFile.exists()
            || !inFolderFile.isDirectory() || !outFolderFile.isDirectory() || !targetFolderFile.isDirectory()
            || !errorFolderFile.isDirectory()) {
            System.out.println("mandatory folders are not present or not directories !!!");
            System.exit(1);
        }

        for (File file : inFolderFile.listFiles()) {
            BufferedWriter bw = null;
            FileWriter fw = null;

            String inputFileName = file.getAbsolutePath();
            String outputFileName = outFolder + File.separator + "output-" + file.getName();
            String errorFileName = errorFolder + File.separator + "error-" + file.getName();
            String targetFileName = targetFolder + File.separator + file.getName();

            try {
                Scanner scanner = new Scanner(new File(inputFileName));
                StringBuilder outputLine = new StringBuilder();

                fw = new FileWriter(outputFileName);
                bw = new BufferedWriter(fw);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("ON")) {
                        if (outputLine.length() > 0) {
                            System.out.println(outputLine.toString());
                            bw.write(outputLine.toString() + "\n");
                            outputLine = new StringBuilder();
                        }
                        outputLine.append(line);
                    } else {
                        outputLine.append("~" + line);
                    }
                }
                if (outputLine.length() > 0) {
                    System.out.println(outputLine.toString());
                    bw.write(outputLine.toString());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                try {
                    FileMod.moveFile(inputFileName, errorFileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    FileMod.moveFile(inputFileName, errorFileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } finally {
                try {
                    bw.close();
                    fw.close();
                    if (new File(inputFileName).exists())
                        FileMod.moveFile(inputFileName, targetFileName);
                    System.out.println(file.getAbsolutePath() + " : File change completed !!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void moveFile(String inFile, String targetFile) throws IOException {

        Path temp = Files.move(Paths.get(inFile), Paths.get(targetFile));

        if (temp != null) {
            System.out.println("File " + inFile + " renamed and moved successfully to " + targetFile);
        } else {
            System.out.println("Failed to move the file " + inFile + " to target dir " + targetFile);
        }

    }
}
