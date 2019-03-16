import java.util.Scanner;

public class Main {
	public static int n = 0;
	public static int [][] pannel = new int [3][3];
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	n=scanner.nextInt();
    	for(int i=0; i<n; i++) {
    		for(int k=0; k<3; k++) {
    			for(int j=0; j<3; j++) {
    				pannel[k][j] = scanner.nextInt();
    			}
    		}
    		System.out.println(cal(1));
    	}
    }
    public static int cal(int k) {
    	int num = 0;
    	for(int i=0; i<3; i++) {
    		for(int j=0; j<3; j++) {
    			if(pannel[i][j]==0) {
    				num++;
    			}
    		}
    	}
    	if(k==1 && win(2)) {
    		return -num-1;
    	}
    	if(k==2 && win(1)) {
    		return num+1;
    	}
    	if(num ==0 ) {
    		return 0;
    	}
    	int min = 999;
    	int max = -999;
    	for(int i=0; i<3; i++) {
    		for(int j=0; j<3; j++) {
    			if(pannel[i][j] == 0) {
    				pannel[i][j] = k;
    				if(k==1) {
    					max = max>cal(2)?max:cal(2);
    				}else if(k==2) {
    					min=min<cal(1)?min:cal(1);
    				}
    				pannel[i][j] = 0;
    				
    			}
    		}
    	}

		if(k==1) {
			return max;
		}else {
			return min;
		}
    }
    public static boolean win(int k) {
    	for (int i = 0; i < 3; i++) {
			if(pannel[1][i]==pannel[2][i] 
					&& pannel[2][i]==pannel[0][i] 
					&& pannel[0][i]==k) {
				return true;
			}
			if(pannel[i][1]==pannel[i][2] 
					&& pannel[i][2]==pannel[i][0] 
					&& pannel[i][0]==k) {
				return true;
			}
		}
    	if(pannel[1][1]==pannel[2][2] 
    			&& pannel[1][1]==pannel[0][0]
    			&& pannel[0][0]==k) {
    		return true;
    	}
    	if(pannel[0][2]==pannel[1][1] 
    			&& pannel[1][1]==pannel[2][0]
    			&& pannel[0][2]==k) {
    		return true;
    	}
    	return false;
    }
}
  


