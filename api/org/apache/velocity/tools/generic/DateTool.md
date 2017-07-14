```java
package org.example.demo.velocity;

import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;

public class Test {
    public static void main(String[] args) throws Exception {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("now", new Date());
        context.put("date", new DateTool());
        context.put("dateutils", DateUtils.class);
        StringWriter result = new StringWriter();

        Velocity.evaluate(context, result, "", "$date.format('yyyy-MM-dd', $dateutils.addDays($!{now}, 7))");
        // 2017-07-20
        System.out.println(result);
    }
}
```
