包含
- low-level incremental/streaming parser and generator abstractions, 不仅用于 JSON 格式。类名中有 JSON 字样只是因为历史原因
- JSON format 的 parser / generator 的默认实现


只有 package 名带 json 的才是特定于 JSON format 的。


重要的类
- JsonFactory - 线程安全
- JsonParser - 解析
- JsonGenerator - 生成


例子
```java
package org.example.demo.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Test {
    public static void main(String[] args) throws Exception {
        JsonFactory factory = new JsonFactory();
        factory.enable(JsonParser.Feature.ALLOW_COMMENTS);

        JsonParser parser = factory.createParser("{\"hello\": \"hello world\"}");
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Expected data to start with an Object");
        }
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            parser.nextToken();
            String value = parser.getText();
            System.out.println(fieldName + ": " + value);
        }
        parser.close();
    }
}
```


# Reference
- https://github.com/FasterXML/jackson-core

