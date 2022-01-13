# 장소 검색 서비스

## Quick Start

```bash
# 레포지토리 복제
git clone https://github.com/sanserif28/search.git
```

레포지토리를 복제했다면, 아래 옵션 중 하나를 선택하여 실행하시면 됩니다.

- **옵션 1)** search-service(test 버전) 만 실행 (도커 없이 springboot 서버만 실행, 단순 API 테스트용, redis, spring-cloud 연동 X)

```bash
cd ./search-service-test
./gradlew bootRun
```

- **옵션 2)** search-service, redis, spring-cloud 모두 실행 (docker-compose 필요, API 테스트 가능, 성능 향상, 가용성 향상)

```bash
# 여러 서비스 build 후 docker-compose 로 실행
./run.sh
```

- 포트별 실행되는 서버(컨테이너)
  - 8761 : discovery service (여러 서버 관리)
  - 8000 : API gateway service
  - 8080 : search service 1
  - 8081 : search service 2
  - 6739 : cache server (redis)
- 종료방법 (모든 컨테이너를 중지하고, 삭제)
  ```bash
  docker-compose down
  ```

## Test

- 장소 검색

```bash
curl -v -X GET "http://localhost:8080/v1/places" --data-urlencode "query=파스타"
```

- 검색 키워드 목록 (Top 10)

```bash
curl -v -X GET "http://localhost:8080/v1/keywords"
```

- 테스트를 위한 데이터를 기본 등록(insert)해 놓았습니다.

---

## 1. 정렬 순서(장소 객체의 동등 기준)

전처리

- 들어온 모든 인풋은 태그 제외 (네이버는 태그가 들어옴)
- 공백 제외

장소의 동등성 판단 기준

- 동등성 판단 기준이 되는 `identity` 필드를 만들어 `equals()`, `hashcode()` 메소드 오버라이딩
- 장소는 ‘주소’가 같으면 같다고 판단
- 도로명주소는 누락된 데이터가 몇몇 보이므로 지번주소를 사용
- 지번주소가 누락되었다면, 장소이름으로 동등성을 비교
- 지번주소 중 (서울특별시, 서울시, 서울) 를 같은 것으로 취급하기 위해 첫번째 행정구역에서 “시”, “도”, “특별”, “광역”, “자치”, “청”, “라”, “상” 을 제외하여 비교

---

## 2. 동시성 이슈 고려하여 설계, 구현 (키워드 검색 수 동시성 처리)

낙관적락(Optimistic Lock) 사용

- version 컬럼을 추가하여 버전을 관리하여 DB lock 의 부담을 줄이면서 동시성 이슈 해결
- 예외가 발생하면 쓰레드 수만큼 재시도 (여기서는 10번으로 설정)
- 재시도 숫자는 성능을 고려하여 적당한 수를 조절
- 재시도 횟수 초과로 데이터 업데이트는 일부 유실될 수 있으나, 키워드 검색 카운트의 정합성과 동시성 성능 향상의 trade-off 중 동시성 성능 향상을 선택

---

## 3. 외부 API 제공자의 다양한 장애 상황 고려

- connect_timeout 3초, read_timeout 7초 설정하여 없으면 empty List 를 반환하도록 처리
- 요청값을 잘 못 전달하는 오류(Bad Request)를 사전에 차단할 수 있도록 query 변수에 검증조건 추가 (validation)
- 에러 핸들링을 통해 클라이언트에게 구체적인 메시지와 에러타입, 상태코드, 에러 필드를 제공하여 빠른 조치가 가능하도록 구현

---

## 4. 대용량 트래픽 처리를 위한 반응성, 확장성, 가용성 향상

반응성

- 특정 메소드 호출 시 Redis 서버에 캐시를 적용하여 성능 향상 (검색 API 호출: 219ms → 15ms)
- look aside cache 방식 적용
- 캐시만료기간 TTL 7일로 설정 (검색 API 결과가 자주 바뀌지 않을 것이라고 가정)

가용성, 확장성

- API gateway (Spring Cloud Gateway) 추가하여 트래픽 분산 (라운드 로빈)
- search-service 를 수평적 확장하여 대용량 트래픽 발생시 가용성, 확장성 향상
- 현재 연결된 서비스 확인을 위한 대시보드로 eureka 서버 실행 및 클라이언트 연결

---

## 5. 지속적 유지 보수, 확장에 용이한 아키텍처

- 객체 지향의 SRP, OCP, DIP 원칙을 지키며 변경에 유연한 구조 적용
- 도메인 모델 패턴으로 해당 도메인과 관련된 로직은 도메인 안에 캡슐화
- 상위 계층은 하위 계층에 의존하지 않고, 공동의 추상화된 인터페이스에 의존하여 쉽게 모듈을 변경할 수 있는 구조
  - PlaceSearchService : 검색 결과를 제공하는 인터페이스 (검색 결과에 대한 다양한 조합을 구현 가능)
  - SearchProvider (ex. 새로운 구글 검색 서비스를 유연하게 추가 가능)
  - PlaceSortPolicy : 정렬에 관한 다양한 정책 적용 가능
- 테스트코드 : 신뢰할 수 있고 견고한 코드 작성 가능
