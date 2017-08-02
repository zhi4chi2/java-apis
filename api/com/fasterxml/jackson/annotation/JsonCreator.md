Jackson 不要求有无参数构造器。


例子：
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"ii\":1, \"j\":2}";
        A a = mapper.readValue(json, A.class);
        // 1-2
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        private Integer i;
        private Integer j;

        @JsonCreator
        public A(@JsonProperty("ii") Integer i, @JsonProperty("jj") Integer j, @JsonProperty("kk") Integer k) {
            // 1 null null
            System.out.println(i + " " + j + " " + k);
            this.i = i;
            this.j = j;
        }

        public Integer getJ() {
            return j;
        }

        public void setJ(Integer j) {
            // 2
            System.out.println(j);
            this.j = j;
        }
    }
}
```


注意第 11 行使用的是 ii, __j__ ， ii 匹配到构造器参数，而 j 则通过 setter 设置。构造器参数可以在 JSON 中不存在，如 jj, kk 。但 JSON 中的属性必须在构造器或 setter 中存在，否则抛异常，如将构造器中的 i 参数去除，则将抛异常 com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "ii" (class org.example.demo.jackson.Test$A), not marked as ignorable (3 known properties: "jj", "j", "kk"])


使用有参数的构造器通常用于构建不可变类。


使用构造器不影响使用 setter 如例子中的 j


@JsonCreator 也用于工厂方法：
```java
package org.example.demo.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"ii\":1}";
        A a = mapper.readValue(json, A.class);
        // 1
        System.out.println(a.i);
    }

    public static class A {
        private Integer i;

        @JsonCreator
        public static A create(@JsonProperty("ii") Integer i) {
            A a = new A();
            a.i = i;
            return a;
        }
    }
}
```


@JsonCreator 的另一种用法（ delegate 方式）：
```java
package org.example.demo.jackson;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"ii\":1, \"j\":2}";
        A a = mapper.readValue(json, A.class);
        // 1-null
        System.out.println(a.i + "-" + a.j);
    }

    public static class A {
        private Integer i;
        private Integer j;

        @JsonCreator
        public A(Map<String, Object> delegate) {
            this.i = (Integer) delegate.get("ii");
        }

        public Integer getJ() {
            return j;
        }

        public void setJ(Integer j) {
            System.out.println(j);
            this.j = j;
        }
    }
}
```


注意这种方式时不能与 setter 共同工作。


delegate 方式：
- 只能有一个参数
- 参数不能有 @JsonProperty 注解。

