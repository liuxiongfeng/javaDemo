package quartz;

import java.util.*;
class Main1 {
    public  static  int count = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//输入一个字符串
        String str = scan.nextLine();//字符串赋给str  
        permutation(str.toCharArray(), 0,str.length()-1);//调用permutation方法
        System.out.println(count);

    }
    public static void permutation(char[] str, int start,int end) {
        if (start > end || str == null) {//边界值判断  
            return;
        }
        if (start == end) {//递归到最后一位时打印出字符数组  
            System.out.println(String.valueOf(str) + " ");//将数组转换成字符串输出
            count++;
        } else {
            for (int i = start; i <= end; i++) {//遍历数组  
                if (isSwap(str, start, i)) {//判断是否重复  
                    char temp = str[start];
                    str[start] = str[i];
                    str[i] = temp;
                    permutation(str, start + 1,end);
                    temp = str[start];
                    str[start] = str[i];
                    str[i] = temp;
                }
            }
        }
    }
    public static boolean isSwap(char[] str, int start, int i) {//定义方法，str[i]在之前是否出现过  
        for (int k = start; k < i; k++) {
            if (str[k] == str[i]) {
                return false;
            }
        }
        return true;
    }
}