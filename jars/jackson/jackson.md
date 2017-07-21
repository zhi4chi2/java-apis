# Active Jackson projects
## Core modules
- Streaming(jackson-core) - 定义 low-level streaming API 以及 JSON 特定的实现。
- Annotations(jackson-annotations) - 标准 Jackson 注解
- Databind(jackson-databind) - 依赖 streaming and annotations


## Third-party datatype modules
第三方 datatype modules 是插件，可以通过 ObjectMapper.registerModule() 注册。第三方 datatype modules 通过添加 serializers and deserializers 以支持各种 java 类型，从而使得 ObjectMapper / ObjectReader / ObjectWriter 可以读写这些类型。


Jackson team 维护的 datatype modules 有：
- 标准 Collections datatype modules
- *HPPC(jackson-datatype-hppc) - 支持 High-Performance Primitive Containers
- *PCollections(jackson-datatype-pcollections) - 支持 PCollections datatypes
- Guava(jackson-datatype-guava) - 支持 Guava datatypes
- Hibernate(jackson-datatype-hibernate3, jackson-datatype-hibernate4, jackson-datatype-hibernate5) - 支持 Hibernate 特性，包含延迟加载和代理
- Joda(jackson-datatype-joda) - 支持 Joda
- JDK7 - 支持 JDK7 data types, 从 2.7 开始废弃，转入 jackson-databind 中。因为 2.7 要求至少 JDK7
- JDK8(jackson-datatype-jdk8) - 包括 Optional ，但不包括 JSR-310 中的类型
- JSR-310(jackson-datatype-jsr310) - Java 8 Dates
- JSR-353(jackson-datatype-jsr353) - Java JSON API
- org.json(jackson-datatype-json-org) - 支持 "org.json JSON lib" 例如 JSONObject, JSONArray


另有更多不是 Jackson team 维护的 datatype modules 参见 https://github.com/FasterXML/jackson


## Providers for JAX-RS
Jackson JAX-RS Providers(jackson-datatype-jaxrs, jackson-jaxrs-base) 添加对 JAX-RS implementations(Jersey, RESTeasy, CXF) 的 dataformat 支持。


实现 MessageBodyReader and MessageBodyWriter 。


支持 formats:
- JSON(jackson-jaxrs-json-provider)
- Smile(jackson-jaxrs-smile-provider)
- XML(jackson-jaxrs-xml-provider)
- YAML(jackson-jaxrs-yaml-provider)
- CBOR(jackson-jaxrs-cbor-provider)


## Data format modules
支持 JSON 之外的 format 。


当前 data format modules 有：
- Avro(jackson-dataformat-avro)
- CBOR(jackson-dataformat-cbor) - a binary JSON variant
- CSV(jackson-dataformat-csv)
- Properties(jackson-dataformat-properties)
- Protobuf(jackson-dataformat-protobuf) - 类似 Avro
- Smile(jackson-dataformat-smile) (binary JSON)
- XML(jackson-dataformat-xml)
- YAML(jackson-dataformat-yaml)
- ion(jackson-dataformat-ion) - https://amznlabs.github.io/ion-docs/


还有更多不是 Jackson core 提供的 data format modules 参见 https://github.com/FasterXML/jackson


## JVM Language modules
- Kotlin(jackson-module-kotlin)
- Scala(jackson-module-scala_2.10, jackson-module-scala_2.11, jackson-module-scala_2.12)


## Support for Schemas
可以根据 Jackson annotations 生成 schemas 。


JSON Schema
- Ant Task for JSON Schema Generation
- JSON Schema generator(jackson-module-jsonSchema)
- Maven plug-in - https://github.com/FasterXML/jackson-schema-maven-plugin


Other schema languages
- Ember Schema Generator


## Other modules, stable
- jackson-module-jaxb-annotations
- jackson-module-parameter-names
- jackson-module-afterburner
- jackson-module-guice
- jackson-module-mrbean
- jackson-module-osgi
- jackson-module-paranamer


## Jackson jr
Jackson databind 太大，另有 Jackson jr 用于 mobile phones 或 light usage 。


Jackson jr 基于 jackson-core, 不依赖 jackson-databind, 只支持部分功能。


- jackson-jr-all
- jackson-jr-objects
- jackson-jr-retrofit2
- jackson-jr-stree


## Third-party non-module libraries based on Jackson
FIXME


# 例子
```java5
package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        A val = new A();
        val.s = "example";
        val.i = 20;
        val.d = new Date();
        System.out.println(val); // example 20 Sat Jan 23 13:23:19 CST 2016

        String s = mapper.writeValueAsString(val);
        System.out.println(s); // {"s":"example","i":20,"d":"2016-01-23"}

        A val2 = mapper.readValue(s, A.class);
        System.out.println(val2); // example 20 Sat Jan 23 00:00:00 CST 2016

        A[] vals = new A[2];
        vals[0] = val;
        vals[1] = val2;
        s = mapper.writeValueAsString(vals);
        System.out.println(s); // [{"s":"example","i":20,"d":"2016-01-23"},{"s":"example","i":20,"d":"2016-01-23"}]

        A[] vals2 = mapper.readValue(s, A[].class);
        for (A myValue : vals2) {
            // example 20 Sat Jan 23 00:00:00 CST 2016
            // example 20 Sat Jan 23 00:00:00 CST 2016
            System.out.println(myValue);
        }

        s = "{\"errcode\":0,\"errmsg\":\"ok\"}";
        @SuppressWarnings("unchecked")
        Map<String, Object> data = mapper.readValue(s, Map.class);
        System.out.println(data.get("errcode")); // 0
        System.out.println(data.get("errcode").getClass()); // class java.lang.Integer
    }
}

class A {
    public String s;
    public int i;
    public Date d;

    @Override
    public String toString() {
        return s + " " + i + " " + d;
    }
}
```


# Reference
- https://github.com/FasterXML/jackson
