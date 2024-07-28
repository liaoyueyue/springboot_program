class Solution {
    public int[] twoSum(int[] nums, int target) {

    }
public static void main(String[] args) {
            Solution solution = new Solution();
            // testcase1
            int[] nums = {2, 7, 11, 15};
            int target = 9;
            int[] result = solution.twoSum(nums, target);
            if (result.length == 2 && result[0] == 0 && result[1] == 1) {
                System.out.println("testcase1 success");
            } else {
                System.out.println("testcase1 fail");
            }

            // testcase2
            nums = new int[]{3, 2, 4};
            target = 6;
            result = solution.twoSum(nums, target);
            if (result.length == 2 && result[0] == 1 && result[1] == 2) {
                System.out.println("testcase2 success");
            } else {
                System.out.println("testcase2 fail");
            }
        }
}