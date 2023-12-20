package com.tv.demo001.zztemp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hubo88
 * @description
 * @date 2023/12/16 4:49 PM
 */
public class Solution001 {

    public static void main(String[] args) {

    }

    List<String> res = new ArrayList();
    public List<String> restoreIpAddresses(String s) {
        char[] sArr = s.toCharArray();
        List<String> path = new ArrayList<>();
        trackback(sArr, 0, path);
        return res;
    }

    private void trackback(char[] sArr, int index, List<String> path) {
        if (path.size() == 4 && index == sArr.length) {
            res.add(parsePath2Str(path));
            return;
        }
        if (index==sArr.length) {
            return;
        }

        if ('0' == sArr[index]) {
            path.add("0");
            trackback(sArr, index+1, path);
            path.remove(path.size()-1);
        } else {
            int num = 0;
            for (int i=index; i<sArr.length; i++) {
                num = num*10 + sArr[i]-'0';
                if (num > 0 && num < 256) {
                    path.add(String.valueOf(num));
                    trackback(sArr, i+1, path);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    private String parsePath2Str(List<String> path) {
        return String.join(".", path);
    }
}
