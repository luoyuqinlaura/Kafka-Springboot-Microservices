# Project Overview

Kafka-producer (micro service 1)  ----> Kafka broker ----> Kafka-consumer-DB (micro service 2)



1.spring initializer

2.create seperate module and add producer configuration and create a topic

3.implementation of producer

* create the wikimediaChangeProducer

* use **event source** to read real time data(ok http), use Jackson-core and Jackson-databind dependencies to deal with the json data of https://stream.wikimedia.org/v2/stream/recentchange

* create eventHandler, inside the class there is onMessage() function, which will put message into topic; then inside the Producer class, we need to create eventhandler instance（当这个实体创建的时候，就会自动重写那些方法）

* we also need to specify the eventSource

* in the main class, call the saveMessage() method

* run the zookeeper and Kafka services, then run the program

  ```
  brew services start zookeeper
  brew services start kafka
  
  // check in the console-consumer， success！
  ```

  ![image-20240331170520273](/Users/biubiubiu/Library/Application Support/typora-user-images/image-20240331170520273.png)



4.consumer setup and config

5.consumer log the real time data from the broker

