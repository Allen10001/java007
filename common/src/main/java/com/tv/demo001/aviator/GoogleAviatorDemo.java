package com.tv.demo001.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 谷歌表达式引擎Aviator示例
 *
 *
 * Aviator——轻量级Java表达式求值引擎
 * https://developer.aliyun.com/article/608829
 *
 * @author hubo88
 * @description
 * @date 2023/1/28 11:49 AM
 */
public class GoogleAviatorDemo {

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.resolve();

//        Solution2 solution2 = new Solution2();
//        solution2.resolve();

//        Solution3 solution3 = new Solution3();
//        solution3.resolve();

//        Solution4 solution4 = new Solution4();
//        solution4.resolve();
    }
}

/**
 * 运算符
 */
class Solution1{

    public void resolve() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
//        Long result = (Long) AviatorEvaluator.execute("~7");
        System.out.println(result);
    }
}

/**
 * 内置函数
 */
class Solution2 {

    public void resolve() {
        String str = "使用Aviator";
        Map<String, Object> env = new HashMap<>();
        env.put("str", str);
        Long length = (Long) AviatorEvaluator.execute("string.length(str)", env);
        System.out.println(length);
    }
}

/**
 * compile 用法
 */
class Solution3 {

    public void resolve() {
        String expression = "a+(b-c)>100";
//        Expression compiledExp = AviatorEvaluator.compile(expression);
        /**
         * Aviator本身自带一个全局缓存
         * 如果决定缓存本次的编译结果，只需要
         */
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }
}

/**
 * 自定义函数的使用
 */
class Solution4 {

    public void resolve() {
        AviatorEvaluator.addFunction(new MinFunction());
        String expression = "min(a,b)";
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        Double result = (Double) compiledExp.execute(env);
        System.out.println(result);
    }

    static class MinFunction extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorBigInt(Math.min(left.doubleValue(), right.doubleValue()));
        }

        @Override
        public String getName() {
            return "min";
        }
    }
}

