package com.tv.demo001.reflections;

import static org.reflections.scanners.Scanners.Resources;
import static org.reflections.scanners.Scanners.ConstructorsAnnotated;
import static org.reflections.scanners.Scanners.ConstructorsSignature;
import static org.reflections.scanners.Scanners.FieldsAnnotated;
import static org.reflections.scanners.Scanners.MethodsAnnotated;
import static org.reflections.scanners.Scanners.MethodsParameter;
import static org.reflections.scanners.Scanners.MethodsReturn;
import static org.reflections.scanners.Scanners.MethodsSignature;
import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;
import static org.reflections.ReflectionUtils.*;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.tv.demo001.guice.test.Demo;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
import javax.websocket.server.PathParam;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * reflections
 * https://github.com/ronmamo/reflections
 *
 *
 * @author hubo88
 * @description
 * @date 2023/2/17 9:40 PM
 */
public class UsageReflections {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
//        Solution001 solution001 = injector.getInstance(Solution001.class);
//        solution001.resolve();

        Solution003 solution003 = injector.getInstance(Solution003.class);
        solution003.resolve();
    }
}

/**
 * 1. Usage
 */
@Singleton
class Solution001{

    public void resolve() {
        Reflections reflections = new Reflections("com.tv");
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(AbstractModule.class).asClass());
        subTypes.forEach(item -> System.out.println(item));
        System.out.println("-----------------------");
        Set<Class<?>> annotated =
            reflections.get(SubTypes.of(TypesAnnotated.with(Singleton.class)).asClass());
        annotated.forEach(item -> System.out.println(item));

       /*
       // in 0.9.x , 用下面的代码
       Set<Class<? extends SomeType>> subTypes =
            reflections.getSubTypesOf(SomeType.class);

        Set<Class<?>> annotated =
            reflections.getTypesAnnotatedWith(SomeAnnotation.class);
       */
    }
}

/**
 * 2. Scan
 */
@Singleton
class Solution002{

    public void resolve() {
        Reflections reflections = new Reflections(
            new ConfigurationBuilder()
                .forPackage("com.tv")
                .filterInputsBy(new FilterBuilder()
                                    .includePackage("com.tv")
                                    .excludePackage("com.tv.demo001.guice"))
                .setScanners(TypesAnnotated, MethodsAnnotated, MethodsReturn));

        // 所有的扫描器都加上会导致 store size 增加
        // scan package with all standard scanners
//        Reflections reflections = new Reflections("com.my.project", Scanners.values());
//        Set<Class<?>> beanClasses = reflections.getTypesAnnotatedWith(Singleton.class);

    }
}


/**
 * 3. Query
 */
@Singleton
class Solution003{

    public void resolve() {

        // 所有的扫描器都加上会导致 store size 增加
        // scan package with all standard scanners
        Reflections reflections = new Reflections("com.tv", Scanners.values());

        // SubTypes
        Set<Class<?>> modules =
            reflections.get(SubTypes.of(Module.class).asClass());
        modules.forEach(item -> System.out.println(item));

// TypesAnnotated (*1)
        Set<Class<?>> singletons =
            reflections.get(TypesAnnotated.with(Singleton.class).asClass());

// MethodsAnnotated
        Set<Method> resources =
            reflections.get(MethodsAnnotated.with(GetMapping.class).as(Method.class));

// FieldsAnnotated
        Set<Field> ids =
            reflections.get(FieldsAnnotated.with(Id.class).as(Field.class));

// Resources  ？？ 一直扫描不出来
        System.out.println("resource");
        Set<String> properties =
            reflections.get(Resources.with(".*\\.properties"));

        properties.forEach(item -> System.out.println(item));


//   ----------------- More scanners -----------------
// MethodsReturn
        Set<Method> voidMethods =
            reflections.get(MethodsReturn.with(void.class).as(Method.class));

// MethodsSignature
        Set<Method> someMethods =
            reflections.get(MethodsSignature.of(long.class, int.class).as(Method.class));

// MethodsParameter
        Set<Method> pathParam =
            reflections.get(MethodsParameter.of(PathParam.class).as(Method.class));

// ConstructorsAnnotated
        Set<Constructor> injectables =
            reflections.get(ConstructorsAnnotated.with(Inject.class).as(Constructor.class));

// ConstructorsSignature
        Set<Constructor> someConstructors =
            reflections.get(ConstructorsSignature.of(String.class).as(Constructor.class));

// MethodParameterNamesScanner
//        List<String> parameterNames =
//            reflections.getMemberParameterNames(number);

// MemberUsageScanner
//        Set<Member> usages =
//            reflections.getMemberUsages();


    }
}

/**
 * 4. ReflectionUtils
 */
@Singleton
class Solution004{

    public void resolve() {
        Reflections reflections = new Reflections("com.tv");
//
//        Set<Class<?>>    superTypes   = ReflectionUtils.get(SuperTypes.of(T));
//        Set<Field>       fields       = ReflectionUtils.get(Fields.of(T));
//        Set<Constructor> constructors = ReflectionUtils.get(Constructors.of(T));
//        Set<Method>     methods      = ReflectionUtils.get(Methods.of(T));
//        Set<URL>         resources    = ReflectionUtils.get(Resources.with(T));
//        Set<Annotation>  annotations  = ReflectionUtils.get(Annotations.of(T));
//
//        Set<Class<? extends Annotation>> annotationTypes = ReflectionUtils.get(AnnotationTypes.of(T));
    }
}



