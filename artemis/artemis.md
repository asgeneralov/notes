# Apache Artemis — создание контейнера

## Исходная команда

Изначально контейнер создавался так:

```bash
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 apache/artemis:latest-alpine
```

При такой конфигурации веб-консоль по умолчанию слушает только `localhost`, поэтому доступ с других машин (например, с ноутбука на сервер) не работает.

---

## Команда с работающей веб-консолью

```bash
docker run --detach --name artemis \
  -e EXTRA_ARGS="--http-host 0.0.0.0 --relax-jolokia" \
  -p 61616:61616 -p 8161:8161 \
  --restart unless-stopped apache/artemis:latest-alpine
```

**Веб-консоль:** http://\<host\>:8161/console/  
**Логин / пароль:** `artemis` / `artemis`

---

## Параметры для работы консоли

### `-e EXTRA_ARGS="--http-host 0.0.0.0 --relax-jolokia"`

Переменная окружения `EXTRA_ARGS` передаётся в `artemis create` при первом запуске контейнера.

| Параметр | Назначение |
|----------|------------|
| `--http-host 0.0.0.0` | Привязка HTTP к всем интерфейсам вместо `localhost`. Без этого консоль доступна только с самой машины, где запущен контейнер. |
| `--relax-jolokia` | Ослабляет ограничения CORS в Jolokia (JMX-HTTP мост для Hawt.io). Без этого при доступе с другого хоста консоль может открываться, но оставаться пустой из‑за блокировки запросов к Jolokia. |

### Остальные параметры

| Параметр | Описание |
|----------|----------|
| `--detach` | Запуск в фоне |
| `--name artemis` | Имя контейнера |
| `-p 61616:61616` | Порт брокера (AMQP, MQTT, STOMP и др.) |
| `-p 8161:8161` | Порт веб-консоли и HTTP API |
| `--restart unless-stopped` | Перезапуск контейнера при перезагрузке сервера |


---

## Создание очереди через API

Для создания очереди MIAN.TEST.QUEUE были выполнены следующие команды:

1. Создание адреса:
```bash
curl -u artemis:artemis -H "Content-Type: application/json" -X POST --data '{"type":"exec","mbean":"org.apache.activemq.artemis:broker=\"0.0.0.0\"","operation":"createAddress(java.lang.String,java.lang.String)","arguments":["MIAN.TEST.QUEUE","ANYCAST"]}' http://localhost:8161/console/jolokia/
```

2. Создание очереди:
```bash
curl -u artemis:artemis -H "Content-Type: application/json" -X POST --data '{"type":"exec","mbean":"org.apache.activemq.artemis:broker=\"0.0.0.0\"","operation":"createQueue(java.lang.String,java.lang.String,java.lang.String,boolean)","arguments":["MIAN.TEST.QUEUE","MIAN.TEST.QUEUE","ANYCAST",false]}' http://localhost:8161/console/jolokia/
```
