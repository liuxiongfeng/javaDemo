package quartz;

import java.util.ArrayList;
import java.util.List;

public class AllCombination {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        subsets(nums);
    }


    public  static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> lll = new ArrayList<>();
            int n = nums.length;
            int pn = 1<<n;
            for (int i = 0;i< pn;i++){
                System.out.print("输出第"+i+"个是：");
                ArrayList<Integer> ll = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    int temp = 1<<j;
                    if ((temp & i) != 0){
                        ll.add(nums[j]);
                    }
                }
                System.out.println(ll);
                lll.add(ll);
            }
            return lll;

        }
    }


