# AOT Cache
[Run Into the New Year with Java’s Ahead-of-Time Cache Optimizations](https://inside.java/2026/01/09/run-aot-cache/)


```
# Training Run + Assembly Phase
java -XX:AOTCacheOutput=app.aot \
     -cp app.jar com.example.App ...
     
# Deployment Run
java -XX:AOTCache=app.aot -cp app.jar com.example.App ...
```