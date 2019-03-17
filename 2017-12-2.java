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
    }import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	int n=scanner.nextInt();
    	int k=scanner.nextInt();
    	scanner.close();
    	Child[] childs = new Child[n];
    	for(int i=0; i<n; i++) {
    		childs[i] = new Child();
    		childs[i].lose = false;
    		childs[i].seq = i+1;
    	}
    	int i=1;
    	int m=0;
    	do {
    		while (childs[m].lose==true) {
				m++;
				if(m==childs.length) {
					m=0;
				}
			}
    		if(i%k==0 || i%10==k) {
    			childs[m].lose = true;
    			n--;
    		}
    		i++;
    		m++;
    		if(m == childs.length) {
    			m=0;
    		}
    	}while(n>1);
    	for(int u=0; u<childs.length; u++) {
    		if(!childs[u].lose) {
    			System.out.println(childs[u].seq);
    			break;
    		}
    	}
    }
    public static class Child{
    	public int seq;
    	public boolean lose;
    }
}


}

