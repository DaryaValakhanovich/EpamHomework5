package task2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DiskAnalyzer {
    public DiskAnalyzer() {
    }

    Pattern pattern = Pattern.compile("((C:\\\\(([A-z]|[0-9]|\\s)+)))");

    public void execute(String filePath, int functionNumber) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("result.txt"));
        validate(filePath);
        switch (functionNumber) {
            case 1:
                objectOutputStream.write(getFileWithMaximumS(filePath).getBytes());
                System.out.println(getFileWithMaximumS(filePath));
                break;
            case 2:
                objectOutputStream.write(getFiveFilesWithLargestSize(filePath).toString().getBytes());
                System.out.println(getFiveFilesWithLargestSize(filePath).toString());
                break;
            case 3:
                objectOutputStream.writeLong(getAverageFileSize(filePath));
                System.out.println(getAverageFileSize(filePath));
                break;
            case 4:
                objectOutputStream.write(getFileStartsWithInformation(filePath).getBytes());
                System.out.println(getFileStartsWithInformation(filePath));
                break;
            default:
                objectOutputStream.writeObject("Wrong function number");
                System.out.println("Wrong function number");
                break;
        }
        objectOutputStream.close();
    }

    private String getFileWithMaximumS(String filePath) throws IOException {
        Path PATH = Paths.get(filePath);
        int numberOfLettersS = -1;
        String path = null;
        List<Path> list = Files.list(PATH).collect(Collectors.toList());
        for (Path p : list) {
            int numberOfLetterSInTheFileName = 0;
            char[] fileName = p.getFileName().toString().toCharArray();
            for (char letter : fileName) {
                if (letter == 's') {
                    numberOfLetterSInTheFileName++;
                }
            }
            if (numberOfLetterSInTheFileName > numberOfLettersS) {
                numberOfLettersS = numberOfLetterSInTheFileName;
                path = p.toAbsolutePath().toString();
            }
        }
        return path;
    }

    private List<String> getFiveFilesWithLargestSize(String filePath) throws IOException {
        Map<Long, Path> sortedMap = new TreeMap<>();
        Path PATH = Paths.get(filePath);
        Files.list(PATH).collect(Collectors.toList()).forEach(p -> sortedMap.put(p.toFile().length(), p));
        List<String> list = new ArrayList<>();
        if (sortedMap.size() >= 5) {
            Object[] array = sortedMap.values().toArray();
            for (int i = array.length - 5; i < array.length; i++) {
                list.add(array[i].toString());
            }
        }
        return list;
    }

    private long getAverageFileSize(String filePath) throws IOException {
        Path PATH = Paths.get(filePath);
        List<Path> list = Files.list(PATH).collect(Collectors.toList());
        int numberOfFiles = 0;
        long averageFileSize = 0;
        for (Path p : list) {
            averageFileSize += p.toFile().length();
            numberOfFiles++;
        }
        if (numberOfFiles != 0) {
            averageFileSize /= numberOfFiles;
        }
        return averageFileSize;
    }

    private String getFileStartsWithInformation(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (char letter = 'a'; letter <= 'f'; letter++) {
            String startsWith = Character.toString(letter);
            int[] array = getNumberOfFilesAndDirectories(filePath, startsWith);
            stringBuilder.append("the letter ").append(letter).append(" - begins ").append(array[0])
                    .append(" files and ").append(array[1]).append(" folders\n");
        }
        return stringBuilder.toString();
    }

    private int[] getNumberOfFilesAndDirectories(String filePath, String startsWith) throws IOException {
        Path PATH = Paths.get(filePath);
        List<Path> list = Files.list(PATH).collect(Collectors.toList());
        int numberOfFiles = 0;
        int numberOfDirectories = 0;
        for (Path p : list) {
            if (p.toFile().isDirectory()) {
                int[] array = getNumberOfFilesAndDirectories(p.toString(), startsWith);
                numberOfFiles += array[0];
                numberOfDirectories += array[1];
            }
            if (p.toFile().getName().startsWith(startsWith)) {
                if (p.toFile().isDirectory()) {
                    numberOfDirectories++;
                } else {
                    numberOfFiles++;
                }
            }
        }
        return new int[]{numberOfFiles, numberOfDirectories};
    }

    private void validate(String filePath) throws Exception {
        Matcher m = pattern.matcher(filePath);
        if (!m.matches()) {
            throw new Exception("Wrong file path");
        }
    }
}
