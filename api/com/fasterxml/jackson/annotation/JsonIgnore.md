```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

        json = "{\"i\":1, \"j\":2}";
        a = mapper.readValue(json, A.class);
        // 1-null
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        public Integer i;
        @JsonIgnore
        public Integer j;
    }
}
```


JsonIgnore 无论加在 field/getter/setter 上都可以。只要 field/getter/setter 中任一个有 @JsonIgnore ，则读写 JSON 都会 ignore 这个属性。


```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

        json = "{\"i\":1, \"j\":2}";
        a = mapper.readValue(json, A.class);
        // 1-null
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        public Integer i;
        //        @JsonIgnore
        private Integer j;

        //        @JsonIgnore
        public Integer getJ() {
            return j;
        }

        @JsonIgnore
        public void setJ(Integer j) {
            this.j = j;
        }
    }
}
```


下例中属性 j 在序列化时 ignore 在反序列化时正常：
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

        json = "{\"i\":1, \"j\":2}";
        a = mapper.readValue(json, A.class);
        // 1-2
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        public Integer i;
        private Integer j;

        @JsonIgnore
        public Integer getJ() {
            return j;
        }

        @JsonProperty
        public void setJ(Integer j) {
            this.j = j;
        }
    }
}
```


但是下例运行与期望的不一样：
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.i = 1;
        a.j = 2;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        // {"i":1,"j":2}
        System.out.println(json);

        json = "{\"i\":1, \"j\":2}";
        a = mapper.readValue(json, A.class);
        // 1-2
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        public Integer i;
        private Integer j;

        @JsonProperty
        public Integer getJ() {
            return j;
        }

        @JsonIgnore
        public void setJ(Integer j) {
            this.j = j;
        }
    }
}
```


FIXME 在 setter 上配置了 JsonIgnore ，因此期望在反序列化时不写入 j ，但第 20 行输出显示 j 也有写入！
