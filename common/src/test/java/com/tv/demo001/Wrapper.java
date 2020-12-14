package com.tv.demo001;

public class Wrapper {
    public static void main(String[] args) {
        String item = "004|99999999999";
        String[] arr = item.split("\\|");
        if(arr.length != 2){
            System.err.println(item+" is not allowed，USAGE: CODE|TIMESTAMP");
        }
        String code = arr[0];
        Long timestamp = Long.parseLong(arr[1]);
        System.out.println(code+"|"+timestamp);
    }
}
