
import java.util.InputMismatchException;
import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) throws Exception {
      
        System.out.println("choose an integer number form 0 - 100 ");
        while (true)
        try {
            Scanner read = new Scanner(System.in); 
            int value = read.nextInt(); 
            if (value < 0 || value > 100){
                throw new outOfRange("Enter a number within the range 0 -100"); 
            }
            System.out.println("The square of " + value + " is : " +(value *  value) );
            break; 
            
        } 
        catch (InputMismatchException e) {
            System.err.println("please enter an intger number");
        }
        catch (outOfRange e) {
            System.err.println(e.getMessage());
    }

    
} 


static class outOfRange extends Exception {
    public outOfRange(){
        super();
    }

    public outOfRange(String s){
        super(s); 
    }

}
}