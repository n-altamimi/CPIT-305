import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CurrencyConverter {
  public static void main(String[] args) {

    int command = 0;

    Scanner in = new Scanner(System.in);

    do {
      System.out.println("\nEnter the option number ");
      System.out.println("1- Display available currencies");
      System.out.println("2- Exchange currency ");
      System.out.println("3- Exit ");

      command = in.nextInt();

      if (command == 1) {
        DisplayCurrncies();
      }
      if (command == 2) {
        Exchange(in);

      }
    } while (command != 3);
  }

  static void DisplayCurrncies() {

    String url = "https://api.frankfurter.app/currencies";

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseBody = response.body();

      JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
      String s = json.toString();
      System.out.println("\n \n Available Currencies:");

      int num = 1;
      StringTokenizer st = new StringTokenizer(s, ",");
      while (st.hasMoreTokens()) {
        System.out.println(num++ + "-  " + st.nextToken());
      }

    } catch (Exception e) {
      System.err.println(e);
    }
  }

  private static void Exchange(Scanner in) {
    String From, To;
    int amount;

    System.out.println("Enter the base currency");
    From = in.next().toUpperCase();

    System.out.println("Enter the target currency ");
    To = in.next().toUpperCase();

    System.out.println("Enter the amount to exchange: ");
    amount = in.nextInt();

    String url = "https://api.frankfurter.app/latest?from=" + From + "&to=" + To;

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String responseBody = response.body();

      JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
      JsonObject rates = json.getAsJsonObject("rates");

      if (rates.has(To)) {
        double exchangeRate = rates.get(To).getAsDouble();
        System.out.println("Exchange Rate (" + From + " to " + To + "): " + exchangeRate);

        System.out.println(amount + " " + From + " To " + To + " = " + exchangeRate * amount);
      } else
        System.out.println("Currency not supported");

    } catch (Exception e) {
      System.err.println(e);
    }

  }
}