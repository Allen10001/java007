package com.tv.demo001.serialize;

import com.google.gson.JsonObject;
import com.tv.demo001.utils.ProtoUtils;

/**
 * @author hubo88
 * @description
 * @date 2022/12/21 2:18 PM
 */
public class ProtoStuffDemo {

    public static void main(String[] args) {
        JsonObject json = new JsonObject();
        json.addProperty("aa", "bb");
        byte[] res = ProtoUtils.protoStuffSerialize(json);
        System.out.println(res);

        JsonObject jsonDes = ProtoUtils.protoStuffDeserialize(res, JsonObject.class);

        System.out.println(jsonDes.toString());
    }

}
