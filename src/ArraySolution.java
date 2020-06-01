import java.util.Arrays;

public class ArraySolution {

    /**
     * 删除排序树组中的重复项
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/21/
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (!(nums[j] == nums[i])) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 买卖股票的最佳时机 II
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/22/
     */
    public static int maxProfit(int[] prices) {
        //计算每天利润
        int[] money = new int[prices.length];
        //默认第一天没钱
        money[0] = 0;
        for (int i = 1; i < money.length; i++) {
            money[i] = prices[i] - prices[i - 1];
        }
        int max = 0;
        int sum = 0;
        for (int value : money) {
            sum += value;
            if (sum > max) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }

    /**
     * 旋转数组
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/23/
     */
    public static int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        int len = nums.length;
        int[] newNums = new int[len+k];
        for (int i = 0; i < newNums.length; i++) {
            if (i < k) newNums[i] = nums[len-k + i];
            if (i>=k)newNums[i] = nums[i-k];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newNums[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(rotate(nums, 3)));
    }
}
