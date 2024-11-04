import java.util.InputMismatchException;
import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) throws Exception {

        System.out.println("choose an integer number form 0 - 100 ");
        while (true)
        try {
            

            Scanner read = new Scanner(System.in); 
            int value = read.nextInt(); 
            if (value < 0 || value > 100){
                throw new RuntimeException();
            }
            System.out.println("The square of " + value + " is : " +(value *  value) );
            break; 
            
        } catch (InputMismatchException e) {
            System.out.println("please inter an intger number");
    }
        catch (RuntimeException e ) {
            System.out.println("Enter a number within the range 1 - 100 ");
        }

}
}