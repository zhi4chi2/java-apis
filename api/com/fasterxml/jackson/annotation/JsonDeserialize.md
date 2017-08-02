反序列化时，声明的类型可能是接口，但希望反序列化成具体的类。


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"i\":1,\"b\":{\"b\":\"b1\",\"bb\":\"bb1\"}}";
        A a = mapper.readValue(json, A.class);
        // 1-b1-bb1
        System.out.println(a.i + "-" + a.b.b + "-" + ((B1) a.b).bb);
    }

    public static class A {
        public Integer i;

        @JsonDeserialize(as = B1.class)
        public B b;
    }

    public static class B {
        public String b;
    }

    public static class B1 extends B {
        public String bb;
    }
}
```
