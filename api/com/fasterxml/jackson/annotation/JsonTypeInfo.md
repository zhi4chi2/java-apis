@JsonTypeInfo 用于在 json 中包含类型信息。


例子
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        B b = new B();
        b.b = "b";

        A a = b;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"class":"org.example.demo.jackson.Test$B","b":"b"}
        System.out.println(json);

        json = "{\"class\":\"org.example.demo.jackson.Test$C\",\"c\":\"c\"}";
        a = mapper.readValue(json, A.class);
        // class org.example.demo.jackson.Test$C
        System.out.println(a.getClass());
        // c
        System.out.println(((C) a).c);
    }

    @JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "class")
    public static abstract class A {
    }

    public static class B extends A {
        public String b;
    }

    public static class C extends A {
        public String c;
    }
}
```


但是 List 是 root 对象时，要如下
```java
package org.example.demo.jackson;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        B b = new B();
        b.b = "b";

        C c = new C();
        c.c = "c";

        List<A> as = new ArrayList<A>();
        as.add(b);
        as.add(c);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerFor(new TypeReference<List<A>>() {
        }).writeValueAsString(as);
        // [{"class":"org.example.demo.jackson.Test$B","b":"b"},{"class":"org.example.demo.jackson.Test$C","c":"c"}]
        System.out.println(json);

        json = "[{\"class\":\"org.example.demo.jackson.Test$B\",\"b\":\"b\"},{\"class\":\"org.example.demo.jackson.Test$C\",\"c\":\"c\"}]";
        List<A> alist = mapper.readerFor(new TypeReference<List<A>>() {
        }).readValue(json);
        for (A a : alist) {
            // org.example.demo.jackson.Test$B@3ffc5af1[b=b]
            // org.example.demo.jackson.Test$C@5e5792a0[c=c]
            System.out.println(ToStringBuilder.reflectionToString(a));
        }
    }

    @JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "class")
    public static abstract class A {
    }

    public static class B extends A {
        public String b;
    }

    public static class C extends A {
        public String c;
    }
}
```
