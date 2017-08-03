Jackson 默认检测属性的规则是：
- 所有 public fields
- 所有 public getters
- 所有 setters ，不管是不是 public


private getter 测试
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.a = "a1";
        a.a2 = "a2";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"a2":"a2"}
        System.out.println(json);
    }

    public static class A {
        private String a;
        public String a2;

        private String getA() {
            return a;
        }
    }
}
```


如果第 21 行改为 public 则将输出 `{"a":"a1","a2":"a2"}`


private setter 测试
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"a\":\"a2\"}";
        A a = mapper.readValue(json, A.class);
        // a2
        System.out.println(a.a);
    }

    public static class A {
        private String a;

        private void setA(String a) {
            this.a = a;
        }
    }
}
```


change visibility levels by using annotation @JsonAutoDetect
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.a = "a1";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"a":"a1"}
        System.out.println(json);

        json = "{\"a\":\"a2\"}";
        a = mapper.readValue(json, A.class);
        // a2
        System.out.println(a.a);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class A {
        private String a;
    }
}
```


注意第 24 行是 private ，如果将第 22 行注掉将抛异常 com.fasterxml.jackson.databind.JsonMappingException: No serializer found for class org.example.demo.jackson.Test$A and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.a = "a1";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {}
        System.out.println(json);

        json = "{}";
        a = mapper.readValue(json, A.class);
        // null
        System.out.println(a.a);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    public static class A {
        private String a;
    }
}
```
