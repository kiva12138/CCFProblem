import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
    	int[] numbers = new int[n];
    	int result=999;
    	for(int i=0; i<n; i++) {
    		numbers[i] = scanner.nextInt();
    	}
    	for(int i=0; i<n; i++) {
    		for(int j=i+1; j<n; j++) {
    			if(Math.abs(numbers[i]-numbers[j]) < result) {
    				result = Math.abs(numbers[i]-numbers[j]);
    			}
    		}
    	}
    	System.out.println(result);
    	scanner.close();
    }
}

