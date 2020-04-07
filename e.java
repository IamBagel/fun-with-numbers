import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class e {

    public static void main(String[] args){
        /*Initialize Scanner and obtain our user information
        * NOTE: there is NO ERROR CATCHING HERE
        * if the user submits certain things the code will break.*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the precision of e you wish to calculate");
        int num = scanner.nextInt();
        MathContext scale = MathContext.UNLIMITED;
        BigDecimal b1 = new BigDecimal(num, scale);
        /*output our information
        * done by calling the exponent method with the user's selected number*/
        System.out.println("e = " + (exponent(b1, scale, num*2)));
    }

    static BigDecimal exponent(BigDecimal num, MathContext scale, int scale2){
        BigDecimal answer = new BigDecimal(0, scale);
        BigDecimal one = new BigDecimal(1, scale);
        BigDecimal i = new BigDecimal(0, scale);
        while(i.compareTo(num) !=0){
            answer = answer.add(one.divide(factorial(i, scale), scale2, RoundingMode.HALF_UP));
            i = i.add(one);
        }
        return answer;
    }

    static BigDecimal factorial(BigDecimal input, MathContext scale){
        BigDecimal answer = new BigDecimal(1, scale);
        BigDecimal i = new BigDecimal(0, scale);
        BigDecimal one = new BigDecimal(1, scale);
        BigDecimal zero = new BigDecimal(0, scale);
        /*prevents dividing by zero*/
        if (input.compareTo(zero) != 0 && input.compareTo(one) !=0) {
            while (i.compareTo(input) != 0) {
                answer = answer.multiply(i.add(one), scale);
                i = i.add(one);
            }
        }
        //storing the previous number would be much more effective. This should be implemented
        return answer;
    }
}
