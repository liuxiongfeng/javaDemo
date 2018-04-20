package quartz;

import java.util.*;
public class Main{
    public static void main(String[] args){
        System.out.println(hammingDistance(1,4));
    }
    public static int hammingDistance(int x, int y) {
        int m = 0;
        for(int i = 0;i<31;i++){
            int n = 1 << i;
            int v = x&n;
            if(((x&n) != 0 && (y&n) == 0) || ((x & n) == 0 && (y & n )!= 0)){
                m++;
            }
        }
        return m;
    }

    //最长公共字串
    public static String lcs(String x,String y) {
        int substringLength1 = x.length();
        int substringLength2 = y.length();
        int opt[][]=new int [substringLength1+1][substringLength2+1];
        for(int i=1;i<=substringLength1;i++)
            for(int j=1;j<=substringLength2;j++)
            {
                if(x.charAt(i-1)==y.charAt(j-1))
                    opt[i][j]=opt[i-1][j-1]+1;//状态转移方程，
            }
        int max=opt[0][0];
        int m=0,n=0;
        for(int i=substringLength1;i>=1;i--)
            for(int j=substringLength2;j>=1;j--){
                if(opt[i][j]>max)
                {
                    max=opt[i][j];
                    m=i;
                    n=j;
                }
            }
        System.out.println("x = "+x);
        System.out.println("y = "+y);
        System.out.println("max = "+max);
        StringBuffer sb=new StringBuffer();
        for(int i=m,j=n;i>=1&&j>=1;i--,j--)
        {
            if(opt[i][j]>0)
                sb.append(x.charAt(i-1));
        }
        StringBuffer result = new StringBuffer();
        for (int i = sb.length()-1; i >=0 ; i--) {
            result.append(sb.charAt(i));
        }
        return result.toString();
    }
    //最长公共子序列
    public static int fun(String s1,String s2){
        char[] s11 = s1.toCharArray();
        char[] s22 = s2.toCharArray();
        int m = s1.length();
        int n = s2.length();
        int[][] c = new int[m+1][n+1];
        for (int i = 1 ; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(s11[i-1] == s22[j-1]){
                    c[i][j] = c[i-1][j-1] + 1;
                } else {
                    c[i][j] = Math.max(c[i][j-1],c[i-1][j]);
                }
            }
        }
        return c[m][n];
    }
}