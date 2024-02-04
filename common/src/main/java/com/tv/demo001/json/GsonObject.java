package com.tv.demo001.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.lang.reflect.Modifier;

/**
 * @author hubo88
 * @description
 * @date 2022/12/23 3:20 PM
 */
public class GsonObject {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        JsonObject jsonObject = gson.fromJson("{\n"
            + "  \"config\": [\n"
            + "    {\n"
            + "      \"resource\": {\n"
            + "        \"system_id\": \"lite\",\n"
            + "        \"res_id\": \"r_2211231841_5jF\",\n"
            + "        \"data_size\": 100000,\n"
            + "        \"operator_erp\": \"hubo88\",\n"
            + "        \"owner\": \"org.xnrec.jrec1\",\n"
            + "        \"time_to_live\": \"0s\",\n"
            + "        \"description\": \"paas_sku_demo_data_indexpaas_sku_demo_data_index\\n\",\n"
            + "        \"source\": [\n"
            + "          {\n"
            + "            \"type\": \"JMQ4\",\n"
            + "            \"topic\": \"sku_topic001\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"type\": \"HIVE\",\n"
            + "            \"hive_table\": \"tmpr.paas_sku_demo_data\",\n"
            + "            \"dependence\": \"\",\n"
            + "            \"refresh_interval\": \"47h\",\n"
            + "            \"partition_format\": \"yyyy-MM-dd\"\n"
            + "          }\n"
            + "        ],\n"
            + "        \"tierInfo\": [\n"
            + "          {\n"
            + "            \"tier_name\": \"did\",\n"
            + "            \"data_size\": 1,\n"
            + "            \"attr_info\": {\n"
            + "              \"attr_type\": {}\n"
            + "            }\n"
            + "          },\n"
            + "          {\n"
            + "            \"tier_name\": \"sku\",\n"
            + "            \"data_size\": 100000,\n"
            + "            \"attr_info\": {\n"
            + "              \"attr_type\": {\n"
            + "                \"spu\": \"INT64\",\n"
            + "                \"name\": \"STRING\",\n"
            + "                \"brand_id\": \"INT32\",\n"
            + "                \"cid3\": \"INT32\",\n"
            + "                \"cid3name\": \"STRING\",\n"
            + "                \"cid2\": \"INT32\",\n"
            + "                \"cid2name\": \"STRING\",\n"
            + "                \"cid1\": \"INT32\",\n"
            + "                \"cid1name\": \"STRING\",\n"
            + "                \"wyn\": \"INT32\",\n"
            + "                \"wstate\": \"INT32\",\n"
            + "                \"shop_id\": \"INT32\",\n"
            + "                \"shop_name\": \"STRING\",\n"
            + "                \"cold_flag\": \"INT32\"\n"
            + "              }\n"
            + "            }\n"
            + "          }\n"
            + "        ]\n"
            + "      },\n"
            + "      \"datasource\": {\n"
            + "        \"name\": \"paas_sku_demo_data_index\",\n"
            + "        \"description\": \"paas_sku_demo_data_index\",\n"
            + "        \"slice_num\": 8,\n"
            + "        \"operator_erp\": \"hubo88\",\n"
            + "        \"owner\": \"org.xnrec.jrec1\",\n"
            + "        \"site\": \"CN\",\n"
            + "        \"refresh_interval\": \"47h\",\n"
            + "        \"resource_pool\": \"REALTIME\",\n"
            + "        \"data_type\": \"DARAB\",\n"
            + "        \"upper_limit\": 100000,\n"
            + "        \"resources\": {\n"
            + "          \"reference\": [\n"
            + "            {\n"
            + "              \"alias\": \"lite\",\n"
            + "              \"system_id\": \"lite\",\n"
            + "              \"res_id\": \"r_2211231841_5jF\",\n"
            + "              \"type\": \"resource\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"build_key\": [\n"
            + "            \"did.id@lite\",\n"
            + "            \"sku.id@lite\"\n"
            + "          ],\n"
            + "          \"join_strategy\": {}\n"
            + "        },\n"
            + "        \"fields\": [\n"
            + "          {\n"
            + "            \"name\": \"did\",\n"
            + "            \"source_value\": [\n"
            + "              \"did.id@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT64\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"sku\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.id@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT64\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"spu\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.spu@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT64\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"name\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.name@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"STRING\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"brand_id\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.brand_id@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid3\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid3@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid3name\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid3name@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"STRING\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid2\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid2@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid2name\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid2name@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"STRING\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid1\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid1@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cid1name\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cid1name@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"STRING\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"wyn\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.wyn@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"wstate\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.wstate@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"shop_id\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.shop_id@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"shop_name\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.shop_name@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"STRING\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\"\n"
            + "            ]\n"
            + "          },\n"
            + "          {\n"
            + "            \"name\": \"cold_flag\",\n"
            + "            \"source_value\": [\n"
            + "              \"sku.cold_flag@lite\"\n"
            + "            ],\n"
            + "            \"type\": \"INT32\",\n"
            + "            \"usage\": [\n"
            + "              \"profile\",\n"
            + "              \"retrieval\"\n"
            + "            ]\n"
            + "          }\n"
            + "        ]\n"
            + "      },\n"
            + "      \"schemaId\": 2\n"
            + "    }\n"
            + "  ]\n"
            + "}", JsonObject.class);

        JsonObject jsonObject1 = jsonObject.getAsJsonArray("config").get(0).getAsJsonObject().getAsJsonObject("resource");
        jsonObject1.addProperty("aa", "bb");
        System.out.println(gson.toJson(jsonObject1));
        System.out.println(gson.toJson(jsonObject));
    }

}

/**
 * Gson deserialize and serialize transient field.
 * https://stackoverflow.com/questions/31669876/gson-deserialize-and-serialize-transient-field
 */
class Solution001{

    private static final Gson gson1 = new Gson();
    private static final Gson gson2 = new GsonBuilder()
        .serializeNulls()
        .excludeFieldsWithModifiers(Modifier.ABSTRACT)
        .create();

    public static void main(String[] args) {
        Student stu = new Student("name", null);
        System.out.println(gson2.toJson(stu));
    }

    /**
     * bean 带有 transient 关键字 json 序列化时 该字段不会被序列化
     */
    static class Student{
        private transient String name;
        private Integer age;

        public Student() {
        }

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}
