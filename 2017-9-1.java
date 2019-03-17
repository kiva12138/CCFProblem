import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
        	Scanner scanner = new Scanner(System.in);
        	int n = scanner.nextInt();
        	int num = (int)n/10;
        	int m5 = (int)(num/5)*2;
        	int m3 = (int)((num-m5*5/2)/3);
        	System.out.println(num+m5+m3);
        	scanner.close();
        }
}