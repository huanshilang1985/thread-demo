package com.zh.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 这个类回报异常：java.util.ConcurrentModificationException
 * 表示在集合类遍历过程中，不允许更新操作（增加，修改，删除）
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        for(int i= 0; i<20; i++){
            User user = new User(i, "User"+ i);
            list.add(user);
        }

        Iterator<User> it = list.iterator();
        while (it.hasNext()){
            User user = it.next();
            if("User6".equals(user.getName())){
                list.remove(user);
            }
        }
    }

}
