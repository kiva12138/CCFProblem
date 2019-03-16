import java.util.Scanner;

public class Main {
    public static void main(String args[]){ 
    	Scanner scanner = new Scanner(System.in);
    	int num = 0;
    	int [] step = new int[30];
    	do {
    		step[num] = scanner.nextInt();
    	}while(step[num++]!=0);
    	int result = 0;
    	int extra = 0;
    	for(int i=0; i<num; i++) {
    		if(step[i] == 1) {
    			extra = 0;
    			result+=1;
    		}else if(step[i] == 2) {
    			extra+=2;
    			result+=extra;
    		}else {
    			break;
    		}
    	}
    	System.out.println(result);
    	scanner.close();
        return;
    }
}
  


