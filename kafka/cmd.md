Create a topic:
```shell
kafka-topics --create --topic order-in-topic --partitions 2 --bootstrap-server 172.16.0.4:29092,172.16.0.4:39092 --replication-factor 2
```

Consumer topic:
```shell
kafka-console-consumer --topic test1 --from-beginning --bootstrap-server 172.16.0.4:29092,172.16.0.4:39092
```

Produce topic:
```shell
kafka-console-producer --topic test1 --bootstrap-server 172.16.0.4:29092,172.16.0.4:39092
```

Consumer Topic with key:
```shell
kafka-console-consumer --topic test1 --from-beginning --bootstrap-server 172.30.16.1:29092,172.30.16.1:39092 --property "key.separator:-" --property "parse.key=true"
```

Producing Topic with Key:
```shell
kafka-console-producer --topic test1 --bootstrap-server 172.30.16.1:29092,172.30.16.1:39092 --property "key.separator:-" --property "parse.key=true"
```

Create Consumer Group:
```shell
kafka-consumer-group --bootstrap-server 172.30.16.1:29092,172.30.16.1:39092 --list
```

Consumer consuming with particular group
```shell
kafka-console-consumer --topic test1 --from-beginning --bootstrap-server 172.30.16.1:29092,172.30.16.1:39092 --group <group_name>
```

