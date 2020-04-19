package com.flow.snow.snow.learn;

import java.util.*;

public class ArrLearn {


    public static void arrRemoveDuplication(List<String> list) {
        // 1.利用set集合覆盖重复元素规则去掉重复元素
        System.out.println("_____________1.利用set集合覆盖重复元素规则去掉重复元素______________");
        Set<String> set = new HashSet<>();
        list.forEach(item -> {
            set.add(item);
        });
        List<String> res = new ArrayList<>();
        res.addAll(set);
        res.forEach(item -> {
            System.out.println(item);
        });

        // 2.利用jdk1.8新特性
        System.out.println("_____________2.利用jdk1.8新特性______________");
        list.stream().distinct().forEach(item -> {
            System.out.println(item);
        });
    }

    public static void listSort() {
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("bob", 100));
        dogList.add(new Dog("woody", 100));
        dogList.add(new Dog("ali", 50));
        dogList.add(new Dog("tom", 100));

        dogList.stream().sorted(Comparator
                .comparing(Dog::getPrice).reversed() // 按照价格降序排序
                .thenComparing(Comparator.comparing(Dog::getName))) // 按照名字生序排序
                .forEach(System.out::println);
    }

    public static void mapProperties() {
//        Map<String, String> map = new HashMap<>();  // hashMap 基于hash表无序
//        Map<String, String> map = new LinkedHashMap<>(); // linkedHashMap 基于链表有序存储
        Map<String, String> map = new TreeMap<>(); // treeMap 自动排序 底层是红黑树

        map.put("bob","100");
        map.put("woody","100");
        map.put("ali","50");
        map.put("tom","100");

        map.forEach((String key, String value)->{
            System.out.println(key + "_" + value);
        });


    }

    public static void removeList() {
        List<String> list  = new ArrayList<>();
        String[] arr = new String[]{"1","2","2","3","3"};
        list = new ArrayList<>(Arrays.asList(arr));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if ("2".equals(str) || "3".equals(str)){
                iterator.remove();
            }
        }
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("4");
        ArrLearn.arrRemoveDuplication(list);
        ArrLearn.listSort();
        ArrLearn.mapProperties();
        // 面试题 arrylist remove方法能否删除object 答案是 能
        List<Object> test = new ArrayList<>();
        test.add(new Dog("tom", 100));
        test.add(new Dog("tom", 200));
        test.remove(new Dog("tom", 100));
        test.forEach(System.out::println);
        System.out.println("-------删除arrylist元素-------");
        ArrLearn.removeList();
    }
}
