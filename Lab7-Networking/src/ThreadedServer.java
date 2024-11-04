import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadedServer implements Runnable {
    // create a lock object
    private static final Lock lock = new ReentrantLock();

    private Socket client;
    String name;
    String BD;
    String gender;
    String na;

    public ThreadedServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread started");
            // Print writer for asking user some info
            PrintWriter pr = new PrintWriter(client.getOutputStream(), true);
            // Scanner for reading the user input
            Scanner sc = new Scanner(client.getInputStream());
            File fr = new File("./output.txt");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fr, true));

            pr.println("Enter your name : ");
            name = sc.nextLine();
            pr.println("Enter your BirthDate in this foramt yyyy/mm/dd : ");
            BD = sc.nextLine();
            pr.println("Enter your Gender : ");
            gender = sc.nextLine();
            pr.println("Enter your Nationality : ");
            na = sc.nextLine();

            Userinfo u = new Userinfo(name, BD, gender, na);

            // here we have the crtical area so we implemt lock here
            lock.lock();
            try {
                oos.writeObject(u.toString());
            } finally {
                lock.unlock();
            }

            pr.println("done");
            oos.flush();
            oos.close();
            sc.close();
            client.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
