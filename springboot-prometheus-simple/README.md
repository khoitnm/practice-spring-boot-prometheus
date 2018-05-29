## Reference:
+ https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-and-graf
+ http://www.java-allandsundry.com/2017/11/using-micrometer-with-spring-boot-2.html 

## Start Prometheus Server
Using the docker compose
```
cd deployment\prometheus-server
docker-compose up -d --build
```
That will start 2 containers: Prometheus and Grafana.
 
Next on, run Spring boot app!
