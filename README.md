The practice with Spring Boot + Prometheus

May also has Grafana UI which provides Dashboard UI for Metrics

TODO:
+ Counts per second/minute/hour: https://stackoverflow.com/questions/26038298/what-does-minute-rates-of-both-timer-and-meter-metrics-indicates, https://reflectoring.io/monitoring-error-rate-spring-boot/
+ Running time for the whole flow with async requests: https://www.nurkiewicz.com/2018/01/monitoring-and-measuring-reactive.html (by create (lazily) a new Context object)
+ How many 200-like and 500-like response occurred: https://touk.pl/blog/2018/03/05/spring-boot-2-0-http-request-metrics-with-micrometer/
+ Response time statistics: mean, median, percentiles
+ SLA (view slide [3] to understand the concept): https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector

Additional references:
   1. Some good codes to use Metrics: https://www.nurkiewicz.com/2018/01/monitoring-and-measuring-reactive.html
   2. https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector
   3. Slide with very good and short explanation about Metric Concepts (Histogram, SLA...): https://www.slideshare.net/makingx/spring-boot-actuator-20-micrometer 
My Personal Document (DRAFT): https://drive.google.com/open?id=16DUc5KDOgY9bEwXOLhkVrHtKykAfspkHkxGEz3AFKik

