- com.google.common.base.Objects.equal(Object, Object) / java.util.Objects.equals(Object, Object) 等价
- com.google.common.base.Objects.hashCode(Object...) / java.util.Objects.hash(Object...) 等价
- com.google.common.base.MoreObjects.toStringHelper(Object)
- com.google.common.collect.ComparisonChain


# toString
```java
package org.example.demo.guava;

import com.google.common.base.MoreObjects;

public class Test {
    public static void main(String[] args) {
        // Test{x=1}
        System.out.println(MoreObjects.toStringHelper(new Test()).add("x", 1).toString());
        // MyObject{x=1}
        System.out.println(MoreObjects.toStringHelper("MyObject").add("x", 1).toString());
    }
}
```


# compare/compareTo
```java
package org.example.demo.guava;

import com.google.common.collect.ComparisonChain;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.lastName = "l1";
        p1.firstName = "f1";
        p1.zipCode = 1;

        Person p2 = new Person();
        p2.firstName = "f2";
        p2.lastName = "l2";
        p2.zipCode = 2;

        System.out.println(p1.compareTo(p2));
    }

    static class Person implements Comparable<Person> {
        private String lastName;
        private String firstName;
        private int zipCode;

        public int compareTo(Person that) {
            return ComparisonChain.start().compare(this.lastName, that.lastName)
                    .compare(this.firstName, that.firstName).compare(this.zipCode, that.zipCode).result();
        }
    }
}
```
