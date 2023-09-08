package com.tv.demo001.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigRenderOptions;
import org.checkerframework.checker.units.qual.C;

/**
 * @author hubo88
 * @description
 * @date 2023/9/6 9:51 PM
 */
public class ConfigTest {

    public static void main(String[] args) {
        ConfigTest configTest = new ConfigTest();
        configTest.solve();
    }

    public void solve() {
        String key = "render$commonImageFilter.CommonImageFilterProcessor.filterConfig.LowQualityImageFilter.replaceImageSwitch";
        String val = "true";
        Config paramConfig = ConfigFactory.parseString(
            key+ " = " + val
        );
        ConfigObject configObject = paramConfig.root();
        String configObjStr = configObject.render(ConfigRenderOptions.concise().setFormatted(true));
        System.out.println(configObjStr);
    }
}
