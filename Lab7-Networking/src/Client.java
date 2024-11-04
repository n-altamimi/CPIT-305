import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket soc = new Socket("127.0.0.1", 9000);
            if (soc.isConnected()) {
                System.out.println("server is up ");
            }
            // this scanner for reading the server side
            Scanner in = new Scanner(soc.getInputStream());
            // this scanner for reading the user input and print it to the server side
            Scanner uin = new Scanner(System.in);
            PrintWriter pr = new PrintWriter(soc.getOutputStream(),true);

            while (in.hasNextLine() ) {
                String prompt = in.nextLine();
                System.out.println(prompt);
                if (prompt.equals("done")) break;
                String response = uin.nextLine();
                pr.println(response);
            }
            in.close();
            uin.close();
            soc.close();
            System.out.println("You are done");

        } catch (IOException e) {
            System.err.println("error");

        }
    }
}
