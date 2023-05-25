package com.tv.demo001.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * jackson示例
 *
 * @author hubo88
 * @description
 * @date 2023/5/13 11:23 AM
 */
public class JacksonDemo001 {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {


        String msg = "{\"groupIds\":[88329763],\"messageType\":4,\"skuId\":100028968466,\"status\":1,\"ts\":1683901766233}";
        JsonNode value = objectMapper.readTree(msg);

        int messageType = value.path("messageType").asInt();

        JsonNode groupIds = value.path("groupIds");
        if (groupIds.isMissingNode()) {
            return;
        }
        if (!groupIds.isArray()) {
            return;
        }

        JsonNode sku = value.path("skuId");
        if (sku.isMissingNode()) {
            return;
        }
        String skuId = sku.asText();
        JsonNode tsNode = value.path("ts");
        if (tsNode == null) {
            return;
        }
        long ts = tsNode.asLong();

        Set<Integer> oldGroups = Sets.newHashSet();
        oldGroups.add(88329763);
        oldGroups.add(88329764);
        Set<Integer> newGroups = Sets.newHashSet();

        switch (messageType) {
            case 1:
                // 1 ：该sku下[全量]groupid （值里面会有具体groupid）
                for (JsonNode groupId : groupIds) {
                    newGroups.add(groupId.asInt());
                }
                ObjectNode attrJsonNode = objectMapper.createObjectNode();
                ArrayNode newGroupArr = attrJsonNode.putArray("group");
                newGroups.forEach(groupId -> newGroupArr.add(groupId));
                break;
            case 3:
                // 3：[增量] 入池 groupid （值里面会有具体groupid）
                for (JsonNode groupId : groupIds) {
                    System.out.println(groupId.asInt());
                    newGroups.add(groupId.asInt());
                }
                oldGroups = Collections.emptySet();;
                newGroups.addAll(oldGroups);
                ObjectNode attrJsonNode3 = objectMapper.createObjectNode();
                ArrayNode newGroupArr3 = attrJsonNode3.putArray("group");
                newGroups.forEach(groupId -> newGroupArr3.add(groupId));
                break;
            case 2:
                break;
            case 4:
                // 4：[增量] 出池 groupid （值里面会有具体groupid）
                for (JsonNode groupId : groupIds) {
                    oldGroups.remove(groupId.asInt());
                }
                ObjectNode attrJsonNode01 = objectMapper.createObjectNode();
                ArrayNode newGroupArr01 = attrJsonNode01.putArray("group");
                oldGroups.forEach(groupId -> newGroupArr01.add(groupId));
                break;
            default:
                return;
        }
    }
}
