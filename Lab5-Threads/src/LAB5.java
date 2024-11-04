import java.io.*;
import java.util.Scanner;

public class LAB5 {
    public static void main(String[] args) throws IOException {
       
        System.out.println("\nEnter the path :");
        try (Scanner reader = new Scanner(System.in)) {
            String path = reader.nextLine();
            File f = new File(path);

            if (!f.exists()) {
                throw new FileNotFoundException("no folder found");
            }
            FileWriter fw = new FileWriter(".\\Output.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.printf("\n%-25s %-20s %-20s %-20s %-20s %-30s %n", "File Name", "Readable", "Writable", "Executable",
            "Last Modified", "Path");
           
            fw.flush();
            fw.close();

            if (!f.isDirectory()) {
                myThread.writer(f,pw);
            } else {
                Thread thread = new Thread(new myThread(f));
                thread.start();
            }
           
        }
        System.out.println("Done, You can see the resualt in Output.txt ");

    }  
}