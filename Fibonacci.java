import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args){
        /*output the commands and grab user input
        Note that there is no error checking here
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of the program you wish to use: ");
        System.out.println("1 = Basic Recursion: O^n");
        System.out.println("2 = Dynamic Recursion: O(n)");
        System.out.println("3 = Matrix Example: O(Logn)");
        System.out.println("4 = Formula: O(1)");

        int usage = scanner.nextInt();
        System.out.println("Please enter the number you wish to display: ");
        int num = scanner.nextInt();
        BigInteger bi1 = new BigInteger(String.valueOf(num));

        //choose which method we are using based on user input "usage"
        switch(usage){
            case 1:
                if(num > 46){
                    /*
                    This method wraps around if it is above 46 so we must limit the numbers
                     */
                    System.out.println("Number is too large for this method.");
                    System.out.println("Please choose a number below 47.");
                    System.out.println("Exiting");
                    System.exit(0);
                }
                System.out.println(fib1(num));
                break;
            case 2:
                System.out.println(fib2(bi1, num));
                break;
            case 3:
                System.out.println(fib3(num));
                break;
            case 4:
                System.out.println(fib4(num));
                break;
            default:
                System.out.println("Unexpected value in program selection: " + usage);
                System.exit(0);
        }
    }

    /* This is the code for the most basic fib sequence
    It has an exponential time complexity which makes it very poor
    We can still use ints for this one because it won't realistically go high enough*/
    static int fib1(int num){
        if(num <= 1){
            return num;
        }
        return fib1(num-1) + fib1(num-2);
    }

    /*This is the code for the dynamic recursion
    It has a linear time complexity when considering the call stack
    This can be further optimized just by reducing the space used
    by storing only the previous two numbers in the sequence
     */
    static BigInteger fib2(BigInteger bigNum, int num){
        if(bigNum.equals(BigInteger.ONE) || bigNum.equals(BigInteger.ZERO)){
            return BigInteger.valueOf(1);
        }
        BigInteger[] farray = new BigInteger[num+1];
        farray[0] = BigInteger.valueOf(0);
        farray[1] = BigInteger.valueOf(1);

        for(int i = 2; i <=num; i++)
        {
            farray[i] = farray[i-1].add(farray[i-2]);
        }
        return farray[num];
    }

    /*

     */
    static BigInteger fib3(int num){
        if(num == 0) {
            return BigInteger.valueOf(0);
        }
        BigInteger[][] farray = new BigInteger[][]{{BigInteger.valueOf(1), BigInteger.valueOf(1)}, {BigInteger.valueOf(1), BigInteger.valueOf(0)}};

        power(farray, num-1);
        return farray[0][0];

    }

    static void power(BigInteger[][] array1, int num) {
        if(num == 0 || num == 1){
            return;
        }
        BigInteger[][] array2 = new BigInteger[][]{{BigInteger.valueOf(1), BigInteger.valueOf(1)}, {BigInteger.valueOf(1), BigInteger.valueOf(0)}};
        power(array1, num/2);
        multiply(array1, array1);

        if(num%2 !=0){
            multiply(array1, array2);
        }
    }

    static void multiply(BigInteger[][] array1, BigInteger[][] array2){
        BigInteger b1 = (array1[0][0].multiply(array2[0][0])).add(array1[0][1].multiply(array2[1][0]));
        BigInteger b2 = (array1[0][0].multiply(array2[0][1])).add(array1[0][1].multiply(array2[1][1]));
        BigInteger b3 = (array1[1][0].multiply(array2[0][0])).add(array1[1][1].multiply(array2[1][0]));
        BigInteger b4 = (array1[1][0].multiply(array2[0][1])).add(array1[1][1].multiply(array2[1][1]));

        array1[0][0] = b1;
        array1[0][1] = b2;
        array1[1][0] = b3;
        array1[1][1] = b4;
    }

    /* This is the code for the fourth method
    In this method we are simply using the formula to obtain our answer
     */
    static BigDecimal fib4(int num){
        MathContext scale1 = new MathContext(1000);
        BigDecimal five = new BigDecimal("5");
        BigDecimal bd1;
        bd1 = (BigDecimal.ONE.add(five.sqrt(scale1)).divide(BigDecimal.valueOf(2),RoundingMode.HALF_UP));
        bd1 = bd1.pow(num).divide(five.sqrt(scale1), RoundingMode.HALF_UP);

        return bd1.setScale(0, RoundingMode.HALF_DOWN);
    }
}
