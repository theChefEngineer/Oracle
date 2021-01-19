
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

public class ApacheZipper {
    public static void main(String[] args) throws Exception {

        System.out.println("Extracting Zip:");
        extractFile(new File("C:\\Users\\Abdessamad\\Desktop\\IntellijProject\\JavaTraining\\RDI.zip"));
    }

    static void extractFile(File zipFile) throws Exception
    {
        byte[] buffer = new byte[4096];
        ZipFile zf = new ZipFile(zipFile);

        Enumeration<ZipArchiveEntry> entries = zf.getEntries();

        while(entries.hasMoreElements())
        {
            ZipArchiveEntry ze = entries.nextElement();
            String zefilename = ze.getName();

            System.out.println("Extracting file: " + zefilename);
            File extfile = new File(zefilename);

            InputStream zis = zf.getInputStream(ze);
            FileOutputStream fos = new FileOutputStream(extfile);
            try {
                int numBytes;
                while ((numBytes = zis.read(buffer, 0, buffer.length)) != -1)
                    fos.write(buffer, 0, numBytes);
            }
            finally {
                fos.close();
                zis.close();
            }

        }
        zf.close();
    }
}

