import java.io.*;
import java.util.*;

public class Lab4 {

    public static void main(String[] args) throws Exception {

        System.out.println("Enter the path : ");
        List<metaData> metaList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String path = null;
        File f = null;

        try {
            path = sc.nextLine();
            f = new File(path);
            if (!f.exists()) {
                sc.close();
                throw new FileNotFoundException();

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        sc.close();

        if (f.isDirectory()) {
            dir(f, metaList);
        } else {
            metaList.add(new metaData(f));
        }

        File output = new File(".\\Output.txt");
        
        serializData(metaList, output);
        System.out.println("We are done, You can see the result in -> Output.txt");
    }
//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------
// Recursion method to store all the directories and files 
    public static void dir(File folder, List<metaData> list) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    dir(file, list);
                } else {
                    list.add(new metaData(file));
                }
            }
        }
    }
//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------

    public static void serializData(List<metaData> list, File output) {
        try (FileOutputStream fos = new FileOutputStream(output);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            for (metaData data : list) {
                oos.writeObject(data.toString());
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}