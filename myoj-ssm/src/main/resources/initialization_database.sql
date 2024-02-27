create database if not exists myoj default character set utf8mb4;
use myoj;
drop table if exists problems;
create table problems
(
    id           int primary key auto_increment,
    title        varchar(50),
    level        varchar(50),
    description  varchar(4090),
    templateCode varchar(4090),
    testCode     varchar(4090)
);


INSERT INTO myoj.problems (id, title, level, description, templateCode, testCode)
VALUES (1, '两数之和', '简单',
        '给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。', 'class Solution {
    public int[] twoSum(int[] nums, int target) {

    }
}', 'public static void main(String[] args) {
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
    }');

