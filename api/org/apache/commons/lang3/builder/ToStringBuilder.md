例子：
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.a1 = "a1_1";
        a.a2 = "a2_1";
        // org.example.demo.commons.Test$A@7ea987ac[p1=a1_1,p2=a2_1]
        System.out.println(a);
    }

    public static class A {
        private String a1;
        private String a2;

        public String toString() {
            return new ToStringBuilder(this).append("p1", a1).append("p2", a2).toString();
        }
    }
}
```


使用反射的例子
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.a1 = "a1_1";
        a.a2 = "a2_1";
        // org.example.demo.commons.Test$A@6e0be858[a1=a1_1,a2=a2_1]
        System.out.println(a);
    }

    @SuppressWarnings("unused")
    public static class A {
        private String a1;
        private String a2;

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
```


使用反射比显式指定字段速度慢。


调用 superclass 和 delegate 的 toString 的方式参见
- appendSuper(final String superToString) - superclass
- appendToString(final String toString) - delegate


# 静态成员
- ToStringStyle defaultStyle - getDefaultStyle/setDefaultStyle


# 静态方法
- getDefaultStyle()
- setDefaultStyle(final ToStringStyle style)


## reflectionToString
- reflectionToString(final Object object)
- reflectionToString(final Object object, final ToStringStyle style)
- reflectionToString(final Object object, final ToStringStyle style, final boolean outputTransients)
- reflectionToString(T object, ToStringStyle style, boolean outputTransients, Class&lt;? super T> reflectUpToClass)


参数：
- object
- style
- outputTransients - 是否包含 transient 字段，没有这个参数则默认 false
- reflectUpToClass - superclass 直到 reflectUpToClass(包含)， null 与 java.lang.Object 等价


# 构造器
- ToStringBuilder(final Object object)
- ToStringBuilder(final Object object, final ToStringStyle style)
- ToStringBuilder(final Object object, ToStringStyle style, StringBuffer buffer)


参数
- object
- style - 如果为 null 使用 default style(getDefaultStyle())
- buffer - 如果为 null 则新建(new StringBuffer(512))


# 成员变量
- Object object - getObject()
- ToStringStyle style - getStyle()
- StringBuffer buffer - getStringBuffer()


调用 style 的方法：
- 在构造器中调用 style.appendStart
- 在 append 方法中调用 style.append
- 在 appendSuper 方法中调用 style.appendSuper
- 在 appendToString 方法中调用 style.appendToString
- 在 toString() 中调用 style.appendEnd


# append()
- append(final boolean value)
- append(final boolean[] array)
- append(final String fieldName, final boolean value)
- append(final String fieldName, final boolean[] array)
- append(final String fieldName, final boolean[] array, final boolean fullDetail)
- append(final byte value)
- append(final byte[] array)
- append(final String fieldName, final byte value)
- append(final String fieldName, final byte[] array)
- append(final String fieldName, final byte[] array, final boolean fullDetail)
- append(final char value)
- append(final char[] array)
- append(final String fieldName, final char value)
- append(final String fieldName, final char[] array)
- append(final String fieldName, final char[] array, final boolean fullDetail)
- append(final short value)
- append(final short[] array)
- append(final String fieldName, final short value)
- append(final String fieldName, final short[] array)
- append(final String fieldName, final short[] array, final boolean fullDetail)
- append(final int value)
- append(final int[] array)
- append(final String fieldName, final int value)
- append(final String fieldName, final int[] array)
- append(final String fieldName, final int[] array, final boolean fullDetail)
- append(final long value)
- append(final long[] array)
- append(final String fieldName, final long value)
- append(final String fieldName, final long[] array)
- append(final String fieldName, final long[] array, final boolean fullDetail)
- append(final float value)
- append(final float[] array)
- append(final String fieldName, final float value)
- append(final String fieldName, final float[] array)
- append(final String fieldName, final float[] array, final boolean fullDetail)
- append(final double value)
- append(final double[] array)
- append(final String fieldName, final double value)
- append(final String fieldName, final double[] array)
- append(final String fieldName, final double[] array, final boolean fullDetail)
- append(final Object obj)
- append(final Object[] array)
- append(final String fieldName, final Object obj)
- append(final String fieldName, final Object obj, final boolean fullDetail)
- append(final String fieldName, final Object[] array)
- append(final String fieldName, final Object[] array, final boolean fullDetail)


对 boolean, byte, char, short, int, long, float, double 都有五个方法


对 Object 有 6 个方法，多一个
- append(final String fieldName, final Object obj, final boolean fullDetail)


参数
- fieldName - 字段名
- value
- array
- fullDetail - true 则输出 array/object 的 detail 信息， false 则仅输出 summary ，一般是数组的长度。


# appendAsObjectToString(final Object srcObject)
对 srcObject 使用 Object.toString() 并添加到 buffer 中。


# appendSuper(final String superToString)
append superclass 的 toString 结果。这个方法假定 superclass 使用同一个 ToStringStyle 。


例子
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        AA a = new AA("a_1", "aa_1");
        // org.example.demo.commons.Test$AA@7ea987ac[aa_1,a_1]
        System.out.println(a);
    }

    public static class A {
        private String a;

        public A(String a) {
            super();
            this.a = a;
        }

        public String toString() {
            return new ToStringBuilder(this).append(a).toString();
        }
    }

    public static class AA extends A {
        private String aa;

        public AA(String a, String aa) {
            super(a);
            this.aa = aa;
        }

        public String toString() {
            return new ToStringBuilder(this).append(aa).appendSuper(super.toString()).toString();
        }
    }
}
```


如果不使用 appendSuper ，即将第 34 行的 appendSuper 改为 append ，则将输出
<syntaxhighlight lang="text">
org.example.demo.commons.Test$AA@7ea987ac[aa_1,org.example.demo.commons.Test$AA@7ea987ac[a_1]]
</syntaxhighlight>


所以 appendSuper 的作用是将内层的类名等信息去掉，参见 ToStringStyle 第 391 行。


# appendToString(final String toString)
append delegate 的 toString 结果。这个方法假定 delegate 使用同一个 ToStringStyle 。


appendToString 与 appendSuper 实际都会调用 ToStringStyle.appendToString(StringBuffer buffer, String toString) ，所以其实是一样的。


例子：
```java
package org.example.demo.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Test {
    public static void main(String[] args) throws Exception {
        AA a = new AA("a_1", "aa_1");
        // org.example.demo.commons.Test$AA@7ea987ac[aa_1,a_1]
        System.out.println(a);
    }

    public static class A {
        private String a;

        public A(String a) {
            super();
            this.a = a;
        }

        public String toString() {
            return new ToStringBuilder(this).append(a).toString();
        }
    }

    public static class AA {
        private A a;
        private String aa;

        public AA(String a, String aa) {
            this.a = new A(a);
            this.aa = aa;
        }

        public String toString() {
            return new ToStringBuilder(this).append(aa).appendToString(a.toString()).toString();
        }
    }
}
```


# toString()
返回对 object 构造的 toString 结果。


这个方法只能被调用一次！如果想得到中间结果，应该使用 getStringBuffer() 方法。


# build()
即 toString()

