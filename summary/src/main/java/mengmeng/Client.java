package mengmeng;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/5 10:50
 */
public class Client {


    public static void main(String[] args) throws CloneNotSupportedException {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("protoType-context.xml");
//
//        Penson peysen = applicationContext.getBean("peysen", Penson.class);
//        System.out.println("peysen:" + peysen.toString());
//        System.out.println("peysen:" + peysen.getAddress().hashCode());
//
//        Penson peysen3 = applicationContext.getBean("peysen", Penson.class);
//        System.out.println("peysen3:" + peysen3.toString());
//        System.out.println("peysen3:" + peysen3.getAddress().hashCode());

        String name = "123";
        Address address = new Address();
        address.setCityName("beijing");
        Set<Integer> hashCodes = new HashSet<>();
        Map<Integer, Penson> pensonMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            Penson penson = new Penson(name, address);
            int hashCode = penson.hashCode();
//            System.out.println(hashCode);
            if (hashCodes.contains(Integer.valueOf(hashCode))) {
                count++;
                for (Integer h: hashCodes) {
                    if (h == hashCode) {
                        System.out.println("=============================="+ i);
                        System.out.println("哈希值" + hashCode + " ： " + h);
                        System.out.println("对象地址：" + pensonMap.get(h) + " : " + penson);
                        System.out.println(pensonMap.get(h) == penson);
                    }
                }
            } else {
                System.out.println(i +": " + penson + " : " + penson.hashCode());
                hashCodes.add(hashCode);
                pensonMap.put(hashCode, penson);
            }
        }

        System.out.println("一百万数据冲突:" + count);
        System.out.println(hashCodes.size());


//        Penson peysen1 = new Penson();
//        peysen1.setName("123");
//        Address address = new Address();
//        address.setCityName("beijing");
//        peysen1.setAddress(address);
//
//        Penson peysen3 = new Penson();
//        peysen3.setName(peysen1.getName());
//        peysen3.setAddress(peysen1.getAddress());
//
//        System.out.println("peysen1:" + peysen1.toString());
//        System.out.println("peysen1:" + peysen1.hashCode());
////        System.out.println("peysen1:" + peysen1.getAddress().hashCode());
//
//        System.out.println("peysen3:" + peysen3.toString());
//        System.out.println("peysen3:" + peysen3.hashCode());
////        System.out.println("peysen3:" + peysen3.getAddress().hashCode());
//
//        Penson peysen2 = (Penson) peysen1.clone();
//        System.out.println("peysen2:" + peysen2.toString());
//        System.out.println("peysen2:" + peysen2.hashCode());
////        System.out.println("peysen2:" + peysen2.getAddress().hashCode());
//
//        System.out.println(peysen1 == peysen2);
//        System.out.println(peysen1 == peysen3);

//        System.out.println(peysen1.getAddress() == peysen2.getAddress());
//        System.out.println(peysen1.getAddress() == peysen3.getAddress());


//        int total = 1000000;
//        Set set = new HashSet<>(1000000);
//        AtomicInteger repeatCount = new AtomicInteger(0);
//        List objectList = new ArrayList<>();
//        IntStream.range(0, total).forEach(i -> {
//            Object obj = new Object();
//            objectList.add(obj);
//            int hashCode = obj.hashCode();
//            if (set.contains(hashCode)) {
//                System.out.println(hashCode);
//                repeatCount.incrementAndGet();
//            } else {
//                set.add(hashCode);
//            }
//        });
//        System.out.println("repeatCount = " + repeatCount.get() + ", total = " + total);
//
//        System.out.println("repeatRate = " + (BigDecimal.valueOf(repeatCount.get())).divide(BigDecimal.valueOf(objectList.size()), 6, RoundingMode.HALF_UP).doubleValue());

    }
}
