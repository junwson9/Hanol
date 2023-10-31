#!/bin/bash

# .env.docker 파일 로드
if [ -f .env.docker ]; then
  export $(cat .env.docker | xargs)
fi

docker login --username $DOCKER_USERNAME --password $DOCKER_PASSWORD

# Dockerfile을 사용하여 이미지 빌드
docker build -t hanol/hanol-repo:server --platform linux/amd64 .

docker push hanol/hanol-repo:server