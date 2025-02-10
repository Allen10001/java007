package com.tv.demo001.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 {
    "id" : "xxx",
    "works": [
        {
        "aa": "bb",
        "cc": "dd"
        },
        {
        "ee": "fff",
        "gg": "hh"
        }
    ],
    "award" : {
        "id" : "xxx",
        "awardName" : "最佳演员"
    }
 }
 */
public class Solution007 {

    private static final Gson gson = new Gson();
    private static Map<List<String>, String> res = new HashMap<>();

    public static void main(String[] args) {
        String jsonStr = "{\n"
            + "\t\"id\" : \"xxx\",\n"
            + "\t\"works\": [\n"
            + "\t\t{\n"
            + "\t\t\t\"aa\": \"bb\",\n"
            + "\t\t\t\"cc\": \"dd\"\n"
            + "\t\t},\n"
            + "\t\t{\n"
            + "\t\t\t\"ee\": \"fff\",\n"
            + "\t\t\t\"gg\": \"hh\"\n"
            + "\t\t}\n"
            + "\t],\n"
            + "\t\"award\" : {\n"
            + "\t\t\"id\" : \"xxx\",\n"
            + "\t\t\"awardName\" : \"最佳演员\" \n"
            + "\t}\n"
            + "}";
        JsonObject target = gson.fromJson(jsonStr, JsonObject.class);
        Solution007 solution007 = new Solution007();
        solution007.solution(target, new ArrayList<>());

        for (Map.Entry<List<String>, String> entry : res.entrySet()) {
            StringBuffer sb = new StringBuffer();
            entry.getKey().forEach(item -> sb.append(item).append("."));

            System.out.println(sb.subSequence(0, sb.length()-1).toString().concat("=").concat(entry.getValue()));
        }
    }

    public void solution(JsonObject jsonObject, List<String> path) {

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().isJsonPrimitive()) {
                path.add(entry.getKey());
                res.put(new ArrayList<>(path), entry.getValue().getAsString());
                path.remove(path.size()-1);
            } else if (entry.getValue().isJsonObject()) {
                path.add(entry.getKey());
                solution(entry.getValue().getAsJsonObject(), path);
                path.remove(path.size()-1);
            } else if (entry.getValue().isJsonArray()) {
                String arrayKey = entry.getKey();
                JsonArray jsonArray = entry.getValue().getAsJsonArray();
                for (int i=0; i<jsonArray.size(); i++) {
                    path.add(arrayKey.concat("[").concat(String.valueOf(i)).concat("]"));
                    solution(jsonArray.get(i).getAsJsonObject(), path);
                    path.remove(path.size()-1);
                }
            }
        }

    }

}


class Solution008 {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("multiQueueSplit.queueCandidateSetConfigs", "[{queueCandidateSetName=allSku,policy=ALL_SKU,config={}}]");
        System.out.println(gson.toJson(jsonObject));
    }
}

class Solution009 {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("a.b.c", "111111111");
        jsonObject.addProperty("a.b.c.d", "22222222");
        jsonObject.addProperty("d.e.f.g", "333333");
        jsonObject.addProperty("a.b.c.d", "111111111");
        jsonObject.addProperty("a.b.c.f", "111111111");
        System.out.println(gson.toJson(jsonObject));

        Map<String, String> map = gson.fromJson(jsonObject, Map.class);
        System.out.println(map.size());
    }
}

class Solution010 {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String jsonStr = "{\n"
            + "    \"is_multi_version\":true,\n"
            + "    \"default\": {\n"
            + "        \"realTimeC3ClkKgExtendIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"false\",\n"
            + "        \"novelKnnIndexRecallV2.KnnIndexRecallerV2Processor.processorSwitch\": \"false\",\n"
            + "        \"realTimeC3ClkIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"false\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
            + "        \"#CONDITION#packageTriggerResult!=null&&packageTriggerResult.isTriggered()#.relBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"2500\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.limit\": \"2500\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.indexFilterConfigs\": \"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"common-simrel\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"sku_i2i_center\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
            + "        \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.tableRequests\": \"[{name=app_jdr_i2i_itemcf_same_search_a_s_d,seedType=sku,recallTag=76,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_trans_search_a_s_d,seedType=sku,recallTag=77,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_overall_mbox_a_d_d,seedType=sku,recallTag=78,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_qz_ord_a_d_d,seedType=sku,recallTag=79,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]}]\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_seed_intervene_name\": \"SimpleCidMatchSeedIntervene\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.fillSeedAttrSwitch\": \"true\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallResultBuilderName\": \"com.jd.rec.odin.processor.recaller.index.indexresultbuilder.RetrieveNodeRecallResultBuilder\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_first_limit_per_seed\": \"200\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_second_limit_per_seed\": \"50\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"multi-score-simrel-iterative\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"sku_i2i_center\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.multi_score_second_limit_per_seed\": \"50\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallType\": \"RECALL_273\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.multi_score_first_limit_per_seed\": \"200\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"4000\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.limit\": \"4000\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.indexFilterConfigs\": \"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.max_iterative_times\": \"3\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.tableRequests\": \"[{name=app_jdr_i2i_itemcf_overall_new_frame2_a_d_d,seedType=sku,recallTag=273,limit=300,seedLimit=30,limitPerSeed=300,priority=1,tableFilters=[]}]\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_seed_intervene_conf\": \"{match_pids=[\\\"205003\\\",\\\"320000\\\",\\\"320009\\\",\\\"320010\\\",\\\"320008\\\",\\\"320007\\\",\\\"320006\\\"],match_pid_source_info=[{source=0,weight=0.7},{source=1,weight=0.8},{source=2,weight=0.5},{source=3,weight=0.5},{source=6,weight=0.5},{source=7,weight=0.5},{source=8,weight=0.7},{source=9,weight=0.8}],filter_match_cid_name=cid3,page_source_info=[{source=2,weight=0.1},{source=3,weight=0.1},{source=4,weight=0.1}]}\",\n"
            + "        \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.iterative_timeout_ms\": \"15\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"common-xapian\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.featureScoreSwitch\": \"true\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.retrieveNode\": \"{operationType=OP_OR,subNodes=[{leafNodeConfig={leafSourceConfig={seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=cb_cid3,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=skucid3}}]}}}]}\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"darab_sku_recall_cb_term_weight_package\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.indexDiversityConfigs\": \"[]\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.retrieveNodeSwitch\": \"true\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.unilateralFeatureConfigs\": \"[{indexFeatureKeyEnum=HOT_SCORE,weight=1.0}]\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallType\": \"HOTSALE\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.limit\": \"500\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"500\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallResultBuilderName\": \"com.jd.rec.odin.processor.recaller.index.indexresultbuilder.RetrieveNodeRecallResultBuilder\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{}\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
            + "        \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.indexServerName\": \"\\\"\\\"\",\n"
            + "        \"behaviorSourceStandardRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
            + "        \"behaviorSourceStandardRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.i2iRequestConfigs\": \"[{basicRequest={Seed2KnnRecall={searcher_database_name=sku_i2i_center_ganr_gnn_source,searcher_extra_fields=[embedding],searcher_token_id=common-source,total_limit=50,searcher_filters=[],table_requests_conf=[{name=__behavior_source__,seed_limit=50,limit=50,seed_type=sku}]},EmbeddingParseFromResp={fied_name=embedding},KnnRecall={searcher_database_name=sku_i2i_center_ganr_gnn_faiss,searcher_extra_fields=[],total_limit=3000,total_limit_searcher=1200,searcher_filters=[cid3_filter]}},embeddingName=embeddingName1,seedType=[sku],filterNames=[cid3_filter],diversityNames=[],featureNames=[],recallType=RECALL_271}]\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.embeddingConfigs\": \"[{embeddingName=embeddingName1,modelName=attr_gnn_model,version=1,dimension=64,limitPerSeed=100,distanceFunc=DirectEqual,distanceFusion=max}]\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.indexFilterConfigs\": \"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.limit\": \"3000\",\n"
            + "        \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.lastLimit\": \"3000\"\n"
            + "    }\n"
            + "}";

//        String jsonStr = "{\n"
//            + "\n"
//            + "    \"realTimeC3ClkKgExtendIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"false\",\n"
//            + "    \"novelKnnIndexRecallV2.KnnIndexRecallerV2Processor.processorSwitch\": \"false\",\n"
//            + "    \"realTimeC3ClkIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"false\",\n"
//            + "\n"
//            + "\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
//            + "    \"#CONDITION#packageTriggerResult!=null&&packageTriggerResult.isTriggered()#.relBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"2500\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.limit\": \"2500\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.indexFilterConfigs\": \"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"common-simrel\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"sku_i2i_center\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
//            + "    \"relBehaviorIndexRecaller.StandardIndexRecallerProcessor.tableRequests\": \"[{name=app_jdr_i2i_itemcf_same_search_a_s_d,seedType=sku,recallTag=76,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_trans_search_a_s_d,seedType=sku,recallTag=77,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_overall_mbox_a_d_d,seedType=sku,recallTag=78,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]},{name=app_jdr_i2i_itemcf_qz_ord_a_d_d,seedType=sku,recallTag=79,limit=2000,seedLimit=20,limitPerSeed=100,priority=1,tableFilters=[]}]\",\n"
//            + "\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_seed_intervene_name\": \"SimpleCidMatchSeedIntervene\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.fillSeedAttrSwitch\": \"true\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallResultBuilderName\": \"com.jd.rec.odin.processor.recaller.index.indexresultbuilder.RetrieveNodeRecallResultBuilder\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_first_limit_per_seed\": \"200\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_second_limit_per_seed\": \"50\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"multi-score-simrel-iterative\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"sku_i2i_center\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.multi_score_second_limit_per_seed\": \"50\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.recallType\": \"RECALL_273\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.multi_score_first_limit_per_seed\": \"200\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"4000\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.limit\": \"4000\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.indexFilterConfigs\": \"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.max_iterative_times\": \"3\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.tableRequests\": \"[{name=app_jdr_i2i_itemcf_overall_new_frame2_a_d_d,seedType=sku,recallTag=273,limit=300,seedLimit=30,limitPerSeed=300,priority=1,tableFilters=[]}]\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.multi_score_seed_intervene_conf\": \"{match_pids=[\\\"205003\\\",\\\"320000\\\",\\\"320009\\\",\\\"320010\\\",\\\"320008\\\",\\\"320007\\\",\\\"320006\\\"],match_pid_source_info=[{source=0,weight=0.7},{source=1,weight=0.8},{source=2,weight=0.5},{source=3,weight=0.5},{source=6,weight=0.5},{source=7,weight=0.5},{source=8,weight=0.7},{source=9,weight=0.8}],filter_match_cid_name=cid3,page_source_info=[{source=2,weight=0.1},{source=3,weight=0.1},{source=4,weight=0.1}]}\",\n"
//            + "    \"simBehaviorIndexRecaller.StandardIndexRecallerProcessor.commonParamMap.iterative_timeout_ms\": \"15\",\n"
//            + "\n"
//            + "\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.tokenId\": \"common-xapian\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.featureScoreSwitch\": \"true\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.retrieveNode\": \"{operationType=OP_OR,subNodes=[{leafNodeConfig={leafSourceConfig={seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=cb_cid3,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=skucid3}}]}}}]}\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.databaseName\": \"darab_sku_recall_cb_term_weight_package\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.indexDiversityConfigs\": \"[]\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.retrieveNodeSwitch\": \"true\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.unilateralFeatureConfigs\": \"[{indexFeatureKeyEnum=HOT_SCORE,weight=1.0}]\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallType\": \"HOTSALE\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.requestTimeout\": \"100\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.limit\": \"500\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.lastLimit\": \"500\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallResultBuilderName\": \"com.jd.rec.odin.processor.recaller.index.indexresultbuilder.RetrieveNodeRecallResultBuilder\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{}\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.searcherFrameWork\": \"1\",\n"
//            + "    \"userWeakPersonalizedIndexRecaller.StandardIndexRecallerProcessor.indexServerName\": \"\\\"\\\"\",\n"
//            + "\n"
//            + "\n"
//            + "    \"behaviorSourceStandardRecaller.StandardIndexRecallerProcessor.recallSeedConfig\": \"{seedSourceEnum = FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
//            + "    \"behaviorSourceStandardRecaller.StandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
//            + "\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.processorSwitch\": \"true\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.recallSeedConfig\":\"{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=sku,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=sku}}]}\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.i2iRequestConfigs\": \"[{basicRequest={Seed2KnnRecall={searcher_database_name=sku_i2i_center_ganr_gnn_source,searcher_extra_fields=[embedding],searcher_token_id=common-source,total_limit=50,searcher_filters=[],table_requests_conf=[{name=__behavior_source__,seed_limit=50,limit=50,seed_type=sku}]},EmbeddingParseFromResp={fied_name=embedding},KnnRecall={searcher_database_name=sku_i2i_center_ganr_gnn_faiss,searcher_extra_fields=[],total_limit=3000,total_limit_searcher=1200,searcher_filters=[cid3_filter]}},embeddingName=embeddingName1,seedType=[sku],filterNames=[cid3_filter],diversityNames=[],featureNames=[],recallType=RECALL_271}]\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.embeddingConfigs\":\"[{embeddingName=embeddingName1,modelName=attr_gnn_model,version=1,dimension=64,limitPerSeed=100,distanceFunc=DirectEqual,distanceFusion=max}]\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.indexFilterConfigs\":\"[{filterScope=GLOBAL,filterInstanceConfig={filterSourceConfigs=[{seedSourceEnum=FETCHER,fetchExtendConfigs=[{seedType=value_match,fetchFullName=com.jd.rec.odin.processor.recaller.index.standard.fetcher.recrequest.PackageStrategyDataFetcher,fetcherDefineConfig={type=filtercid3}}]},{seedSourceEnum=CUSTOM_PARAM,fixedSeedConfigs=[{seedType=field_name,filterValue=cid3}]}],filterInstance={aliasName=sku_filter,filterName=CommonFieldMatchFilter,filterOut=false,switchOff=false}}}]\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.limit\":\"3000\",\n"
//            + "    \"parallelStandardIndexRecallFaiss.ParallelStandardIndexRecallerProcessor.lastLimit\":\"3000\"\n"
//            + "}";

        Map<String, Object> map = gson.fromJson(jsonStr, HashMap.class);
        map.remove("is_multi_version");
        map.forEach((k, v) -> {
            System.out.println(k+"="+v);
        });
    }
}
