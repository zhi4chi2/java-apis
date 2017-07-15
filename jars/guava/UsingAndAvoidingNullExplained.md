# Using and avoiding null
many of Guava's utilities are designed to fail fast in the presence of null rather than allow nulls to be used


Guava provides a number of facilities both to make using null easier, when you must, and to help you avoid using null.


# Specific Cases
不应该使用 null 作为 set 的值，或者 map 的 key


如果使用 null 作为 map 的 value ，则应该改为 keep a separate Set of non-null keys (or null keys).


如果在 List 中使用 null ，则应该改为使用 Map<Integer, E>


# Optional
```java
package org.example.demo.guava;

import com.google.common.base.Optional;

public class Test {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        // true
        System.out.println(possible.isPresent());
        // 5
        System.out.println(possible.get());
    }
}
```


# Convenience methods
```java
package org.example.demo.guava;

import com.google.common.base.MoreObjects;

public class Test {
    public static void main(String[] args) {
        // 2
        System.out.println(MoreObjects.firstNonNull(null, 2));
        // 1
        System.out.println(MoreObjects.firstNonNull(1, null));
        // 1
        System.out.println(MoreObjects.firstNonNull(1, 2));
        // java.lang.NullPointerException
        System.out.println(MoreObjects.firstNonNull(null, null));
    }
}
```


- com.google.common.base.Strings.nullToEmpty(String)
- com.google.common.base.Strings.emptyToNull(String)
- com.google.common.base.Strings.isNullOrEmpty(String)
these methods are primarily for interfacing with unpleasant APIs that equate null strings and empty strings. 不建议 write code that conflates null strings and empty strings, treating them as the same thing is a disturbingly common code smell

