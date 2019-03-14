import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
    	int m = scanner.nextInt();
    	scanner.nextLine();
    	Element[] elements = new Element[n];
    	for(int i=0; i<n; i++) {
    		elements[i] = new Element();
    		elements[i].id = new String[n];
    		elements[i].tag = new String[n];
    	}
    	String[] selects = new String[m];
    	for(int i=0; i<m; i++) {
    		selects[i] = new String();
    	}
    	
    	String temp = "";
    	for(int i=0; i<n; i++) {
    		temp = scanner.nextLine();
    		String[] tempList = temp.split("\\.");
    		int len = tempList.length;
    		elements[i].level = (len-1)/2;
    		String value = tempList[len-1];
    		if(value.split("#").length == 1) {
    			elements[i].tag[0] = value;
    		}else {
    			elements[i].id[0] = value.split("#")[1];
    			elements[i].tag[0] = value.split(" ")[0];
    		}
    	}
    	for(int i=0; i<m; i++) {
    		selects[i] = scanner.nextLine();
    	}
    	scanner.close();
    	for(int i=0; i<n; i++) {
    		int pos = 1;
    		int currentLevel = elements[i].level;
    		for(int j=i-1; j>=0; j--) {
    			if(elements[j].level == currentLevel-1) {
    				currentLevel--;
    				elements[i].tag[pos] = elements[j].tag[0];
    				elements[i].id[pos] = elements[j].id[0];
    				pos++;
    			}
    		}
    	}
    	
    	for(int i=0; i<m; i++) {
    		int numOfResult = 0;
    		int[] results = new int[n];
    		Boolean match = true;
    		String[] targets = selects[i].split(" ");
    		for(int k=0; k<n; k++) {
    			for(int j=targets.length-1; j>=0; j--) {
        			if(targets[j].split("#").length==2) {
        				if(!targets[j].split("#")[1].equals(elements[k].id[targets.length-1-j])) {
        					match = false;
        					break;
        				}
        			}else {
        				if(!targets[j].equals(elements[k].tag[targets.length-1-j])) {
        					match = false;
        					break;
        				}
        			}
        		}
    			if(match) {
    				numOfResult++;
    				results[numOfResult-1] = k+1;
    			}
    			match = true;
    		}
    		System.out.print(numOfResult);
    		for(int p=0; p<numOfResult; p++) {
    			System.out.print(" "+results[p]);
    		}
    		System.out.println();
    	}
    }
    public static class Element{
    	public String tag[];
    	public String id[];
    	public int level = -1;
    }
}
