package task2;

public class Runner {
    public static void main(String[] args) {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer();
        String filePath = "C:\\WebServers\\denwer\\www\\denwer";
        try {
            diskAnalyzer.execute(filePath, 1);
            diskAnalyzer.execute(filePath, 2);
            diskAnalyzer.execute(filePath, 3);
            diskAnalyzer.execute(filePath, 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
