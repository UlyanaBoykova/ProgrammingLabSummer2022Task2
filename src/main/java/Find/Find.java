package Find;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;

public class Find {

    @Option(name = "-r")
    private boolean subdirectory;

    @Option(name = "-d")
    private File directory;

    @Argument(required = true)
    private String fileName;

    private String findDirectory(File directory, String fileName) {
        String result = "Файл " + fileName + " не существует";
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName))
                    return "Путь к файлу " + fileName + ":" + file.getAbsolutePath();
            }
        }
        return result;
    }

    private String findSubdirectory(File directory, String fileName) {
        String result = "Файл " + fileName + " не существует";
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (subdirectory) {
                    if (file.isDirectory() &&
                            (!result.equals(findSubdirectory(file, fileName))))
                        return findSubdirectory(file, fileName);
                }
                if (file.getName().equals(fileName))
                    return "Путь к файлу " + fileName + ":" + file.getAbsolutePath();
            }
        }
        return result;
    }

    public String main(String[] args) {
        File directoryDefault = new File(new File("").getAbsolutePath());
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (fileName.isEmpty()) throw new IllegalArgumentException("");
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("");
        }
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.out.println("Command Line: -r -d directory filename.txt");
            System.exit(1);
        }
        if ((directory != null) && subdirectory){
            return findSubdirectory(directory, fileName);
        }
        else if ((directory == null) && (!subdirectory)){
            return findDirectory(directoryDefault, fileName);
        }
        else if (directory != null) {
            return findDirectory(directory, fileName);
        }
        else return findSubdirectory(directoryDefault, fileName);
    }
}