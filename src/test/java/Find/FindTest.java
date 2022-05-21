package Find;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindTest {
    Launcher fileFind = new Launcher();
    String pathDefault = new File("").getAbsolutePath();
    String path1 = new File("").getAbsolutePath() + "/Directory";
    String path2 = new File("").getAbsolutePath() + "/Directory/Directory2";
    File file1 = new File(pathDefault + "/Directory/Directory1", "File1");
    File file2 = new File(pathDefault + "/Directory/Directory2", "File2");
    File file3 = new File(pathDefault + "/Directory", "File3");
    File file5 = new File(pathDefault, "File5");
    File file6 = new File(pathDefault + "/Directory/Directory2/Directory3", "File6");
    File file7 = new File(pathDefault, "File7");
    File file8 = new File(pathDefault + "/Directory", "File8");
    File file9 = new File(pathDefault + "/Directory", "File9");

    @Test
    public void testFile1WithKey_r() {
        assertEquals("Путь к файлу File1:" + file1.getAbsolutePath(),
                fileFind.launcher(new String[]{"-r", "File1"}));
    }

    @Test
    public void testFile1WithKey_d() {
        assertEquals("Файл File1 не существует",
                fileFind.launcher(new String[]{"-d", pathDefault, "File1"}));
    }

    @Test
    public void testFiles2_6WithKey_rAndKey_d() {
        assertEquals("Путь к файлу File2:" + file2.getAbsolutePath() + "\n" +
                             "Путь к файлу File6:" + file6.getAbsolutePath(),
                fileFind.launcher(new String[]{"-r", "-d", path1, "File2", "File6"}));
    }
    @Test
    public void testFile3WithKey_r() {
        assertEquals("Путь к файлу File3:" + file3.getAbsolutePath(),
                fileFind.launcher(new String[]{"-r", "File3"}));
    }

    @Test
    public void testFiles3_8_9WithKey_r() {
        assertEquals("Путь к файлу File3:" + file3.getAbsolutePath() + "\n" +
                             "Путь к файлу File8:" + file8.getAbsolutePath() + "\n" +
                             "Путь к файлу File9:" + file9.getAbsolutePath(),
                fileFind.launcher(new String[]{"-r", "File3", "File8", "File9"}));
    }

    @Test
    public void testNonExistentFile() {
        assertEquals("Файл File4 не существует",
                fileFind.launcher(new String[]{"-r", "-d", pathDefault, "File4"}));
    }

    @Test
    public void testFile5WithoutKeys() {
        assertEquals("Путь к файлу File5:" + file5.getAbsolutePath(),
                fileFind.launcher(new String[]{"File5"}));
    }

    @Test
    public void testFile5_7WithoutKeys() {
        assertEquals("Путь к файлу File5:" + file5.getAbsolutePath() + "\n" +
                             "Путь к файлу File7:" + file7.getAbsolutePath(),
                fileFind.launcher(new String[]{"File5", "File7"}));
    }

    @Test
    public void testFile6WithKey_rAndKey_d() {
        assertEquals("Путь к файлу File6:" + file6.getAbsolutePath(),
                fileFind.launcher(new String[]{"-r", "-d", path2, "File6"}));
    }

}