package com.tv.demo001.controller;


import com.google.common.collect.Lists;
import org.bouncycastle.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Description: 向zk注册服务demo
 * @Author: Allen
 */
@RestController
public class ZKProviderUserController {

    @GetMapping("/list")
    public List<String> getList(){
        return Lists.newArrayList(Strings.split("allen,john,luccy",','));
    }
}
