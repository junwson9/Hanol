#!/bin/bash

# .env.docker 파일 로드
if [ -f .env.docker ]; then
  export $(cat .env.docker | xargs)
fi

# Docker에 로그인
docker login --username $DOCKER_USERNAME --password $DOCKER_PASSWORD

# Dockerfile을 사용하여 이미지 빌드
docker build -t hanol/hanol-repo:fe --platform linux/amd64 .

# 빌드된 이미지를 Docker Hub에 푸시
docker push hanol/hanol-repo:fe
