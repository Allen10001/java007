package com.tv.demo001.regex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hubo88
 * @description
 * @date 2024/7/30 5:06 PM
 */
public class RegexDemo002 {

    public static void main(String[] args) {
        RegexDemo002 regexDemo002 = new RegexDemo002();
        regexDemo002.solution2();
    }

    public void solution() {
        Set<String> conditionSet = new HashSet<String>(){{
            add("\"CONDITION#hint.has('ci')&&hint.get('ci').getAsInt()==5#.aggMaterialInfoFiller.MaterialResultFillProcessor.materialConfigMap\"");
        }};

        Pattern conPattern = Pattern.compile("(?<=\\[)(\\S+)(?=\\])");
        Pattern touchStoneConPattern = Pattern.compile("(?<=\\#)(\\S+)(?=\\#)");



        conditionSet.forEach(condition -> {
            boolean hitCondition = false;

            Matcher mat = conPattern.matcher(condition);
            String conditionStr = null;
            while (mat.find()) {
                hitCondition = true;
                System.out.println(hitCondition);
                conditionStr = mat.group();
                System.out.println(conditionStr);
            }
            if (!hitCondition) {
                mat = touchStoneConPattern.matcher(condition);
                while (mat.find()) {
                    conditionStr = mat.group();
                    conditionStr = conditionStr.replace('$', ',');
                    System.out.println("conditionStr:"+conditionStr);
                }
            }
        });
    }

    public void solution2() {
        String ipStr = "__ip127.3.33.1__";
        Pattern ipStrPattern = Pattern.compile("(?<=__ip)(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(?=__)");
        Matcher mat = ipStrPattern.matcher(ipStr);
        String ip = "";
        while (mat.find()) {
            System.out.println(mat.group());
            ip = mat.group(1);
        }
        System.out.println("__ip"+ip+"__");

    }
}
