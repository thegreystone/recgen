# recgen
Little repo that will hopefully in time contain a collection of code for generating useful flight recordings, 
for example for testing edge cases in OpenJDK JMC.

## Building and Running
This is how to build:

```bash
mvn clean compile assembly:single
```

This is how to run:
```bash
java -jar target\recgen-0.0.1-SNAPSHOT-jar-with-dependencies.jar <destination folder>
```

The generated jfr file(s) will be available in the folder.
