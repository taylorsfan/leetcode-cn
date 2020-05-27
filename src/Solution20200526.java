import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution20200526 {

    /**
     * 利用hashSet的去重性去判断，能直接AC
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return 0;
    }

    /**
     * https://leetcode-cn.com/problems/find-the-duplicate-number/comments/
     * 其一，对于链表问题，使用快慢指针可以判断是否有环。
     * 其二，本题可以使用数组配合下标，抽象成链表问题。但是难点是要定位环的入口位置。
     * 举个例子：nums = [2,5,9,6,9,3,8,9,7,1]，构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，也就是在[9]处循环。
     * 其三，快慢指针问题，会在环内的[9]->1->5->3->6->8->7->[9]任何一个节点追上，不一定是在[9]处相碰，事实上会在7处碰上。
     * 其四，必须另起一个for循环定位环入口位置[9]。这里需要数学证明。
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
                //判断存在环
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        Solution20200526 solution20200526 = new Solution20200526();
        System.out.println(solution20200526.findDuplicate1(nums));
    }
}
