# tekion-w1

run es 8.6.2 on docker (8.6.2 is supported with spring-data-elasticsearch:5.0.2)

```
docker run -d --name estest -p 9200:9200 -e "discovery.type=single-node" -e "ingest.geoip.downloader.enabled=false" -e "xpack.security.enabled=false" -e "xpack.security.transport.ssl.enabled=false" -e "xpack.security.http.ssl.enabled=false" -e "xpack.security.enrollment.enabled=false" elasticsearch:8.6.2
```
