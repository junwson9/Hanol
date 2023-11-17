![Untitled](/uploads/2b9db6f3a40f38ba94eaaac778382f2c/Untitled.png)

<br/><br/><br/>

# About HANOL


- AI 탈모 진단 및 두피 건강 케어 서비스🌱
- 개발 기간 : 2023.10.09 ~ 2023.11.17 (6주)
<br/><br/><br/>
# Overview


- 병원에 가지 않아도 AI가 간편하게 집에서 두피 상태를 진단해 드립니다.
- 문진을 통해 사용자의 두피 타입을 확인할 수 있습니다.
- 두피 건강을 위한 데일리 루틴을 설정하고, 알림을 받을 수 있습니다.
<br/><br/><br/>
# Description


- 사용자는 IoT기기와 휴대폰 카메라 중 하나를 선택해 진단을 받습니다.
- 두피를 찍으면 그 사진을 AI가 분석하여 탈모, 홍반, 각질, 비듬, 염증, 피지 6가지 두피 지표에 대한 위험도를 4단계로 보여줍니다.
- 과거 진단 결과 및 변화 추이는 마이리포트에서 확인이 가능합니다.
- 사용자는 본인 두피 상태에 따라 데일리 루틴을 추천 받고, 원하는 루틴에 대한 알림을 받을 수 있습니다.
- 촬영 없이 간단한 문진만으로, 랜덤 포레스트 모델을 거쳐 두피 타입을 예측하는 '두피티아이' 기능이 있습니다.
<br/><br/><br/>
# Screens
### 1. AI 두피 건강 분석

<img src="/uploads/9eaa15a890b76e59e167125ce184e6e5/U_2.0.0_진단하기.png" width="270">
- 전용 IoT 기기를 통한 AI 두피 건강 진단

![IoT기기_진단](/uploads/2ca64516a958a9143e12453cb4625207/IoT기기_진단.gif)
- 휴대폰 카메라 촬영을 통한 AI 두피 건강 진단
    
![핸드폰_진단](/uploads/f73ee185d0543411827cec7d77abe8e4/핸드폰_진단.gif)
    

### 2. 진단 결과를 모아보는 마이리포트

![마이리포트](/uploads/7882e672b3048bd48711f13e114d1d3e/마이리포트.gif)
    

### 3. 두피 상태에 맞는 두피 케어 루틴 추천, 알림

![U_4.0.0_데일리___다이어리__비로그인___1_](/uploads/f18faa221570a9039b470184fbd7599d/U_4.0.0_데일리___다이어리__비로그인___1_.png)

- AI 진단 결과를 기반으로 사용자 맞춤형 두피 케어 루틴 추천
    
![루틴설정](/uploads/1834940e1e70cdafd626b6da9891d686/루틴설정.gif)
    
    

- 루틴별 알림 설정 시 원하는 시간에 Push 알림 수신
    
    [루틴 알림 설정하는 화면]
    
    [푸시 알림 수신한 화면]
    

### 4. 생활 습관으로 예측해보는 두피TI

![두피TI메인](/uploads/6d01922446fc4c9812582dbda5dc2244/두피TI메인.png)
- 생활 습관 문진을 기반으로 나의 두피 타입 예측
    
![두피ti](/uploads/5913136c4f4e00119f2e0f05e7b858fd/두피ti.gif)
<br/><br/><br/>
# Main Features


### AI

- 딥러닝 모델 학습을 통한 AI 두피 진단 서비스 제공
    - [데이터 출처](https://www.aihub.or.kr/aihubdata/data/view.do?currMenu=115&topMenu=100&dataSetSn=216)
- 두피 사진을 바탕으로 ‘탈모, 각질, 피지, 홍반, 염증, 비듬’ 6개 증상의 위험도를 4단계로 측정
- 6개 모델 평균 정확도 75%
- 모델 추론 시간 12초에서 1.5초로 단축
- OpenAI gpt-4-vision을 활용한 비 두피 사진 필터링

### IoT

- 최대 24배 배율의 현미경을 장착한 IoT 기기로 선명한 두피 사진 촬영 가능
- Raspberry Pi Zero 2 W에 fastAPI와 webSockets를 통해 실시간 카메라 스트리밍 서버를 구축
- GPIO LED모듈 사용
- IoT 기기가 없는 경우, 휴대폰 카메라 촬영 기능 제공

### Front-End

- PWA
- 반응형 디자인
- 차트 커스텀
- WebSocket을 활용해 라즈베리파이의 실시간 영상을 받음
- SSE통신으로 찍은 두피사진에 대한 진단결과를 받음

### Back-End

- Spring Batch. no-offset paging 방식의 ItemReader 구현을 통한 데일리 루틴 생성 Job 수행 속도 향상
    - 데이터 10만 건 기준, 잡 수행 시간 17.9% 향상 (6s394ms → 5s252ms)
    - 데이터 20만 건 기준, 잡 수행 시간 35% 향상 (15s180ms → 9s825ms)
- OpenID Connect(OIDC)를 활용한 카카오 소셜 로그인 및 회원가입
- JWT Refresh Token을 redis에 저장하여 빠른 엑세스 속도, 데이터 캐싱을 통한 효율성, 토큰 만료 관리 등 다양한 이점을 얻음
- 클라이언트와 SSE 통신을 사용해 효율적인 단방향 통신 구현
- RabbitMQ를 활용해 AI 두피 사진 진단 과정을 이벤트 기반 처리
- Firebase Cloud Messaging을 이용한 대량 Push 알림 발송 기능 구현

### INFRA

- gitlab runner를 이용한 CI/CD 파이프라인 구축
- SSL 인증서를 활용하여 Nginx에 HTTPS 적용하고 종단간 암호화를 구성
- Nginx reverse proxy를 활용하여 애플리케이션 서버의 직접적인 노출을 방지하고 요청을 안전하게 처리
- 시스템 아키텍처

![Group_49](/uploads/82c2b14dc691e233e33d0c5a66c9d76d/Group_49.png)
<br/><br/><br/>
# Stacks


### AI

- EfficientNet b7
- Scikit-learn(Random Forest)
- cuda12.0 & cudnn 8.9.6
- nvidia driver

### IoT

- raspberry pi zero 2 w
- Python 3.11.2
- FastAPI
- websocket

### Front-End

- React
- Recoil
- Java Script
- Type Script
- Tailwind CSS
- service-worker

### Back-End

- Java 11
- Spring Boot 2.7.1
- Spring Security
- Spring Batch
- Spring Data JPA
- Query DSL
- Gradle
- FCM
- Python 3.10
- FastAPI 0.100.0
- MariaDB 11.1.2
- Redis 7.0.12

### Infra

- Ubuntu
- Nginx
- RabbitMQ
- Docker
- AWS EC2
- AWS S3
- GitLab Runner
<br/><br/><br/>
## Tools


- JIRA
    - 매주 40시간의 Sprint를 진행하며 스케줄 관리
    - Agile한 방식으로 Sprint진행
- GitLab
    - git-flow 전략 사용
    - 코드 리뷰 후 Merge
    - Squash Merge
    - 코드 버전 관리
    - GitLab Runner로 자동배포
- Figma
    - 와이어프레임 및 디자인
- Notion
    - 회의록 보관
    - 스프린트 회고 진행
    - 컨벤션 정리
    - 기술 공유 및 이슈 정리
    - 산출물, 공통 문서 관리
<br/><br/><br/>
## Project Outputs


- [기능명세서](https://docs.google.com/spreadsheets/d/14SEDeVco1jyxY_MQKTBNZvW6g-wu6vDGmLwV3gJq0hA/edit#gid=1030871795)
- [WBS](https://docs.google.com/spreadsheets/d/14SEDeVco1jyxY_MQKTBNZvW6g-wu6vDGmLwV3gJq0hA/edit#gid=849952922)
- [Notion](https://www.notion.so/eb5ae1610ab6446ea10758a616bf12bc?pvs=21)
- [WireFrame & Design](https://www.figma.com/file/zBW2JDgqUmynq0e4aFpBeY/%EC%99%80%EC%9D%B4%EC%96%B4%ED%94%84%EB%A0%88%EC%9E%84%2F%EB%94%94%EC%9E%90%EC%9D%B8?type=design&node-id=0-1&mode=design)
<br/><br/><br/>
## Members


- [김지수](https://github.com/jis002) (BE, PM)
- [김정락](https://github.com/jlal1226) (BE, Infra, IoT)
- [김준현](https://github.com/jhhhhhj) (AI, Infra)
- [박승휘](https://github.com/hwi29) (FE)
- [송준우](https://github.com/junwson9) (FE, IoT)
- [최홍준](https://github.com/HBumzz) (FE)
