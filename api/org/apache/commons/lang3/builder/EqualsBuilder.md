两个 equals 的对象必须有同样的 hashCode ，但 hashCode 相同的对象不一定 equals


例子
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        A a1 = new A();
        a1.a1 = "a1_1";
        a1.a2 = "a2_1";

        A a2 = new A();
        a2.a1 = "a1_1";
        a2.a2 = "a2_1";

        // true
        System.out.println(a1.equals(a2));
    }

    public static class A {
        private String a1;
        private String a2;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            A rhs = (A) obj;
            return new EqualsBuilder().append(a1, rhs.a1).append(a2, rhs.a2).isEquals();
        }
    }
}
```


注意不能使用 <code>new EqualsBuilder().append(this, obj).isEquals()</code> ，因为这会导致无限循环。


reflectionEquals 例子：
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        A a1 = new A();
        a1.a1 = "a1_1";
        a1.a2 = "a2_1";

        A a2 = new A();
        a2.a1 = "a1_1";
        a2.a2 = "a2_1";

        // true
        System.out.println(a1.equals(a2));
    }

    @SuppressWarnings("unused")
    public static class A {
        private String a1;
        private String a2;

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
    }
}
```


EqualsBuilder.reflectionEquals 不需要检查 null 等情况，其会自己检查。


# 静态方法
- reflectionEquals(Object lhs, Object rhs, Collection&lt;String> excludeFields)
- reflectionEquals(Object lhs, Object rhs, String... excludeFields)
- reflectionEquals(Object lhs, Object rhs, boolean testTransients)
- reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class&lt;?> reflectUpToClass, String... excludeFields)


参数：
- lhs
- rhs
- excludeFields
- testTransients - 是否测试 transient 字段 equals ，没有这个参数则默认 false
- reflectUpToClass - superclass 直到 reflectUpToClass(包含)， null 与 java.lang.Object 等价


如果 lhs 和 rhs 的 class 不同，则比较最特殊（子类）的那个的 fields ，例如：
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;

@SuppressWarnings("unused")
public class Test {
    public static void main(String[] args) throws Exception {
        A a1 = new A();
        a1.a = "a1";

        B b = new B();
        b.a = "a1";
        b.b = "b1";

        // false
        System.out.println(a1.equals(b));
    }

    public static class A {
        protected String a;

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
    }

    public static class B extends A {
        private String b;
    }
}
```


# 构造器
- EqualsBuilder()


# 成员变量
- boolean isEquals = true


方法
- isEquals()
- reset() - 重置为 true


# appendSuper(boolean superEquals)
其实即设置 isEquals = superEquals


# append
对 Object 调用 equals 方法，对整数使用 == 比较，对浮点数比较特别，先转(Double.doubleToLongBits/Float.floatToIntBits)再比较。对数组检查长度一致且每个索引的元素都依次 equals


- append(Object lhs, Object rhs)
- append(long lhs, long rhs)
- append(int lhs, int rhs)
- append(short lhs, short rhs)
- append(char lhs, char rhs)
- append(byte lhs, byte rhs)
- append(double lhs, double rhs) - 参见 Double.doubleToLongBits
- append(float lhs, float rhs) - 参见 Float.floatToIntBits
- append(boolean lhs, boolean rhs)
- append(Object[] lhs, Object[] rhs)
- append(long[] lhs, long[] rhs)
- append(int[] lhs, int[] rhs)
- append(short[] lhs, short[] rhs)
- append(char[] lhs, char[] rhs)
- append(byte[] lhs, byte[] rhs)
- append(double[] lhs, double[] rhs)
- append(float[] lhs, float[] rhs)
- append(boolean[] lhs, boolean[] rhs)


# build()
即 isEquals() ，只是为了实现 Builder 接口的方法而已。
