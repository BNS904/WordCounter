import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName;
        List<String> givenWords;
        Scanner scanner = new Scanner(System.in);

       System.out.println("Hello, welcome to my WordCounter program! To start the program please enter a file name.");

       while (true) {
           try {
               fileName = scanner.nextLine();
               givenWords = readFileToArray(fileName);
               break;
           } catch (Exception e) {
               System.out.println("File name is invalid, please try again.");
           }
       }

       System.out.println("Now, please enter your output file name.");

        while (true) {
            try {
                fileName = scanner.nextLine();
                writeCountArrayToFile(givenWords, fileName);
                break;
            } catch (Exception e) {
                System.out.println("File name is invalid, please try again.");
            }
        }

       System.out.println("Success, file outputted.");

    }

    public static ArrayList readFileToArray(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            arrayList.add(scanner.nextLine());
        }
        scanner.close();
        ArrayList<String> readArray = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            readArray.add(i, arrayList.get(i));
        }
        return readArray;
    }
    public static void writeCountArrayToFile(List<String> countList, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        Map<String, Integer> stringCounts = new HashMap<>();

        for (String str : countList) {
            String lowercaseStr = str.toLowerCase(); // Convert to lowercase
            if (stringCounts.containsKey(lowercaseStr)) {
                // If the lowercase string is already in the map, increment the count
                int count = stringCounts.get(lowercaseStr);
                stringCounts.put(lowercaseStr, count + 1);
            } else {
                // If the lowercase string is not in the map, add it with a count of 1
                stringCounts.put(lowercaseStr, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : stringCounts.entrySet()) {
            fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        fileWriter.close();
    }
}

