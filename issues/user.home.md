```java
package org.example;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
    }
}
```


���ע��� `HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\Shell Folders\Desktop` ��ֵ�� `F:\UserData\Desktop`


��
```bash
D:\jdk1.7.0_67\bin\javac org/example/Test.java
D:\jdk1.7.0_67\bin\java org.example.Test
```
����� `F:\UserData`


��
```bash
D:\jdk1.8.0_112\bin\javac org/example/Test.java
D:\jdk1.8.0_112\bin\java org.example.Test
```
����� `C:\Users\bruce` �� USERPROFILE ��������һ��


һ����Ϊ JDK 8 ���������ȷ�ģ������⵼���� JDK8- �����ݡ�


# Reference
- https://bugs.openjdk.java.net/browse/JDK-8137163
- http://bugs.java.com/view_bug.do?bug_id=4787931
- http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6519127
- https://stackoverflow.com/questions/2134338/java-user-home-is-being-set-to-userprofile-and-not-being-resolved
- http://www.timehat.com/javas-user-home-is-wrong-on-windows/