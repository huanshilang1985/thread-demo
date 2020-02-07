package com.zh.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 航班查询例子
 * 使用CountDownLatch分为3个线程分开执行，再汇总输出
 */
public class FightQueryDemo {

    //航空公司
    private static List<String> company = Arrays.asList("东方航空", "南方航空", "海南航空");
    //航线
    private static List<String> fightLIst = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        String origin = "Beijing"; //出发地
        String dest = "Shanghai";  //目的地

        Thread[] threads = new Thread[company.size()];
        CountDownLatch latch = new CountDownLatch(company.size());
        for (int i = 0; i < threads.length; i++) {
            String name = company.get(i);
            threads[i] = new Thread(() -> {
                System.out.printf("%s 查询从%s到%s的机票 \n", name, origin, dest);
                // 随机产生票数
                int val = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    fightLIst.add(name + "--" + val);
                    System.out.printf("%s公司查询成功！ \n", name);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        //唤醒主线程
        latch.await();
        System.out.println("======查询结果如下=====");
        fightLIst.forEach(System.out::println);
    }


}
