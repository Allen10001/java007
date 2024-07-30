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
        regexDemo002.solution();
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
}
