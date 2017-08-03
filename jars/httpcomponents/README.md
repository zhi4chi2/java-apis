- [httpcomponents](/jars/httpcomponents/README.md)
  - [httpclient](/jars/httpcomponents/client/README.md)


HttpComponents 包括：
- HttpCore
- HttpClient - 取代 Commons HttpClient 3.x 。 HTTP/1.1 兼容的客户端(agent)
- Asynch HttpClient - 基于 NIO 的 HTTP/1.1 兼容的客户端(agent)，基于 HttpCore NIO and HttpClient components


HttpCore 支持：
- blocking I/O 模式，基于经典的 Java IO 模式，用于数据密集、低延迟的情景
- non-blocking I/O 模式，事件驱动，基于 Java NIO 模式，用于高并发情景


# modules
HttpCore(4.4.6)
- httpcore
- httpcore-nio
- httpcore-osgi
- httpcore-ab
- httpcomponents-core - parent pom


HttpClient(4.5.3)
- httpclient-osgi
- httpclient-win
- httpclient-cache
- fluent-hc
- httpmime
- httpclient
- httpcomponents-client - parent pom


另有
- httpclient-android - 4.3.5.1


Asynch HttpClient(4.1.3)
- httpasyncclient
- httpasyncclient-cache
- httpasyncclient-osgi
- httpcomponents-asyncclient - parent pom


# Reference
- http://hc.apache.org/
- http://hc.apache.org/downloads.cgi - 下载地址
