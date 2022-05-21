package Find;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;
import java.util.Objects;

public class Launcher {

    @Option(name = "-r")
    private boolean subdirectory;

    @Option(name = "-d")
    private File directory;

    @Argument
    private String[] fileNames;

    Launcher (boolean subdirectory, File directory, String[] fileNames) {
        this.subdirectory = subdirectory;
        this.directory = directory;
        this.fileNames = fileNames;
    }

    Launcher(){}

    public boolean getSubdirectory() {
        return subdirectory;
    }

    public File getDirectory() {
        return directory;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public String launcher(String[] args) {
        Find find = new Find();
        File directoryDefault = new File(new File("").getAbsolutePath());
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.out.println("Command Line: -r -d directory filename.txt");
            System.exit(1);
        }
        return find.findFiles(new Launcher(subdirectory, Objects.requireNonNullElse(directory, directoryDefault), fileNames));
    }
}