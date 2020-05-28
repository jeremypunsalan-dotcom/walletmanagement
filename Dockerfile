FROM openjdk:8
ADD target/walletmanagement.jar walletmanagement.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "walletmanagement.jar"]