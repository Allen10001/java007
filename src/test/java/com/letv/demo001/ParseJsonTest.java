package com.letv.demo001;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.File;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-10 19:14
 **/
public class ParseJsonTest {
    public static void main(String[] args) throws Exception{
        JSONObject jsonObject = JSONObject.parseObject(FileUtil.readAsString(new File("/Users/allen/javaapp/demo001/src/main/resources/cloudatlas.config.json")));
        JSONArray array = jsonObject.getJSONArray("apps");

        for(Object item : array){

            JSONObject itemChild = (JSONObject)item;
            if(itemChild.containsKey("app_num")){
                int appNum = itemChild.getInteger("app_num");
                String startCmd = itemChild.getString("start_cmd");
                System.out.println(appNum);
            }else{
                System.out.println("=======");
            }

        }

    }
}
