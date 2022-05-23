package Find;

import java.io.File;

public class Find {


    public void findFiles(Launcher launcher) {
        boolean firstLine = true;
        for (String fileName: launcher.getFileNames()) {
            if (firstLine) {
                firstLine = false;
            } else System.out.print("\n");
            System.out.print(findOneFile(launcher.getDirectory(), fileName, launcher.getSubdirectory()));

        }
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
