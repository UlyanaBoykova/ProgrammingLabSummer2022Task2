package Find;

import java.io.File;

public class Find {

    public String findFiles(Launcher launcher) {
        boolean firstFile = true;
        StringBuilder result = new StringBuilder();
        for (String fileName: launcher.getFileNames()) {
            if (firstFile){
                firstFile = false;
                result.append(findOneFile(launcher.getDirectory(), fileName, launcher.getSubdirectory()));
            }
            else result.append("\n").append(findOneFile(launcher.getDirectory(), fileName, launcher.getSubdirectory()));
        }
        return result.toString();
    }

    private String findOneFile( File directory,  String fileName, boolean subdirectory) {
        String result = "Файл " + fileName + " не существует";
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (subdirectory) {
                    if (file.isDirectory() &&
                            (!result.equals(findOneFile(file, fileName, true)))) {
                        return findOneFile(file, fileName, true);
                    }
                }
                if (file.getName().equals(fileName)) {
                    return "Путь к файлу " + fileName + ":" + file.getAbsolutePath();
                }
            }
        }
        return result;
    }
}
