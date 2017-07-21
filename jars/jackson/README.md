版本
- 2.7 需要 JDK1.7+
- 2.4 需要 JDK1.6+
- 2.3 需要 JDK1.5+


# modules
- jackson-annotations
- jackson-core
- jackson-databind
- jackson-dataformat-avro
- jackson-dataformat-cbor
- jackson-dataformat-csv
- jackson-dataformat-ion
- jackson-dataformat-properties
- jackson-dataformat-protobuf
- jackson-dataformat-smile
- jackson-dataformat-xml
- jackson-dataformat-yaml
- jackson-datatype-guava
- jackson-datatype-hibernate3
- jackson-datatype-hibernate4
- jackson-datatype-hibernate5
- jackson-datatype-hppc
- jackson-datatype-jaxrs
- jackson-datatype-joda
- jackson-datatype-jdk8
- jackson-datatype-json-org
- jackson-datatype-jsr310
- jackson-datatype-jsr353
- jackson-datatype-pcollections
- jackson-jaxrs-base
- jackson-jaxrs-cbor-provider
- jackson-jaxrs-json-provider
- jackson-jaxrs-smile-provider
- jackson-jaxrs-xml-provider
- jackson-jaxrs-yaml-provider
- jackson-jr-all
- jackson-jr-objects
- jackson-jr-retrofit2
- jackson-jr-stree
- jackson-module-afterburner
- jackson-module-guice
- jackson-module-jaxb-annotations
- jackson-module-jsonSchema
- jackson-module-kotlin
- jackson-module-mrbean
- jackson-module-osgi
- jackson-module-parameter-names
- jackson-module-paranamer
- jackson-module-scala_2.10
- jackson-module-scala_2.11
- jackson-module-scala_2.12


# 术语
- data format - 有 json, avro, bson(https://github.com/michel-kraemer/bson4jackson), cbor, csv, ion, smile, properties, protobuf, xml, yaml
- data type - 是 jackson 插件(modules)，通过 ObjectMapper.registerModule() 注册。注册后 ObjectMapper / ObjectReader / ObjectWriter 可以读写这些 type
- streaming - 即 jackson-core


handler types 包括
- parser - 反序列化
- generator - 序列化


- databind - 使用 POJO 序列化/反序列化 json ，包括使用 Map
- tree-model - 不使用 POJO 而是使用 JsonNode
- incremental model / streaming model - 最底层的方式


# Reference
- http://wiki.fasterxml.com/JacksonHome - 官网
- https://github.com/FasterXML/jackson - 主页
- https://github.com/FasterXML/jackson-docs - 文档
- https://github.com/FasterXML/jackson-core
- https://github.com/FasterXML/jackson-annotations
- https://github.com/FasterXML/jackson-databind
