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
    - BATCH_JOB_EXECUTION, BATCH_JOB_INSTANCE 는 부모-자식 관계이다.
    - 성공, 실패 내역을 가지고 있다.
- BATCH_JOB_EXECUTION_PARAMS
    - BATCH_JOB_EXECUTION 이 생성될 때 입력 받은 Job Parameter를 가지고 있다.

**지정한 Batch Job만 실행하기**
```java
// yml
spring.batch.job.names: ${job.name:NONE}

// Job Parameter 부분
 --job.name=stepNextJob
// 실제 운영 환경
java -jar spring_batch.jar --job.name=simpleJob
```

**Batch Job Flow**

- Exit Status - [(링크)](https://github.com/JungwooSim/SpringBatch/blob/master/src/main/java/me/batch/job/StepNextConditionalJobConfiguration.java)**
    - Next를 통해 Step의 순서를 제어할 수 있다. 하지만 앞의 Step에서 오류가 발생하면 나머지 뒤에 있는 Step 들은 실행되지 못한다. 하지만 이를 대비해서 Spring Batch Job에서는 조건별로 Step를 사용할 수 있게 되어 있다.
    - Exit Status의 상태를 정하고, on() 메서드가 참조하여 Flow를 정할 수 있다.
- Decide - [(링크)](https://github.com/JungwooSim/SpringBatch/blob/master/src/main/java/me/batch/job/DeciderJobConfiguration.java)
    - FlowExecutionStatus 로 상태를 관리
    - Exit Status 보다 명확하게 Step들 간의 Flow를 담당하면서 다양한 분기 처리 가능

**Batch Status & Exit Status**

- Batch Status 는 Job 또는 Step의 실행 결과를 Spring에서 기록할 때 사용하는 Enum 이다.
- Exit Status 는 Step의 실행 후 상태를 얘기하고, Enum이 아니다.

**Job Parameter, Scope**

- Spring Batch는 외부 혹은 내부에서 파라미터를 받아 여러 Batch 컴포넌트에서 사용할 수 있도록 지원해준다. 이 파라미터를 **Job Parameter** 이라 한다.
- Job Parameter을 사용하기 위해서는 전용 Scope를 선언해야 하는데, **@StepScope** 와 **@JobScope** 2가지가 있다.
- 사용법은 SpEL로 선언해서 사용하면 된다.

```java
@Value("#{jobParameters[파라미터명]}")
```

**Chunk - [(공식문서)](https://docs.spring.io/spring-batch/docs/4.0.x/reference/html/index-single.html#chunkOrientedProcessing)**

- 데이터 덩어리로 작업할 때 각 커밋 사이에 처리되는 row 수를 얘기한다.
- Chunk 지향 처리는 한 번에 하나씩 데이터를 읽어 Chunk라는 덩어리를 만든 뒤, Chunk 단위로 트랜잭션을 다루는 것을 의미한다.
    - Chunk 단위로 트랜잭션을 수행하기 때문에 실패할 경우 해당 Chunk 만큼만 롤백이 된다.
