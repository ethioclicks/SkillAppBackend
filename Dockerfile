FROM openjdk
COPY target/zegulitApp-0.0.1-SNAPSHOT.jar /usr/app/zegulitapp.jar
WORKDIR /usr/app
EXPOSE 8080
VOLUME /tmp
RUN sh -c 'touch zegulitapp.jar'
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "zegulitapp.jar"]
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "ethiomekinaapp.jar"]
#how to run
#create docker file same as above
#remove all containers for safty docker rm $(docker ps -a -q)
#remove all images for safty docker rmi $(docker images -a -q)
#docker build -t zegulitapp .
#docker run -p 8080:8080 -d zegulitapp
#docker ps
#docker exec -it b7196bdf8d81 bash to see inside
#docker stop 5ba to kill the container


#create docker file same as above

#  docker build -t ethiomekina-ecr .
#  docker run -p 8080:8080 -d ethiomekina-ecr
#  docker tag ethiomekina-ecr:latest 992806411596.dkr.ecr.us-east-1.amazonaws.com/ethiomekina-ecr:latest
#  aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 992806411596.dkr.ecr.us-east-1.amazonaws.com
#  docker push 992806411596.dkr.ecr.us-east-1.amazonaws.com/ethiomekina-ecr:latest

#docker cleaning
#docker rmi -f $(docker images -a -q)
#docker stop $(docker ps -a -q)
#docker rm $(docker ps -a -q)


#aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 992806411596.dkr.ecr.us-east-1.amazonaws.com
#docker compose example https://www.javachinna.com/angular-nginx-spring-boot-mysql-docker-compose/
