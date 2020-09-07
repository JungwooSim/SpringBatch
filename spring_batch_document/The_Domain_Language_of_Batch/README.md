# The Domain Language of Batch

### The Domain Language of Batch

"Jobs" 과 "Steps" 와 개발자가 사용할 수 있는 ItemReader, ItemWriter 가 있다.

<img src="/spring_batch_document/The_Domain_Language_of_Batch/img/1.png" width="500px;">

위 다이어그램은 수십년 동안 사용된 배치 아키텍처의 단순화 한 것이다.

Spring Batch 는 공통적으로 사용되는 계층, 기능을 물리적 구현을 제공하고 복잡한 처리에 도움이 되도록 인프라 확장도 할 수 있다.

Job 은 1:N 관계이고 각 Step 은 하나의 ItemReader, ItemProcessor, ItemWriter 을 가진다.

JobLauncher 를 사용하여 작업을 시작하고 현재 실행중인 메타 데이터를 JobRepository 에 저장한다.

### Job

이 장에서는 일괄 작업의 개념을 설명한다.

Job 은 전체 배치 과정을 캡슐화하는 실체다.

<img src="/spring_batch_document/The_Domain_Language_of_Batch/img/2.png" width="500px;">

Spring Batch 에서 Job 은 Step 인스턴스용 컨테이너일 뿐이다.

한 흐름에서 논리적으로 함께 속하는 여러 단계를 결합하여 재시동, 등 모든 단계에 글로벌한 속성을 구성할 수 있도록 한다.

Job Configration contains

- The simple name of the job.
- Definition and ordering of Step instances.
- Whether or not the job is restartable

Spring Batch 의 SimpleJob Class 는 기본적으로 Job interface 에서 simple implementation 제공하므로 Job 위에 몇가지 표준 기능이 생성된다.

Java 기반 구성을 사용하는 경우 다음과 같이 Job 의 인스턴스화하여 빌더 컬렉션을 사용할 수 있다.

```java
@Bean
public Job footballJob() {
    return this.jobBuilderFactory.get("footballJob")
                     .start(playerLoad())
                     .next(gameLoad())
                     .next(playerSummarization())
                     .end()
                     .build();
}
```

### JobInstance

JobInstance 는 논리적 작업 실행 이다.

[Figure 2. Job Hierarchy] 다이어그램에서 'EndOfDay' Job 은 하루의 끝이날때 한번만 실행해야 된다. 이러한 경우 논리적인 JobInstance가 하루에 1번 있는 것이다. 

JobInstance 는 load 할 데이터와 전혀 관련 없다. 데이터가 어떻게 load 되는지 결정하는 것은 ItemReader 구현에 달려 있다.

### JobParameters

JobParameters 객체는 배치 작업을 시작하는데 사용되는 매개변수 집합이다.

이를 통해 JobInstance 간의 구별 할 수 도 있다.

<img src="/spring_batch_document/The_Domain_Language_of_Batch/img/3.png" width="500px;">

모든 JobInstance 는 JobParameters 를 필수로 사용할 필요는 없다.

### JobExecution

JobExecution 은 Job 을 실행하려는 단일 시도의 기술적 개념을 나타낸다.

실행은 실패 또는 성공으로 끝날수 있다. 하지만 JobInstance 는 실행이 성공적으로 완료되지 않는한 완료된 것으로 간주하지 않는다.

Job 은 작업의 정의와 실행 방법을 정의하고 JobInstance 는 실행을 함께 그룹화 한다. JobExecution 은 실행 중에 실제로 발생한 일에 대한 기본 저장소 메커니즘이다. 다음의 표와 같이 제어 및 유지 되어야 하는 속성을 포함하고 있다.

**JobExecution Properties**

Status, startTime, endTime, exitStatus, createTime, lastUpdated, executionContext, failureExceptions

### Step

Step 는 배치 작업을 독립적이고 순차적인 단계를 캡슐화 하는 도메인 객체이다.

따라서 모든 Job 은 하나 이상의 Step 로 구성된다.

Step 는 실제 배치 처리를 정의하고 제어하는데 필요한 모든 정보를 포함한다.

Job 과 마찬가지로 Step 는 고유한 JobExecution 과 StepExcution 이 있다.

<img src="/spring_batch_document/The_Domain_Language_of_Batch/img/4.png" width="500px;">

### StepExecution

JobExecution 과 유사하게 Step 가 실행될 때마다 StepExecution 이 생성된다.

그러나 이전 Step 이 실패하게 되면 실행이 지속되지 않는다.

StepExecution 는 실제로 시작될 때만 생성된다.

Step 은 StepExecution Class 의 object 로 표현된다.

다음은 StepExecution 의 속성이다.

Status, startTime, endTime, exitStatus, executionContext, readCount, writeCount, commitCount, rollbackCount, readSkipCount, processSkipCount, filterCount, writeSkipCount
