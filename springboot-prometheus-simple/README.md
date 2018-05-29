## Reference:
+ https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-and-graf
+ http://www.java-allandsundry.com/2017/11/using-micrometer-with-spring-boot-2.html 
  
## Build our Spring Boot application
```
mvn clean install -DskipTests

```

Open the browser:
+ Business Logic APIs will be exposed via the port 8080 (by default): http://localhost:8080/welcome 
+ While metrics APIs will be exposed via the port 9090 (configured in application.yml): http://localhost:9090/actuator/prometheus

## Start Prometheus Server
Using the docker compose
```
cd deployment\prometheus-server
docker-compose up -d --build
```
That will start 2 containers: Prometheus and Grafana.
 
Next on, run Spring boot app!
