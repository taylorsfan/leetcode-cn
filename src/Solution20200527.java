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
     * 一看到“子数组和”，有必要马上想到“前缀和”
     * √ 复习，什么是“前缀和”？
     * 从数组 第 0 项 到 当前项 的 总和
     * 如果用一个数组 preSum 表示：
     * preSum[0]：数组A 第 0 项 到 第 0 项 的总和
     * preSum[1]：数组A 第 0 项 到 第 1 项 的总和
     * preSum[2]：数组A 第 0 项 到 第 2 项 的总和
     * preSum[3]：数组A 第 0 项 到 第 3 项 的总和
     * …… 于是有：
     * preSum[i] = A[0] + A[1] +…+A[i]
     * preSum[i]=A[0]+A[1]+…+A[i]
     * <p>
     * 数组某项，可以表示为相邻前缀和之差：
     * A[i] = preSum[i] - preSum[i - 1]
     * A[i]=preSum[i]−preSum[i−1]
     * <p>
     * 叠加多项，等号右边加减相消：
     * A[i] +…+A[j]=preSum[j] - preSum[i - 1]
     * A[i]+…+A[j]=preSum[j]−preSum[i−1]
     * <p>
     * i 当然可以为 0，此时 i - 1 为 - 1，我们故意让 preSum[-1]preSum[−1] 为 0，此时：
     * A[0] +A[1]+…+A[j]=preSum[j]
     * A[0]+A[1]+…+A[j]=preSum[j]
     * <p>
     * 这么做是为了让边界情况也能套用通式（通式也能成立）
     * 题目等价转化，目标更清晰
     * 子数组的元素之和，就是数组 第 i 项 到 第 j 项 的和
     * <p>
     * 元素之和能被 K 整除的子数组数目 就是 有几种 i、j 组合，使得 第 i ~ j 项的和 mod K == 0
     * <p>
     * ↓ ↓ ↓ 转化为 ↓ ↓ ↓
     * <p>
     * 有几种 i、ji、j 组合，满足 (preSum[ j ] - preSum[ i - 1 ])mod K== 0(preSum[j]−preSum[i−1])modK==0
     * <p>
     * 有几种 i、ji、j 组合，满足 pre[j] mod K == pre[i-1] mod Kpre[j]modK==pre[i−1]modK
     * <p>
     * 求出 preSum 数组的每一项，然后呢？
     * 前一项的前缀和，累加当前项，就是当前项的前缀和
     * 求出的 preSum 数组项 mod K，mod 完再看哪两项相等，统计计数
     * 但通式有 i、ji、j 两个变量，找出所有相等的两项，需要两层循环，母汤哦~
     * 抛弃 preSum 数组，引入哈希表
     * 因为我们不关心 前缀和 对应数组 A 的哪一项，即 不关心具体位置
     * 只关心出现过哪些 前缀和 % K 的结果，和对应的 出现次数
     * 改用一个变量 preSum ，保存每次求出的前缀和 mod K，存入哈希表
     * map 不含多余信息，存键值对：
     * key：前缀和 mod K 。数值作为 key
     * value：这个结果值出现了几次
     * 注意到， 前缀和 mod K 的值正好是 0,1,2...K0,1,2...K，恰似索引，也可以用数组存，代码会在后面给出~
     * 核心流程
     * 预置边界情况 (即 preSum[-1] = 0)：遍历数组 A 之前，map 提前放入 0:1，代表【前缀和 mod K 等于 0】已经出现 1 次
     * 遍历数组 A 的每一项，求当前项的前缀和 mod K ，存入 map 中
     * 之前没有存过，则作为 key 存入，值为 1
     * 之前存过，则对应值 +1
     * 边存边查看 map ，如果 map 中已存在 key 为 当前前缀和 mod K
     * 说明存在 之前求出的前缀和，它 mod K == 当前前缀和 mod K
     * 把 之前求出的前缀和 mod K 出现的次数，累加给 count
     * 一句话总结
     * 根据 当前前缀和 mod K，在 map 中寻找与之 相等 的 key 。满足条件的目标前缀和，可能出现不止 1 次，假设为 n 次，等价于，找到 n 个子数组的元素和能被 K 整除。遍历数组 A 每一项， n 不断累加给 count，最后返回 count
     * <p>
     * 时间复杂度、空间复杂度
     * Time：O(n)
     * Space：O(K)
     * mod 的结果最多 K 种，哈希表最多存放 K 个键值对
     * <p>
     * 作者：hyj8
     * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/solution/you-jian-qian-zhui-he-na-jiu-zai-ci-dai-ni-da-tong/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
