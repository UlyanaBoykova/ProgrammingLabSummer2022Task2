package Find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


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
        fileFind.launcher(new String[]{"-r", "File1"});
        assertEquals("Путь к файлу File1:" + file1.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFile1WithKey_d() {
        fileFind.launcher(new String[]{"-d", pathDefault, "File1"});
        assertEquals("Файл File1 не существует", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFiles2_6WithKey_rAndKey_d() {
        fileFind.launcher(new String[]{"-r", "-d", path1, "File2", "File6"});
        assertEquals("Путь к файлу File2:" + file2.getAbsolutePath() + "\n" +
                             "Путь к файлу File6:" + file6.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }
    @Test
    public void testFile3WithKey_r() {
        fileFind.launcher(new String[]{"-r", "File3"});
        assertEquals("Путь к файлу File3:" + file3.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFiles3_8_9WithKey_r() {
        fileFind.launcher(new String[]{"-r", "File3", "File8", "File9"});
        assertEquals("Путь к файлу File3:" + file3.getAbsolutePath() + "\n" +
                             "Путь к файлу File8:" + file8.getAbsolutePath() + "\n" +
                             "Путь к файлу File9:" + file9.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testNonExistentFile() {
        fileFind.launcher(new String[]{"-r", "-d", pathDefault, "File4"});
        assertEquals("Файл File4 не существует", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFile5WithoutKeys() {
        fileFind.launcher(new String[]{"File5"});
        assertEquals("Путь к файлу File5:" + file5.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFile5_7WithoutKeys() {
        fileFind.launcher(new String[]{"File5", "File7"});
        assertEquals("Путь к файлу File5:" + file5.getAbsolutePath() + "\n" +
                             "Путь к файлу File7:" + file7.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFile6WithKey_rAndKey_d() {
        fileFind.launcher(new String[]{"-r", "-d", path2, "File6"});
        assertEquals("Путь к файлу File6:" + file6.getAbsolutePath(), outputStreamCaptor.toString().trim());
    }

}