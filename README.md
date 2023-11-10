<p align="center">
  <img width="500" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/54495eaa-9977-4487-96ef-a4221f3c6aa3" />
</p>

<p align="center">
   <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=OpenJDK&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/JPA-59666C?style=flat&logo=hibernate&logoColor=white"/>
  <img src="https://img.shields.io/badge/AWS-232F3E?style=flat&logo=amazonaws&logoColor=white"/>
  <br>
  <img src="https://img.shields.io/badge/MariaDB-003545?style=flat&logo=mariadb&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white"/>
</p>
    
&nbsp;

# 📌 Intro
📤 **we:vitation**은 위밋 we:meet의 **팀원 초대 기능** 팀원 초대 기능에 대한 사용자의 피드백을 받기 위해 개발하였습니다.

앱 설치 후 팀 가입, 웹 링크를 통한 팀 가입 2가지 방식에 대한 **A/B 테스트**를 진행했고 팀 결성에 소요되는 시간과 사용자 피드백을 수용하여 팀가입 절차를 웹 링크를 통한 팀가입 방식으로 변경하였습니다.

본 프로젝트의 팀원 초대 기능은 다음의 4단계로 이루어집니다.
1. 휴대폰 인증
2. 기본 정보 입력
3. 대학생 인증
4. 프로필 사진 등록

위 과정을 모두 마치면 링크를 전달한 사용자의 팀원으로 함께하게 됩니다.

&nbsp;

## 📌 화면 구성
<div align="center">
  <img width="1268" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/deaf1215-10b3-456a-bda8-c67ec95b8372">
</div>

&nbsp;

## 📌 테스트 범위
<div align="center">
  <img width="1268" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/1cbadee4-7fff-4714-848e-dfe34bdef4ec">
</div>

&nbsp;

## 휴대폰 인증
<div align="center">
  <img width="900" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/9d2bc077-97ab-431b-ac13-3264fccd4fa0">
</div>

## 이메일 인증
<div align="center">
  <img width="900" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/2f7f4d20-6008-4713-bd8b-453cc8565e43">
</div>

## 프로필 사진 등록
- 사진 등록 API의 이미지 리사이징 및 블러 처리 작업에 많은 시간이 소요었기 때문에 AWS의 메시지 큐 서비스인 SQS + Lambda 조합 사용
<div align="center">
  <img width="900" alt="image" src="https://github.com/SWM-E2I/we-vitation/assets/99247279/f22785c7-0b09-4e6e-b707-009b05e01ec8">
</div>

### 결과
- 이행률 : 65%
- 팀 활성화 비율 : 40%
- 등록 절차 병목 구간 : 대학 인증, 사진 등록
- 사용자 피드백
  - 대학 인증 부분에서 귀찮음을 느껴, 나중에 진행하려고 마음먹게 되었어요
  - 어떤 사진을 등록할지 고민하는데 많은 시간이 들었어요
  - 팀원들의 인증을 기다리는 과정이 답답하게 느껴졌어요
- ⛔️ ***팀원 초대 기능 자체에 대한 변화 필요***
