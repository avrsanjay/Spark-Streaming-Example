# Spark-Streaming-Example
##### This is a sample project to consume messages from Kafka using Spark Streaming and landing it onto HBase for performing stateful transformations
* After you pull this code into your IDE, wait for it to download all the necesasry dependencys and click **Maven Install** to generate the Uber jar with dependencies nested under the **target** folder.
* **Copy** the jar onto you machine and run the following command

   <code>`spark-submit --class com.cloudwick.streaming.spark.SparkConsumer spark-0.0.1-SNAPSHOT-jar-with-dependencies.jar localhost:2181 Spark-2-Hbase stock_topic stockdata 2`</code>
   
* In the above command you pass **five** arguments apart from specifying your class file `SparkConsumer` and Uber jar
  * **_Zookeeper Quorum_**
    * Give your Zookeeper Quorum along with it's port (default: 2181)
  * **_App Name_**
    * you can specify App name for you Streaming application to check it's status from the WebUI
  * **_Topic Name_**
    * Specify the topic name you want to subscribe from Kafka
    * Note: For now I added functionality to subscribe to single Kafka topic at a time
  * **_HBase Table Name_**
    * Specify the HBase table name you want to push and pull your data 
  * **_Number of Threads_**
    * Until functionality to subscribe to multiple topics has been added you need not worry about this 

* Important files in the project
  * `/src/main/scala/com.cloudwick.streaming.spark/SparkConsumer.scala`
    * This is the main class
  * `/src/main/scala/com.cloudwick.streaming.spark/StreamingLogger.scala`
    * This is class to set you logging levels for the App
  * `pom.xml`
    * If anymore needed, add you dependencies here

* For ease, make a bash script for the spark-submit command
