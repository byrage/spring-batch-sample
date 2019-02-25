- @JobScope, @StepScope 동작 방식
- jobParameter 세팅 방식
- jobBuilderFactory, stepBuilderFactory 내부에서 job을 가져오는 방식
~~~java
@Bean
public Job stepNextJob() {
        return jobBuilderFactory.get("stepNextJob") // ??
}

@Bean
public Step step1() {
    return stepBuilderFactory.get("step1") // ??
}
~~~
- step 분기 직접 구현해보기