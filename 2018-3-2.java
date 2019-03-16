import java.util.Scanner;

public class Main {
    public static void main(String[] args){ 
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
    	int len = scanner.nextInt();
    	int t = scanner.nextInt();
    	Ball[] balls = new Ball[n];
    	for(int i=0; i<n; i++) {
    		balls[i] = new Ball();
    		balls[i].speed = 1;
    		balls[i].pos = scanner.nextInt();
    	}
    	
    	for(int i=0; i<t; i++) {
    		Boolean[] cal = new Boolean[n];
    		for(int p=0; p<n; p++) {
    			cal[p] = false;
    		}
    		for(int j=0; j<n; j++) {
    			for(int k=0; k<n; k++) {
    				if(j==k) {continue;}
    				if(balls[k].pos == balls[j].pos && cal[k] == false) {
    					balls[k].speed*=-1;
    					balls[j].speed*=-1;
    					cal[k] = true;
    					break;
    				}
    			}
    			if(balls[j].pos ==0 || balls[j].pos == len) {
    				balls[j].speed *=-1;
    			}
    			balls[j].pos = balls[j].pos + balls[j].speed;
    		}
    	}
    	for(int i=0; i<n; i++) {
    		System.out.print(balls[i].pos);
    		if(i != n-1) {
    			System.out.print(" ");
    		}
    	}
        return;
    }
    
    public static class Ball{
    	public int speed;
    	public int pos;
    }
}
  


