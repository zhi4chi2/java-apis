JsonIgnoreProperties 从 JSON 中忽略指定的属性。


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.i = 1;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"i":1}
        System.out.println(json);

        json = "{\"i\":1, \"j\":2}";
        a = mapper.readValue(json, A.class);
        // 1
        System.out.println(a.i);
    }

    @JsonIgnoreProperties({ "j" })
    // 或者
    //    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class A {
        public Integer i;
    }
}
```


如果没有第 22/24 行，则将抛异常：
```
com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "j" (class org.example.demo.jackson.Test$A), not marked as ignorable (one known property: "i"])
```


另外， JsonIgnoreProperties 也影响序列化操作：
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.i = 1;
        a.j = 2;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"i":1}
        System.out.println(json);

        json = "{\"i\":1, \"j\":2, \"extra\":3}";
        a = mapper.readValue(json, A.class);
        // 1-null
        System.out.println(a.i + "-" + a.j);
    }

    @JsonIgnoreProperties({ "j", "extra" })
    public static class A {
        public Integer i;
        public Integer j;
    }
}
```


第 15 行输出 `{"i":1}` 而不是 `{"i":1,"j":2}`
