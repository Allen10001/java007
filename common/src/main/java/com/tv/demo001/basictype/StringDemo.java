package com.tv.demo001.basictype;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.checkerframework.checker.units.qual.C;

public class StringDemo {

    public static void main(String[] args) {
        String message = "\"2020/12/14-15:56:36.917\" \"ff0445322ac139d651eac56c650bd6e1\" \"C80E777D9C9C\" \"127692880\" \"tv\" \"rec_0617\" \"2_10050643 2_10051243 2_10050977 2_10033307 2_10050533 2_10048155 2_10050933 2_10050927 2_590361 2_10050849 2_10051157 2_10051001 2_10050835 2_10050565 2_10050029 2_10049967 2_10043397 2_10051277 2_10050597 2_10034201 2_10034086 2_10051383 2_10050969 2_10051287 2_10050587 2_10036236 2_10049953 2_10050353 2_10047953 2_10050851 2_10050045 2_10050137 2_10050997 2_10046781 2_10049945 2_10050503 2_10049643 2_10049489 2_10049639 2_10050209 2_10048333 2_10049429 2_10050201 2_10048571 2_10049741 2_10050979 2_10049997 2_10049873 2_314048 2_10049925 2_10048783 2_10049923 2_10049833 2_10047355 2_10049887 2_10047715 2_10049557 2_10048611 2_10050309 2_10036971\"\n" +
                "\"2020/12/14-15:56:37.319\" \"a2e30f722a24882d334375b950a1f0e7\" \"C80E777F823A\" \"291707179\" \"tv\" \"rec_0617\" \"2_10051287 2_10051349 2_10051283 2_10051289 2_10050801 2_10050567 2_10051159 2_10050807 2_10051271 2_10051295 2_590361 2_10048329 2_10050835 2_10047997 2_10048513 2_10050635 2_10033824 2_10051277 2_10050949 2_10049511 2_10049475 2_10041484 2_10051095 2_10050743 2_10044303 2_10034819 2_10050565 2_10049945 2_10050327 2_10051243 2_10050503 2_10049643 2_10049489 2_10049639 2_10050209 2_10048333 2_10049429 2_10050201 2_10048571 2_10049741 2_10051381 2_10050979 2_10049873 2_10049925 2_10048783 2_10049923 2_10049833 2_10047355 2_10048611 2_10050309 2_10036971 2_10048483 2_10048373 2_10050053 2_10001351 2_10048367 2_10049231 2_10048455 2_10036393 2_10048481\"\n" +
                "\"2020/12/14-15:56:37.667\" \"0a933b18b0e8a5e8610bc9ed23a91972\" \"C80E779128CD\" \"231609696\" \"tv\" \"rec_0623\" \"\"";

        String[] messageArr = message.split("\n");

        String[] itemArr = messageArr[0].split("\" \"");

        String rec_time_text = itemArr[0].replace("\"", "");
        System.out.println(rec_time_text);
        String reid = itemArr[1];
        System.out.println(reid);
        String lc = itemArr[2];
        System.out.println(lc);
        String uid = itemArr[3];
        System.out.println(uid);
        String pt = itemArr[4];
        System.out.println(pt);
        String area = itemArr[5];
        System.out.println(area);
        String res_result = itemArr[6].replace("\"", "");
        System.out.println(res_result);
    }


}

class Main001{

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "bbb");
        String res = map.get("a");
        System.out.println(res);
        map.remove("a");
        System.out.println(res);
    }
}

class Main002{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
//        if (in.hasNextLine()) { // 注意 while 处理多个 case
//            String line = in.nextLine();
//            System.out.println(Math.round(Float.valueOf(line)));
//        }
        char a = 'a';
        String b;
        while (in.hasNext()) {
            b = in.next();
            System.out.println(b);
        }

    }
}

 class Main003 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        Integer lineNum = 0;
        if (in.hasNextLine()) {
            lineNum = Integer.valueOf(in.nextLine());
        }
        Set<Integer> numSet = new HashSet<Integer>();
        for (int i=0; i<lineNum; i++) {
            if (in.hasNextLine()) {
                numSet.add(Integer.valueOf(in.nextLine()));
            }
        }
        // 倒序
        numSet.stream().sorted((o1, o2) ->{
            if (o1<o2) {
                return 1;
            } else if (o1>o2) {
                return -1;
            } else {
                return 0;
            }
        }).forEach(System.out::println);
    }
}

class Main004{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        Set<Integer> numSet = new HashSet<Integer>();
        for (int i=0; i<5; i++) {
            if (in.hasNextLine()) {
                numSet.add(Integer.valueOf(in.nextLine()));
            }
        }
        System.out.println(numSet.stream().reduce((o1, o2) -> {return o1+o2;}).get());
    }
}


class Main005{

    public static void main(String[] args) {
        String className = "aaa$$enhanceByGuice$$adf86";
        if (className.contains("$$")) {
            String[] nameArr = className.split("\\$\\$");
            if (nameArr.length > 0) {
                className = nameArr[0];
            }
        }
        System.out.println(className);
    }
}

class Main006{

    public static void main(String[] args) {
        String className = "a//b";

        String[] nameArr = className.split("/");
        if (nameArr.length > 0) {
            className = nameArr[0];
        }
        System.out.println(nameArr.length);
        Arrays.stream(nameArr).forEach(item->System.out.println(item));

    }
}



class Main007{

    public static void main(String[] args) {
        try {
            String str2 = "aa";
            LinkedList<Long> debugSkus = new LinkedList<>();
            CountDownLatch countDownLatch = new CountDownLatch(2);
            Thread thread1 = new Thread(()->{
                for (int i=0; i<5000; i++) {
                    debugSkus.add(1L);
                }
                countDownLatch.countDown();
            });
            Thread thread2 = new Thread(()->{
                for (int i=0; i<5000; i++) {
                    debugSkus.add(1L);
                }
                countDownLatch.countDown();
            });
            thread1.start();
            thread2.start();
            countDownLatch.await();
            System.out.println(str2+":"+debugSkus.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Main008{

    public static void main(String[] args) {
        String className = "a.b";

        String[] nameArr = className.split("\\.");
        if (nameArr.length > 0) {
            className = nameArr[0];
        }
        System.out.println(nameArr.length);
        Arrays.stream(nameArr).forEach(item->System.out.println(item));

    }
}

class Main009{

    public static void main(String[] args) {
        String key = "__gray.newUserColdStartRecaller.StandardIndexRecallerProcessor.lastLimit";
        final String MINOR_CLUSTER_PARAM_PREFIX = "__gray.";

        if (key.startsWith(MINOR_CLUSTER_PARAM_PREFIX)) {
            System.out.println("TRUE");
        }

        System.out.println(key.substring(MINOR_CLUSTER_PARAM_PREFIX.length()));
    }
}


