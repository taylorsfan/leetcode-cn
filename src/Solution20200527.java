/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution20200527 {

    /**
     * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/solution/you-jian-qian-zhui-he-na-jiu-zai-ci-dai-ni-da-tong/
     */
    public int subarraysDivByK(int[] A, int K) {
        int[] map = new int[K];
        ++map[0];
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (a + prefix) % K;
            if (prefix < 0) prefix += K;
            res += map[prefix]++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution20200527 solution20200527 = new Solution20200527();
        System.out.println(solution20200527.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }
}
