import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class myThread implements Runnable {
    // create lock object
    private File dir;
    private static final Lock lock = new ReentrantLock();

    public myThread(File dir) {
        this.dir = dir;
    }

    @Override
    public void run() {
        try (FileWriter fw = new FileWriter(".\\output.txt", true);
                PrintWriter pw = new PrintWriter(fw);) {

            System.out.println("The running Thread id is : " + Thread.currentThread().getId()
                    + " Processing the path : " + dir.getPath());

            File[] files = dir.listFiles();
            
            System.out.println("");
            if (files != null) {
                for (File file : files) {
                        if (file.isDirectory()) {
                            Thread thread = new Thread(new myThread(file));
                            thread.start();
                            thread.join();
                        } else {
                            writer(file, pw);
                        }
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void writer(File file, PrintWriter pw) {
        // starting the lock
        lock.lock();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastModified = sdf.format(new Date(file.lastModified()));

            pw.printf("%-25s | %-15s |  %-15s |  %-15s | %-20s | %-30s %n\n", file.getName(),
                    file.canRead(), file.canWrite(), file.canExecute(), lastModified, file.getAbsolutePath());

            System.out.printf("%-25s | %-15s |  %-15s |  %-15s | %-20s | %-30s %n\n", file.getName(),
                    file.canRead(), file.canWrite(), file.canExecute(), lastModified, file.getAbsolutePath());
            ;

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            // unlock the lock
            lock.unlock();
        }
    }
}