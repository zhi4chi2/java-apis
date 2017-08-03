序列化时，可能期望仅序列化接口/基类中的部分字段。


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

public class Test {
    public static void main(String[] args) throws Exception {
        B1 b1 = new B1();
        b1.b = "b1";
        b1.bb = "bb1";

        A a = new A();
        a.i = 1;
        a.b = b1;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"i":1,"b":{"b":"b1"}}
        System.out.println(json);
    }

    public static class A {
        public Integer i;

        @JsonSerialize(typing = Typing.STATIC)
        // 或者
        //        @JsonSerialize(as = B.class)
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


如果 26,28 行都注掉，则将输出：
```
{"i":1,"b":{"b":"b1","bb":"bb1"}}
```
