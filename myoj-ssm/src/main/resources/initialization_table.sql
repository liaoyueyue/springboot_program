use myoj;

# 用户表数据
insert into myoj.user(username, password, nickname, email)
values ('admin', '123456', '管理员的昵称', 'admin@email.com');

# 题目合集数据
insert into collection (name, description)
values ('Java基础题目', 'Java编程语言的基础知识练习题'),
       ('Java面向对象', 'Java面向对象编程相关的题目'),
       ('Java集合框架', 'Java集合框架相关的题目'),
       ('Java异常处理', 'Java异常处理机制的练习题'),
       ('Java多线程', 'Java多线程编程相关的题目'),
       ('JavaIO编程', 'Java输入输出流编程相关的题目'),
       ('Java网络编程', 'Java网络编程相关的题目'),
       ('Java数据库连接', 'Java数据库连接相关的题目'),
       ('JavaGUI编程', 'Java图形用户界面编程相关的题目'),
       ('JavaWeb开发', 'JavaWeb开发相关的题目');

# 题目表数据
insert into problem (title, level, collection_id, description, template_code, test_code)
values ('两数之和', '简单', 1,
        '给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。',
        'class Solution {
            public int[] twoSum(int[] nums, int target) {

            }
        }',
        'public static void main(String[] args) {
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
       ('最大子序和', '简单', 1,
        '给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。',
        'class Solution {
            public int maxSubArray(int[] nums) {

            }
        }',
        'public static void main(String[] args) {
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
       ('猫和狗的继承关系', '中等', 2,
        '使用 Java 编程实现猫和狗的继承关系。具体要求：猫和狗都是动物，它们都有姓名和年龄属性，并且都可以发出叫声。同时，猫还有特有的抓老鼠行为，而狗有特有的看门行为。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建猫实例
                Cat cat = new Cat("Tom", 3);
                cat.makeSound();
                cat.catchMouse();
                // 创建狗实例
                Dog dog = new Dog("Buddy", 5);
                dog.makeSound();
                dog.watchHouse();
            }
        }'),
       ('图形形状的抽象类设计', '中等', 2,
        '设计一个抽象类 Shape，表示图形形状。具体要求：Shape 包含一个抽象方法 area()，用于计算图形的面积。同时，设计三个子类 Circle、Rectangle 和 Triangle，分别表示圆、矩形和三角形，实现 area() 方法以计算各自图形的面积。请编写程序测试你的设计。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建圆形实例
                Circle circle = new Circle(5);
                System.out.println("圆形的面积：" + circle.area());
                // 创建矩形实例
                Rectangle rectangle = new Rectangle(4, 6);
                System.out.println("矩形的面积：" + rectangle.area());
                // 创建三角形实例
                Triangle triangle = new Triangle(3, 4, 5);
                System.out.println("三角形的面积：" + triangle.area());
            }
        }'),
       ('学生管理系统', '中等', 3,
        '使用 Java 编程实现一个简单的学生管理系统。具体要求：设计一个 Student 类表示学生，包含学生的姓名、年龄和成绩等信息；设计一个 StudentManager 类用于管理学生信息，实现添加学生、删除学生、按姓名查找学生和按成绩排序等功能。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建学生管理系统实例
                StudentManager studentManager = new StudentManager();
                // 添加学生
                studentManager.addStudent(new Student("Alice", 20, 85));
                studentManager.addStudent(new Student("Bob", 21, 78));
                studentManager.addStudent(new Student("Charlie", 19, 92));
                // 按姓名查找学生
                Student foundByName = studentManager.findStudentByName("Bob");
                System.out.println("按姓名查找学生：" + foundByName);
                // 按成绩排序
                studentManager.sortStudentsByScore();
                System.out.println("按成绩排序后的学生列表：" + studentManager.getStudents());
            }
        }'),
       ('简单计算器', '中等', 3,
        '使用 Java 编程实现一个简单的计算器。具体要求：设计一个 Calculator 类实现加法、减法、乘法和除法等基本运算功能。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建计算器实例
                Calculator calculator = new Calculator();
                // 加法测试
                System.out.println("加法测试：" + calculator.add(5, 3));
                // 减法测试
                System.out.println("减法测试：" + calculator.subtract(5, 3));
                // 乘法测试
                System.out.println("乘法测试：" + calculator.multiply(5, 3));
                // 除法测试
                System.out.println("除法测试：" + calculator.divide(10, 2));
            }
        }'),
       ('除零异常处理', '中等', 4,
        '使用 Java 编程实现一个简单的除法计算器，要求能够处理除零异常。具体要求：设计一个 Calculator 类实现除法运算功能，当除数为零时，抛出自定义的除零异常 ZeroDivisionException，并在主程序中进行异常处理。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建计算器实例
                Calculator calculator = new Calculator();
                // 普通除法测试
                try {
                    double result = calculator.divide(10, 2);
                    System.out.println("普通除法测试：" + result);
                } catch (ZeroDivisionException e) {
                    System.out.println("除零异常：" + e.getMessage());
                }
                // 除零测试
                try {
                    double result = calculator.divide(10, 0);
                    System.out.println("除零测试：" + result);
                } catch (ZeroDivisionException e) {
                    System.out.println("除零异常：" + e.getMessage());
                }
            }
        }'),
       ('文件读取异常处理', '中等', 4,
        '使用 Java 编程实现一个简单的文件读取器，要求能够处理文件不存在异常。具体要求：设计一个 FileReader 类实现从文件中读取内容的功能，当文件不存在时，抛出自定义的文件不存在异常 FileNotFoundException，并在主程序中进行异常处理。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建文件读取器实例
                FileReader fileReader = new FileReader();
                // 存在文件测试
                try {
                    String content = fileReader.readFile("example.txt");
                    System.out.println("存在文件测试：" + content);
                } catch (FileNotFoundException e) {
                    System.out.println("文件不存在异常：" + e.getMessage());
                }
                // 不存在文件测试
                try {
                    String content = fileReader.readFile("nonexistent.txt");
                    System.out.println("不存在文件测试：" + content);
                } catch (FileNotFoundException e) {
                    System.out.println("文件不存在异常：" + e.getMessage());
                }
            }
        }'),
       ('线程创建与启动', '中等', 5,
        '使用 Java 编程实现一个简单的多线程应用，要求实现线程的创建和启动。具体要求：设计一个 MyThread 类继承自 Thread 类，重写 run() 方法，在该方法中实现线程要执行的任务；在主程序中创建 MyThread 的实例并启动线程。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建 MyThread 实例并启动线程
                MyThread thread = new MyThread();
                thread.start();
            }
        }'),
       ('线程同步与通信', '中等', 5,
        '使用 Java 编程实现一个简单的生产者-消费者模型，要求实现线程间的同步与通信。具体要求：设计一个 Producer 类和一个 Consumer 类，分别表示生产者和消费者，它们共享一个队列作为缓冲区；在主程序中创建多个生产者和消费者实例，并通过线程间的同步与通信实现生产者向队列中添加元素，消费者从队列中取出元素。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 创建共享队列
                Queue<Integer> queue = new LinkedList<>();
                // 创建生产者和消费者实例
                Producer producer = new Producer(queue);
                Consumer consumer = new Consumer(queue);
                // 启动生产者和消费者线程
                producer.start();
                consumer.start();
            }
        }'),
       ('文件复制', '中等', 6,
        '使用 Java 编程实现一个简单的文件复制程序。具体要求：设计一个 FileCopy 类，包含一个静态方法 copyFile，接受源文件路径和目标文件路径作为参数，实现将源文件复制到目标文件的功能。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 源文件路径和目标文件路径
                String sourcePath = "source.txt";
                String targetPath = "target.txt";
                // 执行文件复制
                FileCopy.copyFile(sourcePath, targetPath);
                System.out.println("文件复制完成");
            }
        }'),
       ('文件压缩与解压', '中等', 6,
        '使用 Java 编程实现一个简单的文件压缩与解压程序。具体要求：设计一个 FileCompressor 类，包含两个静态方法 compress 和 decompress，分别实现文件的压缩和解压功能。请根据以上描述设计类，并编写程序进行测试。',
        '// 请在此处编写你的 Java 代码',
        'public class Main {
            public static void main(String[] args) {
                // 源文件路径和压缩文件路径
                String sourcePath = "source.txt";
                String compressedPath = "compressed.gz";
                // 执行文件压缩
                FileCompressor.compress(sourcePath, compressedPath);
                System.out.println("文件压缩完成");
                // 解压文件
                String decompressedPath = "decompressed.txt";
                FileCompressor.decompress(compressedPath, decompressedPath);
                System.out.println("文件解压完成");
            }
        }');

# 提交记录表数据
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
