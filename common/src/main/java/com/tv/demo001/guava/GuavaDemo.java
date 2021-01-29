package com.tv.demo001.guava;


import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.*;
import com.tv.demo001.bean.Person;
import com.google.common.hash.*;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GuavaDemo {

    public static void main(String[] args) throws Exception {

        Optional<Integer> possible = Optional.of(5);

        possible.isPresent();

        Optional.fromNullable(null);

        // 1.2-前置条件
        Preconditions.checkArgument(1<2 , " 1<2 ", "error");

        Objects.hashCode(new String());

        ImmutableSet.of("a","e","d","b","c");

        ImmutableSortedSet.of("a","e","d","b","c");

        Funnel<Person> personFunnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
                into
                        .putInt(person.getId())
                        .putString(person.getFirstName(), Charsets.UTF_8)
                        .putString(person.getLastName(), Charsets.UTF_8)
                        .putInt(person.getBirthYear());
            }
        };

        HashFunction hfMd5 = Hashing.md5();
        HashFunction hfSha1 = Hashing.sha1();
        HashFunction hfSha256 = Hashing.sha256();
        HashFunction hfSha512 = Hashing.sha512();
        HashFunction hfMurmur3_32 = Hashing.murmur3_32();

        HashCode hc = hfMd5.newHasher()
                .putLong(1000L)
                .putString("hello world", Charsets.UTF_8)
                .putObject(new Person(12345, "Billy", "Hu", 1992), personFunnel)
                .hash();

        System.out.println("hash: "+hc.toString());

        /**
         * 布隆过滤器： 简而言之，布鲁姆过滤器是一种概率数据结构，它允许你检测某个对象是一定不在过滤器中，
         * 还是可能已经添加到过滤器了。
         */
        Person allen = new Person(601, "allen", "zhang", 1999);
        Person billy = new Person(602, "billy", "wu", 2000);
        Person siri = new Person(602, "siri", "li", 2000);
        Person[] friendsArr = {allen, billy, siri};
        List<Person> friends = Arrays.asList(friendsArr);
        BloomFilter<Person> friendsFilter = BloomFilter.create(personFunnel, 500, 0.01);
        friends.forEach(friend -> friendsFilter.put(friend));

        if(friendsFilter.mightContain(siri)){
            System.out.println("might contain \"siri li\"");
        }


        /**
         * 连接器
         */
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, null, "Ron", "Hermione"));

        System.out.println(Joiner.on("|").useForNull("").join(Arrays.asList("a", null,"b", "c")));

        /**
         * 分离器
         */
        String toBeSplitedStr = ",a,\"\",b,";
        Iterator<String> strIterator = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(",a,\"\",,b,").iterator();

        System.out.println("------------------");
        while(strIterator.hasNext()){
            System.out.println(strIterator.next());
        }
        System.out.println("------------------");
        String[] splitArr = toBeSplitedStr.split(",");
        Arrays.asList(splitArr).forEach(item -> {
            System.out.println(item);
        });
        System.out.println("------------------");

        /**
         * 9-I/O, 字节流和字符流
         */
       /*InputStream is = System.in;
        byte[] inputbyte = ByteStreams.toByteArray(is);
        System.out.println(new String(inputbyte));
*/
        //Read the lines of a UTF-8 text file
        File file = new File("/Users/allen/javaapp/demo001/src/main/resources/.gitignore");
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();

        //Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));

        //SHA-1 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        System.out.println(hash.hashCode());

        //Copy the data from a URL to a file
        Resources.asByteSource(new URL("https://www.baidu.com")).copyTo(Files.asByteSink(file));

    }


    public void testFunction(){

        new GuavaDemo().hashCode();

    }


}

