create database if not exists myoj default character set utf8mb4;
use myoj;

# 用户表
drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    username    varchar(20) not null unique,
    password    varchar(30) not null,
    nickname    varchar(50),
    email       varchar(50),
    phone       varchar(20),
    create_time timestamp default now()
);
insert into myoj.user(username, password, nickname, email)
values ('admin', '123', '管理员的昵称', 'admin@email.com');


# 题目表
drop table if exists problem;
create table problem
(
    id           int primary key auto_increment,
    title        varchar(100) not null,
    level        varchar(50),
    description  varchar(4090),
    template_code varchar(4090),
    test_code     varchar(4090),
    create_time  timestamp default now()
);
insert into myoj.problem (title, level, description, template_code, test_code)
values ('两数之和', '简单',
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
    }'),
       ('最大子序和', '简单',
        '给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。', 'class Solution {
    public int maxSubArray(int[] nums) {

    }
}', 'public static void main(String[] args) {
        Solution solution = new Solution();
        // testcase1
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expectedResult = 6;
        int result = solution.maxSubArray(nums);
        if (result == expectedResult) {
            System.out.println("testcase1 success");
        } else {
            System.out.println("testcase1 fail");
        }

        // testcase2
        nums = new int[]{1};
        expectedResult = 1;
        result = solution.maxSubArray(nums);
        if (result == expectedResult) {
            System.out.println("testcase2 success");
        } else {
            System.out.println("testcase2 fail");
        }
    }'),
       ('买卖股票的最佳时机', '简单',
        '给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。', 'class Solution {
    public int maxProfit(int[] prices) {

    }
}', 'public static void main(String[] args) {
        Solution solution = new Solution();
        // testcase1
        int[] prices = {7, 1, 5, 3, 6, 4};
        int expectedResult = 5;
        int result = solution.maxProfit(prices);
        if (result == expectedResult) {
            System.out.println("testcase1 success");
        } else {
            System.out.println("testcase1 fail");
        }

        // testcase2
        prices = new int[]{7, 6, 4, 3, 1};
        expectedResult = 0;
        result = solution.maxProfit(prices);
        if (result == expectedResult) {
            System.out.println("testcase2 success");
        } else {
            System.out.println("testcase2 fail");
        }
    }'),
       ('最小栈', '简单',
        '设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。', 'class MinStack {
    public MinStack() {

    }

    public void push(int val) {

    }

    public void pop() {

    }

    public int top() {

    }

    public int getMin() {

    }
}', 'public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        if (minStack.getMin() == -3) {
            System.out.println("testcase1 success");
        } else {
            System.out.println("testcase1 fail");
        }
        minStack.pop();
        if (minStack.top() == 0) {
            System.out.println("testcase2 success");
        } else {
            System.out.println("testcase2 fail");
        }
        if (minStack.getMin() == -2) {
            System.out.println("testcase3 success");
        } else {
            System.out.println("testcase3 fail");
        }
    }');

# 提交记录表
drop table if exists submission;
create table submission
(
    id              int auto_increment primary key,
    user_id         int,
    problem_id      int,
    submission_time timestamp default now(),
    language        varchar(50),
    code            text,
    status          enum ('pending', 'judging', 'accepted', 'wrong answer', 'runtime error', 'time limit exceeded', 'memory limit exceeded'), -- '待处理', '评判中', '已接受', '答案错误', '运行时错误', '超时', '内存超限'
    execution_time  int,  -- 执行时间
    memory_usage    int   -- 内存使用
);
insert into submission (user_id, problem_id, language, code, status, execution_time, memory_usage)
values (1, 1, 'Java', 'class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}', 'accepted', 5, 1024),
       (1, 2, 'Java', 'class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}', 'accepted', 10, 2048),
       (1, 3, 'Java', 'class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
}', 'accepted', 5, 1024);