package Find;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindTest {
    Find fileFind = new Find();

    File file1 = new File(new File("").getAbsolutePath() + "/Directory/Directory1", "File1");
    File file2 = new File(new File("").getAbsolutePath() + "/Directory/Directory2", "File2");
    File file3 = new File(new File("").getAbsolutePath() + "/Directory", "File3");
    File file5 = new File(new File("").getAbsolutePath(), "File5");
    File file6 = new File(new File("").getAbsolutePath() + "/Directory/Directory2/Directory3", "File6");

    @Test
    public void testFile1() {
        assertEquals("Файл File1 не существует",
                fileFind.main(new String[]{"-d", new File("").getAbsolutePath(), "File1"}));

        assertEquals("Путь к файлу File1" + ":" + file1.getAbsolutePath(),
                fileFind.main(new String[]{"-r", "File1"}));
    }
    @Test
    public void testFile2() {
        assertEquals("Путь к файлу File2" + ":" + file2.getAbsolutePath(),
                fileFind.main(new String[]{"-r", "-d", new File("").getAbsolutePath(), "File2"}));
    }
    @Test
    public void testFile3() {
        assertEquals("Путь к файлу File3" + ":" + file3.getAbsolutePath(),
                fileFind.main(new String[]{"-r", "File3"}));
    }
    @Test
    public void testFile4() {
        assertEquals("Файл File4 не существует",
                fileFind.main(new String[]{"-r", "-d", new File("").getAbsolutePath(), "File4"}));
    }

    @Test
    public void testFile5() {
        assertEquals("Путь к файлу File5" + ":" + file5.getAbsolutePath(),
                fileFind.main(new String[]{"File5"}));
    }

    @Test
    public void testFile6() {
        assertEquals("Путь к файлу File6" + ":" + file6.getAbsolutePath(),
                fileFind.main(new String[]{"-r", "-d", new File("").getAbsolutePath(), "File6"}));
    }

    @Test
    public void testThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                fileFind.main(new String[]{""}));
    }

}