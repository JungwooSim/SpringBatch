### Spring Batch Introduction(<a href="https://docs.spring.io/spring-batch/docs/4.2.x/reference/html/spring-batch-intro.html#spring-batch-intro" target="_blank">링크</a>)

비즈니스 운영을 수행하기 위해 대량 처리를 필요로 한다.

1. 사용자와 상호작용 없이 복잡한 대량의 정보 처리(다양한 조건)
2. 정기적인 반복작업

Spring Batch 는 Spring Framework(POJO) 특성을 기반으로 구축되어있어 개발자들이 쉽게 접근하고 활용할 수 있도록 해준다.

Spring Batch 는 scheduling framework(Quartz, Tivoli, Control-M) 가 아니다. scheduling framework 와 연계하여 작업하는 것을 목적으로 한다.

Spring Batch 는 processing large volumes of records, including logging/tracing, transaction management, job processing statistics, job restart, skip, and resource management 에 사용된다.

또, 복잡한 대용량 데이터를 배치 작업하는데 고도화 되어 있다.

### Background

배치 프로그램에 노하우가 있던 Pivotal, Accenture 회사가 개발하고 이를 Spring Batch에 기부하였다.

이들의 모든 사용자가 일관되게 활용할 수 있도록 표준화된 Batch Application을 개발하는 것을 목적으로 하였다.

### Usage Scenarios

전형적인 batch 프로그램은 데이터베이스, 파일을 읽고 어떠한 방식으로 처리 후 다시 데이터를 쓰는데 이러한 작업을 Spring Batch 는 상호작용 없이 하나의 트랜잭션 단위로 처리할 수 있다.

Business Scenarios

- Commit batch process periodically - 주기적인 배치 프로세스 commit
- Concurrent batch processing: parallel processing of a job - 동시 일괄 처리 : 병렬 처리
- Staged, enterprise message-driven processing -
- Massively parallel batch processing
- Manual or scheduled restart after failure
- Sequential processing of dependent steps (with extensions toworkflow-driven batches)
- Partial processing: skip records (for example, on rollback)
- Whole-batch transaction, for cases with a small batch size or existing stored procedures/scripts

Technical Objectives (기술 목표)

- Batch developers use the Spring programming model: Concentrate on business logic and let the framework take care of infrastructure.
비즈니스 로직에 집중하고 framework 가 관리하도록
- Clear separation of concerns between the infrastructure, the batch execution environment, and the batch application.
- Provide common, core execution services as interfaces that all projects can implement.
- Provide simple and default implementations of the core execution interfaces that can be used 'out of the box'.
- Easy to configure, customize, and extend services, by leveraging the spring framework in all layers.
- All existing core services should be easy to replace or extend, without any impact to the infrastructure layer.
인프라에 영향이 없어야하며 쉽게 교체하거나 확장가능해야 한다.
- Provide a simple deployment model, with the architecture JARs completely separate from the application, built using Maven.
빌드 된 어플리케이션과 독립적으로 베포

### Spring Batch Architecture

<img src="/spring_batch_document/Spring_Batch_Introduction/img/1.png" width="500px;">

1. Application : Spring Batch 를 사용하기 위한 개발자 코드가 있는 영역
2. Batch Cord : Batch 작업을 시작과 제어하는 핵심 런타임 클래스가 있는 영역 (JobLauncher, Job, Step)
3. Batch Infrastructure : This infrastructure contains common readers and writers and services (such as the RetryTemplate), which are used both by application developers(readers and writers, such as ItemReader and ItemWriter) and the core framework itself (retry, which is its own library).
