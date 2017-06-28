# 使用 Spring
```java
package org.example.demo.spring.context;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class Test {
    public static void main(String[] args) throws Exception {
        List<String> classes = getAllSubClasses(ApplicationContext.class, "org/springframework");
        classes.forEach(clazz -> System.out.println(clazz));
    }

    public static List<String> getAllSubClasses(Class<?> superClass, String basePackage) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(superClass));
        Set<BeanDefinition> components = provider.findCandidateComponents(basePackage);
        return components.stream().map(component -> component.getBeanClassName()).collect(Collectors.toList());
    }
}
```


但只列出了子类，不能列出子接口，大概可以通过 override ClassPathScanningCandidateComponentProvider.isCandidateComponent(AnnotatedBeanDefinition) 方法实现，没有测试。


# 使用 reflections
FIXME https://github.com/ronmamo/reflections


# Reference
- https://stackoverflow.com/questions/492184/how-do-you-find-all-subclasses-of-a-given-class-in-java
