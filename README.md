# Toy Project: Social (소셜 기능 토이 프로젝트)

이 프로젝트는 Spring Boot를 사용하여 간단한 소셜 네트워킹 기능의 백엔드를 구현하는 토이 프로젝트입니다.

## 🚀 주요 기능

- 사용자 회원가입 및 로그인
- 게시글 작성, 조회, 수정, 삭제 (CRUD)
- 게시글 좋아요 기능
- (추가 예정 기능)

## 🛠️ 사용 기술

- **Backend**: Java 17, Spring Boot 3.x
- **Database**: H2 (개발용), MySQL (운영용)
- **Build Tool**: Gradle
- **API Test**: Postman

## ⚙️ 실행 방법

1.  **프로젝트 클론**
    ```sh
    git clone https://github.com/your-username/toy-project-social.git
    cd toy-project-social
    ```

2.  **프로젝트 빌드**
    ```sh
    ./gradlew build
    ```

3.  **애플리케이션 실행**
    ```sh
    java -jar build/libs/toy-project-social-0.0.1-SNAPSHOT.jar
    ```

애플리케이션은 `http://localhost:8080` 에서 실행됩니다.