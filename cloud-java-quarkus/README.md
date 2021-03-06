# cloud-java-quarkus

![edge-pt-example-java-quarkus](https://i.ibb.co/gy0RTsB/java-quarkus.png "edge-pt-example-java-quarkus")

This example complements the [edge protocol example](https://github.com/PelionIoT/edge-pt-example) by providing an application that connects to Pelion Device Management and listens for all state changes happening at the building heating control system and applies some sample analytics to determine whether the building is operating efficiently. 

In particular, it applies the following rules:

- If blower heat or cool is on but temperature change occurs after 10s, then we issue a service warning.​

- If blower heat or cool is on but temperature change occurs after 20 seconds then we issue a system failure.

- If thermostat set-point is changed more than 4 times in 20 seconds then we issue an optimisation warning​.


## Getting Started

The project is written in Java with the [Quarkus framework](https://quarkus.io/)  and connects to a [Kafka broker](https://www.google.com/search?client=firefox-b-d&q=apache+kafka) where notifications coming from Pelion Device Management are stored. Please visit our ['Connector-for-Apache-Kafka'](https://github.com/PelionIoT/Connector-for-Apache-Kafka/tree/master/demo-example) documentation page for installation instructions of both a Kafka broker and the connector plugin to Pelion.

Once your Kafka installation is up, you can download the provided [binary artifacts](https://github.com/PelionIoT/edge-pt-example/releases/tag/0.1) for your architecture (linux/mac) to bootstrap the example. Simply download the to your machine where your Kafka broker is running and execute it with:

```
./edge-pt-example-cloud-app-bin-{linux or mac}
```

> The beauty of Quarkus native executables of Java Code :) 

>If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


Once the application is running, change the thermostat and ambient temperature values accordingly and notice how the rules discussed above are applied and notification messages being printed in the console.



## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/cloud-java-quarkus-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.