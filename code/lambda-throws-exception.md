以下代码有误，编译报错 Unhandled exception type Exception
```java
package org.example.demo.java;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream.of("org.example.A", "org.example.B").map(name -> getClass(name)).collect(Collectors.toList());
    }

    public static Class<?> getClass(String name) throws Exception {
        return Class.forName(name);
    }
}
```


# 转换异常
```java
package org.example.demo.java;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream.of("org.example.A", "org.example.B").map(name -> {
            try {
                return getClass(name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static Class<?> getClass(String name) throws Exception {
        return Class.forName(name);
    }
}
```


或者
```java
package org.example.demo.java;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream.of("org.example.A", "org.example.B").map(name -> wrapClass(name)).collect(Collectors.toList());
    }

    public static Class<?> getClass(String name) throws Exception {
        return Class.forName(name);
    }

    public static Class<?> wrapClass(String name) {
        try {
            return getClass(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```


# Durian
FIXME https://github.com/diffplug/durian


# Reference
- https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception/30246026