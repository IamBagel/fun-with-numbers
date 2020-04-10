import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class primeFactorization {
    public static void main(String[] args){
        System.out.println("Please enter the number you wish to find the prime factors for: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(prime(num));
    }
    public static List<Integer> prime(int num){
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 2; i <= num/i; i++){
            while(num%i == 0){
                list.add(i);
                num /=i;
            }
        }
        if(num > 1){
            list.add(num);
        }
        return list;
    }

}
