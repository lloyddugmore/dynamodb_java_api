
## MetaData Service API

This is a Spring Boot application that exposes an endpoint at **api/v1/metadata**.

The data that is returned by this application is stored in a DynamoDB that is hosted on AWS.

### How to start up the application

1. The application uses maven to build and is similiar to other Spring Boot applications in the fact that it can be built and run locally using commands like
   1. mvn clean install
   2. and running the main class of the project (TvMetaDataApplication.java)

##### but

Because we are using application.properties files to hold sensitive data (DynamoDB username and password), we have chosen to encrypt the secret using a library called 'jasypt'.

Below are instructions you can use to encrpyt/decrypt and startup the application.


#### DynamoDB secret encryption
1. Note that there is a file that contains the encrypted value, here - src/main/resources/encrypted.properties
2. There is also a DynamoDBConfig class that is responsible for setting up the connection between the application server and the Dynamo DB.
3. How to encrypt (this will only be neccessary if the token used in this app has since expired and a new token needs to be created).
   1. At the root of your project run the following command 
   <pre>
        mvn jasypt:encrypt-value -Djasypt.encryptor.password={INSERT YOUR "KEY" HERE} -Djasypt.plugin.value="{INSERT PASSWORD/TOKEN HERE}"
   </pre>
4. The output of this will create something like the below 
   <pre>
    ENC(4Zq+DOCo5F0noBmYclLuY+CeJ5byGlC0OqWRkqoAkMRVF0mLDJXcTq5FaysleLOgRz+QS+lr+MnzYavuSH88lY/nnzXxooKlqaa4k+o1988=)
   </pre>
5. Paste this value into your encrypted.properties file as the value for **amazon.aws.secretkey**
6. When building your application via mvn you will need to run as follows
   <pre>
      mvn clean install -Djasypt.encryptor.password={INSERT YOUR "KEY" HERE}
   </pre>
7. To start/run the application, you will also need to add the below to your VM opts 
8. <pre>
        -Djasypt.encryptor.password={INSERT YOUR "KEY" HERE}
   </pre>
9. If you are not able to get this to work, you can simply paste the password itself into the encrypted.properties file as the value for **amazon.aws.secretkey**. But remember not to commit to version control.

### Run via docker
#### Pre-Requisites
1. Have Docker and Docker compose installed locally. See https://docs.docker.com/compose/install/, for some instructions.
2. Have a file at the root of your project called **local.env** that has a property used for the dynamoDB access token. See instructions in **sampleEnv.env.template**.

#### Instructions
1. Use the following command to build the image.
   <pre>
   docker-compose build
   </pre>
2. Use the following command to run the image.
   <pre>
   docker-compose up
   </pre>
3. Use the following command to ensure that the image is running.
   <pre>
   docker ps
   </pre>
4. Use the following command to tail the logs.
   <pre>
   docker logs -f {insert_container_id_here}
   </pre>

### Architecture diagram

![Highlevel diagram](/src/main/resources/static/metadata-service-api.drawio.png "Highlevel Diagram")

### Testing

#### VIA Postman
1. There is a postman collection in the resources folder that can be used to test the API locally.

### Swagger
1. There is a swagger UI at http://localhost:8080/swagger-ui/#/

![Swagger](/src/main/resources/static/swagger.png "Swagger Diagram")

