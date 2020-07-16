## Spring Batch

**Batch Application의 조건**

- 대용량 데이터 - 대량의 데이터를 가져오거나, 전달, 계산, 등 처리 할 수 있어야 한다.
- 자동화 - 최대한 사용자 개입 없이 실행 되어야 한다.
- 견고성 - 잘못된 데이터를 충돌/중단 없이 처리할 수 있어야 한다.
- 신뢰성 - 오류를 추적할 수 있어야 한다.
- 성능 - 지정된 시간안에 처리를 완료하거나 동시에 실행되는 어플리케이션에 방해를 주면 안된다.

**Spring Batch**

- Accenture(Batch에 관한 많은 노하우를 가지고 있음) 와 Spring Source의 공동 작업으로 만들어 졌다.
- Spring의 특성을 그대로 가져왔으므로 DI, AOP, 서비스 추상화 등 사용할 수 있다.
- Job은 하나의 배치 작업 단위를 얘기한다. Job 안에는 여러 step이 존재하고, step안에는 Tasklet, Reader & Processor & Writer 묶음이 존재한다.

    <img src="/img/1.png" width="500px">

**Spring Batch Meta date**

- 이전에 실행한 Job 내역
- 실패, 성공한 Job 내역
    - 실패했다면 어디서부터 다시 실행하면 될지
- Job에 어떤 Step이 있고, Step중 성공과 실패한것들이 어떤것이 있는지 확인
- 등...

    <img src="/img/2.png" width="500px">

H2 데이터베이스를 사용할 경우에는 Spring Boot가 직접 생성해주지만, MySQL과 같은 DB를 사용할 때는 개발자가 직접 생성해야 한다.

**Spring Batch Meta date 상세**

- BATCH_JOB_INSTANCE
    - Job Parameter에 따라 생성되는 테이블 (Job Parameter는 Spring Batch가 실행될 때 외부에서 받을 수 있는 파라미터)
    - 예를 들어, 특정 날짜를 Job Parameter로 넘기게되면 해당 날짜 데이터로 조회/가공/입력, 등의 작업 가능
    - Job Parameter에 따라 생성되기 때문에 같은 Job 이라도 Job Parameter가 같다면 기록되지 않는다.
- BATCH_JOB_EXECUTION
