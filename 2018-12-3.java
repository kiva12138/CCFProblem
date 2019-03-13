import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
    	int numOfInput = scanner.nextInt();
    	String[] inputIp = new String[numOfInput];
    	for(int i=0; i<numOfInput; i++) {
    		inputIp[i] = new String();
    		inputIp[i] = scanner.next();
    	}
    	scanner.close();
    	IpPrefix ipPrefixs[] = new IpPrefix[numOfInput];
    	for(int i=0; i<numOfInput; i++) {
    		ipPrefixs[i] = new IpPrefix();
    	}
    	
    	//输入
    	for(int i=0; i<numOfInput; i++) {
    		ipPrefixs[i] = new IpPrefix();
    		String[] split1 = inputIp[i].split("/");
    		if(split1.length == 2) {
    			ipPrefixs[i].len = Integer.valueOf(split1[1]);
    			String[] split2 = split1[0].split("\\.");
    			for(int j=0; j<split2.length; j++) {
    				ipPrefixs[i].ipOf10[j] = Integer.valueOf(split2[j]);
    				ipPrefixs[i].ipOf2[j] = String.format("%08d", Integer.valueOf(Integer.toBinaryString(Integer.valueOf(split2[j]))));
    			}
    		}else {
    			String[] split2 = split1[0].split("\\.");
    			if(split2.length == 1) {
    				ipPrefixs[i].len = 8;
    			}else if(split2.length == 2) {
    				ipPrefixs[i].len = 16;
    			}else if(split2.length == 3) {
    				ipPrefixs[i].len = 24;
    			}else if(split2.length == 4) {
    				ipPrefixs[i].len = 32;
    			}
    			for(int j=0; j<split2.length; j++) {
    				ipPrefixs[i].ipOf10[j] = Integer.valueOf(split2[j]);
    				ipPrefixs[i].ipOf2[j] = String.format("%08d", Integer.valueOf(Integer.toBinaryString(Integer.valueOf(split2[j]))));
    			}
    		}
    	}
    	
    	//排序
    	for(int i=0; i<numOfInput; i++) {
    		IpPrefix temp = new IpPrefix();
    		for(int j=i+1; j<numOfInput; j++) {
    			for(int k=0; k<4; k++) {
    				if(ipPrefixs[i].ipOf10[k]>ipPrefixs[j].ipOf10[k]) {
    					temp = ipPrefixs[i];
    					ipPrefixs[i] = ipPrefixs[j];
    					ipPrefixs[j] = temp;
    					break;
    				}
    				if(ipPrefixs[i].ipOf10[k]==ipPrefixs[j].ipOf10[k] && k==3) {
    					if(ipPrefixs[i].len>ipPrefixs[j].len) {
    						temp = ipPrefixs[i];
        					ipPrefixs[i] = ipPrefixs[j];
        					ipPrefixs[j] = temp;
        					break;
    					}
    				}
    			}
    		}
    	}
    	
    	//合并
    	for(int i=0; i<numOfInput-1; i++) {
    		Boolean delete = true;
    		for(int j=0; j<ipPrefixs[i].len; j++) {
    			if(j < 8) {
    				if(ipPrefixs[i].ipOf2[0].charAt(j) != ipPrefixs[i+1].ipOf2[0].charAt(j)) {
    					delete = false;
    					break;
    				}
    			}else if(j < 16) {
    				if(ipPrefixs[i].ipOf2[1].charAt(j-8) != ipPrefixs[i+1].ipOf2[1].charAt(j-8)) {
    					delete = false;
    					break;
    				}
    			}else if(j < 24) {
    				if(ipPrefixs[i].ipOf2[2].charAt(j-16) != ipPrefixs[i+1].ipOf2[2].charAt(j-16)) {
    					delete = false;
    					break;
    				}
    			}else if(j < 32) {
    				if(ipPrefixs[i].ipOf2[3].charAt(j-24) != ipPrefixs[i+1].ipOf2[3].charAt(j-24)) {
    					delete = false;
    					break;
    				}
    			}
    		}
    		if(delete) {
    			for(int k=i+1; k<numOfInput-1; k++) {
    				ipPrefixs[k] = ipPrefixs[k+1];
    			}
    			numOfInput--;
    			i--;
    		}
    	}
    	
    	//同级合并
    	for(int i=0; i<numOfInput-1; i++) {
    		if(ipPrefixs[i].len != ipPrefixs[i].len) {
    			continue;
    		}else {
    			IpPrefix temp = new IpPrefix();
    			temp = (IpPrefix)ipPrefixs[i].clone();
    			temp.len--;
    			IpPrefix tempUnion = new IpPrefix();
    			if(temp.len <0 || temp.len >32) {
    				continue;
    			}else {
    				int lenToUnion = -1;
    				for(int j=0; j<ipPrefixs[i].len; j++) {
    					if(j < 8) {
    	    				if(ipPrefixs[i].ipOf2[0].charAt(j) != ipPrefixs[i+1].ipOf2[0].charAt(j)) {
    	    					lenToUnion = j;
    	    					break;
    	    				}
    	    			}else if(j < 16) {
    	    				if(ipPrefixs[i].ipOf2[1].charAt(j-8) != ipPrefixs[i+1].ipOf2[1].charAt(j-8)) {
    	    					lenToUnion = j;
    	    					break;
    	    				}
    	    			}else if(j < 24) {
    	    				if(ipPrefixs[i].ipOf2[2].charAt(j-16) != ipPrefixs[i+1].ipOf2[2].charAt(j-16)) {
    	    					lenToUnion = j;
    	    					break;
    	    				}
    	    			}else if(j < 32) {
    	    				if(ipPrefixs[i].ipOf2[3].charAt(j-24) != ipPrefixs[i+1].ipOf2[3].charAt(j-24)) {
    	    					lenToUnion = j;
    	    					break;
    	    				}
    	    			}
    				}
    				if(lenToUnion>=0) {
    					tempUnion.len = lenToUnion;
    					if(tempUnion.len <= 8) {
							String string = "00000000";
							StringBuilder stringBuilder = new  StringBuilder(string);
    						for(int k=0; k<tempUnion.len; k++) {
    							stringBuilder.setCharAt(k, ipPrefixs[i].ipOf2[0].charAt(k));
    						}
							tempUnion.ipOf2[0] = stringBuilder.toString();
    					}else if(tempUnion.len <= 16) {
							String string = "00000000";
							StringBuilder stringBuilder = new  StringBuilder(string);
    						for(int k=0; k<tempUnion.len; k++) {
    							tempUnion.ipOf2[0] = ipPrefixs[i].ipOf2[0];
    							stringBuilder.setCharAt(k-8, ipPrefixs[i].ipOf2[1].charAt(k-8));
    						}
							tempUnion.ipOf2[1] = stringBuilder.toString();
    					}else if(tempUnion.len <= 24) {
							String string = "00000000";
							StringBuilder stringBuilder = new  StringBuilder(string);
    						for(int k=0; k<tempUnion.len; k++) {
    							tempUnion.ipOf2[0] = ipPrefixs[i].ipOf2[0];
    							tempUnion.ipOf2[1] = ipPrefixs[i].ipOf2[1];
    							stringBuilder.setCharAt(k-16, ipPrefixs[i].ipOf2[2].charAt(k-16));
    						}
							tempUnion.ipOf2[2] = stringBuilder.toString();
    					}else if(tempUnion.len <= 32) {
							String string = "00000000";
							StringBuilder stringBuilder = new  StringBuilder(string);
    						for(int k=0; k<tempUnion.len; k++) {
    							tempUnion.ipOf2[0] = ipPrefixs[i].ipOf2[0];
    							tempUnion.ipOf2[1] = ipPrefixs[i].ipOf2[1];
    							tempUnion.ipOf2[2] = ipPrefixs[i].ipOf2[2];
    							stringBuilder.setCharAt(k-24, ipPrefixs[i].ipOf2[3].charAt(k-24));
    						}
							tempUnion.ipOf2[3] = stringBuilder.toString();
    					}
    					
    					for(int k=0; k<4; k++) {
    						tempUnion.ipOf10[k] = Integer.valueOf(tempUnion.ipOf2[k], 2);
    					}
    					
    					if(temp.len == tempUnion.len && temp.ipOf10[0]==tempUnion.ipOf10[0] &&
    							temp.ipOf10[1]==tempUnion.ipOf10[1] &&temp.ipOf10[2]==tempUnion.ipOf10[2]
    							&&temp.ipOf10[3]==tempUnion.ipOf10[3]) {
    						ipPrefixs[i] = temp;
    						for(int k=i+1; k<numOfInput-1; k++) {
    							ipPrefixs[k] = ipPrefixs[k+1];
    						}
    						numOfInput--;
    						if(i!=0) {
    							i-=2;
    						}
    					}
    					
    				}else {
    					break;
    				}
    			}
    		}
    	}
    	
    	//输出
    	for(int i=0; i<numOfInput; i++) {
    		System.out.println(ipPrefixs[i].ipOf10[0]+"."+
    						ipPrefixs[i].ipOf10[1]+"."+
    						ipPrefixs[i].ipOf10[2]+"."+
    						ipPrefixs[i].ipOf10[3]+"/"+ipPrefixs[i].len);
    	}
    	
    }
    public static class IpPrefix implements Cloneable{
		public int len;
		public int[] ipOf10;
		public String[] ipOf2;
		public IpPrefix(){
			ipOf10 = new int[4];
			ipOf2 = new String[4];
			ipOf2[0] = "00000000";
			ipOf2[1] = "00000000";
			ipOf2[2] = "00000000";
			ipOf2[3] = "00000000";
		}
		@Override  
		  public Object clone() {  
			IpPrefix ipPrefix = null;  
		    try{  
		    	ipPrefix = (IpPrefix)super.clone();  
		    }catch(CloneNotSupportedException e) {  
		        e.printStackTrace();  
		    }  
		    return ipPrefix;  
		  } 
	}
}
