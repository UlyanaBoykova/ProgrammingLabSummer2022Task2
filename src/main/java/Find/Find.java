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

    @Argument
    private File fileName;


    private String find(File directory, File fileName) {
        String result = "Файл " + fileName.getName() + " не существует";
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (subdirectory) {
                    if (file.isDirectory() &&
                            (!result.equals(find(file, fileName))))
                        return find(file, fileName);
                }
                if (file.getName().equals(fileName.getName()))
                    result = "Путь к файлу " + fileName.getName() + ":" + file.getAbsolutePath();
            }
        }
        return result;
    }


    public String main(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (fileName.getName().isEmpty()) throw new IllegalArgumentException("");
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
        if (directory != null) {
            return find(directory, fileName);
        }
        File directory1 = new File(new File("").getAbsolutePath());
        return find(directory1, fileName);
    }
}