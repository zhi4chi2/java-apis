@JsonProperty 用于改变属性名。


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.i = 1;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"n":1}
        System.out.println(json);

        json = "{\"n\":1}";
        a = mapper.readValue(json, A.class);
        // 1
        System.out.println(a.i);

        // 不可使用原属性名
        json = "{\"i\":1}";
        // com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "i" (class org.example.demo.jackson.Test$A), not marked as ignorable (one known property: "n"])
        a = mapper.readValue(json, A.class);
    }

    public static class A {
        @JsonProperty("n")
        public Integer i;
    }
}
```
