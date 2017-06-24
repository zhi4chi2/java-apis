```java
package org.example.demo.java;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
    }
}
```


如果注册表 `HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\Shell Folders\Desktop` 键值是 `F:\UserData\Desktop`


则
```bash
D:\jdk1.7.0_67\bin\javac org/example/demo/java/Test.java
D:\jdk1.7.0_67\bin\java org.example.demo.java.Test
```
将输出 `F:\UserData`


而
```bash
D:\jdk1.8.0_112\bin\javac org/example/demo/java/Test.java
D:\jdk1.8.0_112\bin\java org.example.demo.java.Test
```
将输出 `C:\Users\bruce` 与 USERPROFILE 环境变量一致


一般认为 JDK 8 的输出是正确的，但是这导致与 JDK8- 不兼容。


# Reference
- https://bugs.openjdk.java.net/browse/JDK-8137163
- http://bugs.java.com/view_bug.do?bug_id=4787931
- http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6519127
- https://stackoverflow.com/questions/2134338/java-user-home-is-being-set-to-userprofile-and-not-being-resolved
- http://www.timehat.com/javas-user-home-is-wrong-on-windows/