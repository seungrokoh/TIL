# WorkManager [코드랩 실습](https://codelabs.developers.google.com/codelabs/android-workmanager-kt/index.html?index=../..index)

### 수 많은 Background works 중 WorkManager
많은 Background Works 통합 Solution  



#### 단계별 적용

##### Worker - unit of work
* task를 doWork()에 정의한다.

##### WorkRequest
* 언제 어떻게 task를 실행할지 설정
* 2 type: 한 번만 실행 or 주기를 줘서 실행
* Add Constraints : task를 실행할 특정 제약조건을 추가
* tag 설정 가능 -> 내가 실행할 동일한 task를 다시 호출할 수 있음

##### Enqueue
* 이 시점에 JobScheduler 또는 Alarmmanager + Broadcast Receiver 중 선택
* Work의 실행을 보장하기 위해 Database에 저장함

##### Observing work
* work의 id로 조회를 할 수 있음
* LiveData<WorkInfo>로 반환
* id는 달라도 tag는 동일하게 부여할 수 있으므로 tag로도 조회가능

##### Chains
* 실행하려는 순서대로 workRequest에 enqueue 한다
* 작업들을 체이닝할 수 있다.
* work들을 병렬적으로 실행가능하다(디바이스의 쓰레드풀에 따라 상이할 수 있음)

##### Unique Work
* Unique한 Work에 Policy를 적용할 수 있음

##### Cancle
* id, tag, unique, chain name을 이용해 cancle 가능
