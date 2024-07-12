import junit.framework.TestCase;
import org.te.file_system.FileSystem;
import org.te.file_system.FileSystemImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileSystemTest extends TestCase
{

    @Test
    public void testCreateDirectory()
    {
        FileSystem fileSystem = new FileSystemImpl();
        fileSystem.mkdir("/david/rodrigues");
        List<String> files =  fileSystem.ls("/david/rodrigues");
        assertEquals(true,files.size() == 0);

        files =  fileSystem.ls("/david");
        assertEquals(true,files.size() == 1);
        assertEquals(true, "rodrigues".equals(files.get(0)));

        files =  fileSystem.ls("/");
        assertEquals(true,files.size() == 1);
        assertEquals(true, "david".equals(files.get(0)));

    }

    @Test
    public void testCreateDirectoryAndThenFile()
    {
        FileSystem fileSystem = new FileSystemImpl();
        fileSystem.mkdir("/david/rodrigues");
        fileSystem.createFile("/david/rodrigues/nunes", "file data");
        List<String> files =fileSystem.ls("/david/rodrigues");
        assertTrue(files.size() == 1);
        assertTrue(files.get(0).equals("nunes"));


        fileSystem.mkdir("/david/rodrigues/dirInRodrigues");
        files =fileSystem.ls("/david/rodrigues");

        assertEquals(2, files.size());
        assertEquals(true, files.get(0).equals("nunes"));
        assertEquals(true, files.get(1).equals("dirInRodrigues"));
    }
}
