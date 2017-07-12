# decapitalize(String name)
将首字母小写。


但如果第一个字母和第二个字母都是大写，则保持原样。


例子
```java
package org.example.demo.java;

import java.beans.Introspector;

public class Test {
    public static void main(String[] args) {
        // fooBar
        System.out.println(Introspector.decapitalize("FooBar"));
        // x
        System.out.println(Introspector.decapitalize("X"));
        // URL
        System.out.println(Introspector.decapitalize("URL"));
    }
}
```
