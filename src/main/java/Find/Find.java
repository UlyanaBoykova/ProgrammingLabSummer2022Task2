package Find;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;
import java.util.Objects;

public class Find {

    @Option(name = "-r")
    private boolean subdirectory;

    @Option(name = "-d")
    private File directory;

    @Argument(required = true)
    private String fileNames;

    private String findSetFiles(File directory, String fileNamesSet) {
        boolean firstFile = true;
        StringBuilder result = null;
        String[] fileSet = fileNamesSet.split(", ");
        for (String fileName: fileSet) {
            if (firstFile){
                firstFile = false;
                result = new StringBuilder(findOneFile(directory, fileName));
            }
            else result.append("\n").append(findOneFile(directory, fileName));
        }
        assert result != null;
        return result.toString();
    }

    private String findOneFile(File directory, String fileName) {
        String result = "Файл " + fileName + " не существует";
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (subdirectory) {
                    if (file.isDirectory() &&
                            (!result.equals(findOneFile(file, fileName))))
                        return findOneFile(file, fileName);
                }
                if (file.getName().equals(fileName))
                    return "Путь к файлу " + fileName + ":" + file.getAbsolutePath();
            }
        }
        return result;
    }

    public String launcher(String[] args) {
        File directoryDefault = new File(new File("").getAbsolutePath());
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (fileNames.isEmpty()) throw new IllegalArgumentException("");
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
        return findSetFiles(Objects.requireNonNullElse(directory, directoryDefault), fileNames);
    }
}