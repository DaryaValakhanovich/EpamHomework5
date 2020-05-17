package task3;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FastFileMover {

    public void moveFile1(String fromFilePath, String toFilePath) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File afile = new File(fromFilePath);
            File bfile = new File(toFilePath);
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            afile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveFile2(String fromFilePath, String toFilePath) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File afile = new File(fromFilePath);
            File bfile = new File(toFilePath);
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);
            byte[] buffer = new byte[100000];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            afile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveFile3(String fromFilePath, String toFilePath) throws IOException {
        File destFile = new File(toFilePath);
        File sourceFile = new File(fromFilePath);
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size - count)) < size) ;
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public void moveFile4(String fromFilePath, String toFilePath) throws IOException {
        try {
            Files.copy(Paths.get(fromFilePath), Paths.get(toFilePath));
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
