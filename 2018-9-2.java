import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	int n, m, root;
    	n=scanner.nextInt();
    	m=scanner.nextInt();
    	root=scanner.nextInt();
    	Node[] nodes = new Node[m];
    	for(int i=0; i<m; i++) {
    		nodes[i] = new Node();
    		nodes[i].n1 = scanner.nextInt();
    		nodes[i].n2 = scanner.nextInt();
    		nodes[i].dis = scanner.nextInt();
    		nodes[i].join = 0;
    	}
    	scanner.close();
    	for(int i=0; i<m; i++) {
    		for(int j=i+1; j<m; j++) {
    			if(nodes[i].dis > nodes[j].dis) {
    				int temp = nodes[i].dis;
    				nodes[i].dis = nodes[j].dis;
    				nodes[j].dis = temp;
    				temp = nodes[i].n1;
    				nodes[i].n1 = nodes[j].n1;
    				nodes[j].n1 = temp;
    				temp = nodes[i].n2;
    				nodes[i].n2 = nodes[j].n2;
    				nodes[j].n2 = temp;
    			}
    		}
    	}
    	
    	Node[] result = new Node[n-1];
    	for(int i=0; i<n-1; i++) {
    		result[i] = new Node();
    	}
    	int[] cla = new int[n+1];
    	for(int i=0; i<n; i++) {
    		cla[i] = i;
    	}
    	for(int i=0; i<n-1; i++) {
    		for(int j=0; j<m; j++) {
    			if(nodes[j].join == 0 && cla[nodes[j].n1] != cla[nodes[j].n2]) {
    				result[i].n1 = nodes[j].n1;
    				result[i].n2 = nodes[j].n2;
    				result[i].dis = nodes[j].dis;
    				nodes[j].join = 1;
    				cla[nodes[j].n2] = cla[nodes[j].n2];
    				break;
    			}
    		}
    	}
    	
    	int max = 0;
    	for(int i=0; i<n-1; i++) {
    		if(result[i].dis>max) {
    			max = result[i].dis;
    		}
    	}
    	System.out.println(max);
    }
    public static class Node{
    	public int n1;
    	public int n2;
    	public int dis;
    	public int join;
    }
}
