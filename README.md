### Build code
1. enter project home folder "/takehome"
2. run maven command: mvn clean package

### Run app
1. enter project home folder "/takehome"
2. run java command: java -jar ./target/takehome-1.0-SNAPSHOT.jar


### Build Docker Image
1. enter project home folder "/takehome"
2. run docker command: docker build -t takehome .

### Run Docker Image
1. enter project home folder "/takehome"
2. run docker command: docker run -it -p 8080:8080 takehome


### endpoints
GET http://localhost:8080/api/countries?codes=CA,US