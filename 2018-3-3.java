import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	
    	int regnum = scanner.nextInt();
    	int addnum = scanner.nextInt();
    	
    	Reg[] regs = new Reg[regnum];
    	Add[] adds = new Add[addnum];
    	
    	String temp = "";
    	List<String> templist = new ArrayList<>();
    	for(int i=0; i<regnum; i++) {
    		regs[i] = new Reg();
    		temp = scanner.next();
    		templist = Arrays.asList(temp.split("/"));
    		for(int j=1; j<templist.size(); j++) {
    			regs[i].values.add(templist.get(j));
    			regs[i].numOfValue++;
    		}
    		temp = scanner.next();
    		regs[i].name = temp;
    	}
    	
    	for(int i=0; i<addnum; i++) {
    		adds[i] = new Add();
    		temp = scanner.next();
    		templist = Arrays.asList(temp.split("/"));
    		for(int j=1; j<templist.size(); j++) {
    			adds[i].values.add(templist.get(j));
    			adds[i].numOfValue++;
    		}
    	}
    	
    	scanner.close();
    	
    	for(int i=0; i<addnum; i++) {
    		int num = 0;
    		Boolean match = true;
    		String name = "";
    		int k=0;
    		List<String> list = new ArrayList<String>();
    		for(int j=0; j<regnum; j++) {
    			match = true;
    			num = 0;
    			list.clear();
				for(k=0; k<regs[j].numOfValue; k++) {
					if(k>=adds[i].numOfValue) {
						match = false;
						break;
					}else {
						if(regs[j].values.get(k).equals("<int>") && isNumeric(adds[i].values.get(k))) {
    						list.add(String.valueOf(Integer.valueOf(adds[i].values.get(k))));
    						num++;
    					}else if(regs[j].values.get(k).equals("<str>")) {
    						num++;
    						list.add(adds[i].values.get(k));
    					}else if(regs[j].values.get(k).equals("<path>")) {
    						String tempStr = adds[i].values.get(k);
    						int u=k+1;
    						for(; u<adds[i].numOfValue; u++) {
    							tempStr = tempStr+"/"+adds[i].values.get(u);
    							k++;
    						}
    						k++;
    						num++;
    						list.add(tempStr);
    						match = true;
    						break;
    					}else if(regs[j].values.get(k).equals(adds[i].values.get(k))){
    					}else {
    						match = false;
    						break;
    					}
					}
				}
				if(k<adds[i].numOfValue) {
					match = false;
				}
				if(match) {
					name = regs[j].name;
					break;
				}
    		}
    		if(match) {
				System.out.print(name);
				for(k=0; k<num; k++) {
					System.out.print(" "+list.get(k));
				}
				System.out.println();
			}else {
				System.out.println("404");
			}
    	}
    	
        return;
    }
    
    public static class Reg{
    	public int numOfValue;
    	public List<String> values= new ArrayList<>();
    	public String name;
    }
    
    public static class Add{
    	public int numOfValue;
    	public List<String> values= new ArrayList<>();
    }
    

	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
  


