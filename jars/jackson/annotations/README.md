The only annotations not included are ones that require dependency to the Databind package.


1.x 版本的 jackson-annotations 在 jackson-core 中。


Full Listing of Jackson Annotations 参见 https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations


# Usage, general
Jackson 支持
- Mix-in annotations - 在第三方类上关联注解，但不修改 class 类。参见 https://github.com/FasterXML/jackson-annotations/wiki/Mixin-Annotations
- 支持继承 - 包括类、方法和字段上的注解的继承。


注解可以加在 field, method (getter/setter), class 上。


# Usage, simple
- [Annotations for renaming properties](/api/com/fasterxml/jackson/annotation/JsonProperty.md)
- [Annotations for Ignoring properties](/api/com/fasterxml/jackson/annotation/JsonIgnore.md)
- [Annotations for Ignoring properties](/api/com/fasterxml/jackson/annotation/JsonIgnoreProperties.md)
- [Annotations for choosing more specific types](/api/com/fasterxml/jackson/annotation/JsonSerialize.md), [Annotations for choosing less specific types](/api/com/fasterxml/jackson/annotation/JsonDeserialize.md)


# Usage, intermediate
- [Using constructors or factory methods](/api/com/fasterxml/jackson/annotation/JsonCreator.md)
- [Handling polymorphic types](/api/com/fasterxml/jackson/annotation/JsonTypeInfo.md)
- [Changing property auto-detection](/api/com/fasterxml/jackson/annotation/JsonAutoDetect.md)


# Further reading
Jackson 2 可以通过 jackson-legacy-introspector(https://github.com/Laures/jackson-legacy-introspector) 使用 Jackson 1 annotations 。


参见
- http://wiki.fasterxml.com/JacksonHome
- http://wiki.fasterxml.com/JacksonAnnotations
- https://github.com/FasterXML/jackson-annotations/wiki
- https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations


# Reference
- https://github.com/FasterXML/jackson-annotations
