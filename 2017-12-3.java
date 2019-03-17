import java.util.Scanner;
import java.util.Stack;

public class Main {
    	static int min = 99999999;
        static int[][] e ;
        static int[][] ec ;
        static int[] book ;
        static int[] path ;
        static int numOfPath = 0;
        //static int current1Len = 0;
        static Stack<Integer> stack = new Stack<Integer>();
        static int n, m;
        static Scanner input = new Scanner(System.in);

        public static void main(String[] args) {
            n = input.nextInt();
            m = input.nextInt();
            e = new int[n+1][n+1];
            ec = new int[n+1][n+1];
            book = new int[m+1];
            path = new int [m+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        e[i][j] = 0;
                    } else {
                        e[i][j] = 99999999;
                    }
                }
            }
            for (int i = 1; i <= m; i++) {
            	int type = input.nextInt();
                int a = input.nextInt();
                int b = input.nextInt();
                int c = input.nextInt();
                e[a][b] = c;
                ec[a][b] = type;
            }
            
            book[1] = 1;
            stack.push(1);
            dfs(1, 0, 0);
            System.out.println(min);
            /*System.out.println(numOfPath);
            for(int i=0; i<numOfPath; i++) {
            	System.out.print(path[i]+" ");
            }*/
        }

        public static void dfs(int cur, int dis, int current1Len) {
        	System.out.println(current1Len);
            /**
             * 如果当前路径大于之前找到的最小值，可直接返回
             * */
            if (dis + current1Len*current1Len > min) {
                return;
            }
            /**
             * 判断是否达到最后一个结点，更新最小值，返回
             * */
            if(cur == n) {
            	dis+=current1Len*current1Len;
            	//System.out.println(current1Len);
                if (dis < min) {
                	int i=0;
                    path = new int[m];
                    numOfPath = 0;
                    for (Integer x : stack) {  
                    	path[i] = x;
                		i++;
                		numOfPath++;
                    }
                    min = dis;
                    return;
                }
            }
            /**
             * 当前点到其他各点之间可连通但是还未添加进来时，遍历执行
             * */
            for (int i = 1; i <= n; i++) {
                if (e[cur][i] != 99999999 && book[i] == 0) {
                    book[i] = 1;
                    stack.push(i);
                    //dfs(i, dis+e[cur][i]);
                    if(ec[cur][i] ==0) {
                    	dfs(i, dis+e[cur][i]+current1Len*current1Len, 0);
                    }else if(ec[cur][i]==1){
                    	current1Len+=e[cur][i];
                    	dfs(i, dis, current1Len);
                    }
                    
                    /**
                     * 回溯
                     **/
                    book[i] = 0;
                    stack.pop();
                }
            }
            return;
        }

}

