import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


class Pi {
    private static final BigDecimal ONE = new BigDecimal(1);//constant with Value 1
    private static final BigDecimal TWO = new BigDecimal(2);//constant with Value 2
    private static final BigDecimal FOUR = new BigDecimal(4);//constant with Value 4 (used in multiplying at end)

    public static void main(String[] args) {
        /* initialize and obtain user input */
        Scanner scanner = new Scanner(System.in);
        int num;
        System.out.println("Please enter the digits of Pi you wish to calculate:");
        num = scanner.nextInt();

        /* run method calculate with user input and print out our answer */
        System.out.println(calculate(num));
        System.out.println("Please note that PI may not be accurate in the last few digits, this is due to rounding.");
    }

    /* Gauss-Legendre Algorithm for calculating PI */
    public static BigDecimal calculate(final int NUM) {
        BigDecimal b1 = ONE;
        BigDecimal b2 = ONE.divide(sqrt(TWO, NUM), NUM, RoundingMode.HALF_UP);
        BigDecimal b3 = new BigDecimal("0.25");
        BigDecimal b4 = ONE;
        BigDecimal b5;

        while (!b1.equals(b2)) {
            b5 = b1;
            b1 = b1.add(b2).divide(TWO, NUM, RoundingMode.HALF_UP);
            b2 = sqrt(b2.multiply(b5), NUM);
            b3 = b3.subtract(b4.multiply(b5.subtract(b1).multiply(b5.subtract(b1))));
            b4 = b4.multiply(TWO);
        }

        return b1.add(b2).multiply(b1.add(b2)).divide(b3.multiply(FOUR), NUM, RoundingMode.HALF_UP);
    }

    /* Newton's method */
    public static BigDecimal sqrt(BigDecimal NUM1, final int NUM2) {
        BigDecimal b1 = new BigDecimal("0");
        BigDecimal b2 = BigDecimal.valueOf(Math.sqrt(NUM1.doubleValue()));

        while (!b1.equals(b2)) {
            b1 = b2;
            b2 = NUM1.divide(b1, NUM2, RoundingMode.HALF_UP);
            b2 = b2.add(b1);
            b2 = b2.divide(TWO, NUM2, RoundingMode.HALF_UP);
        }

        return b2;
    }
}