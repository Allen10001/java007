package com.tv.demo001.guice.test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import java.io.Serializable;
import javax.inject.Named;

/**
 * @author hubo88
 * @description 练习
 * @date 2022/8/30 4:46 PM
 */
public class Demo {

    public static void main(String[] args) {
        // ducc 配置类
        DuccConfig duccConfig = new DuccConfig("aa","bb","cc");
        Module module = binder -> binder.bind(DuccConfig.class).annotatedWith(Names.named("duccConfig")).toInstance(duccConfig);
        Injector injector = Guice.createInjector(module);
        injector.getInstance(DuccConfig.class).toString();
    }
}

@Named("duccConfig")
class DuccConfig implements Serializable {

    private static final long serialVersionUID = 42L;

    private String domain;
    private String appName;
    private String token;

    @Inject
    public DuccConfig(String domain, String appName, String token) {
        this.domain = domain;
        this.appName = appName;
        this.token = token;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DuccConfig{" +
            "domain='" + domain + '\'' +
            ", appName='" + appName + '\'' +
            ", token='" + token + '\'' +
            '}';
    }
}
