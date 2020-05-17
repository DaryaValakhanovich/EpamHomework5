package task3;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        String filePath1 = "C:\\WebServers\\denwer\\www\\denwer\\i\\ivlvllv.txt";
        String filePath2 = "C:\\WebServers\\denwer\\www\\denwer\\Tools\\ivlvllv.txt";
        FastFileMover fastFileMover = new FastFileMover();
        fastFileMover.moveFile1(filePath1, filePath2);
        fastFileMover.moveFile2(filePath2, filePath1);
        fastFileMover.moveFile3(filePath1, filePath2);
        fastFileMover.moveFile4(filePath2, filePath1);
    }
}
