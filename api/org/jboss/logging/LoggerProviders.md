# findProvider()
查找 provider 的过程
1. 根据系统属性 org.jboss.logging.provider
1. 根据 ServiceLoader
1. 依次尝试
  1. jboss log(org.jboss.logmanager.Logger)
  1. Log4j 2.x
  1. Log4j 1.x
  1. logback(ch.qos.logback.classic.Logger), 只有在有 logback 时才使用 slf4j
  1. jdk
