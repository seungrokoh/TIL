Android 면접 질문
***

### Q. 액티비티나 뷰가 아닌 프래그먼트로 했을 때의 장점은?
### Q. 뷰의 라이프사이클과 프래그먼트의 라이프사이클의 차이점은?
### Q. 리사이클러뷰가 무엇인지?
### Q. 뷰홀더 패턴이 무엇인지?
### Q. 4대 컴포넌트가 무엇이며 언제 사용되는지?
### Q. px과 dp의 차이점은 무엇이며 1dp당 몇 px인지?
### Q. sp가 무엇인지?
### Q. constraintLayout이 무엇이며 왜 사용하는지? (다른 레이아웃에 비해서 사용하는 이유?)
### Q. 코틀린을 사용할 때 자신이 생각하는 장점은 무엇인지?
### Q. 코틀린 스탠다드 함수는 무엇이 있으며 언제 사용하는지?
### Q. 메니페스트 파일이 무엇인지
### Q. 프로젝트를 어떻게 설계했는지 설명해보시오
### Q. mvp로 설계한 이유가 있는지?
### Q. 프로세스와 스레드를 안드로이드에 적용한다면 어떤 상황을 예로들 수 있을 까요?
### Q. ui 쓰레드에서 긴 작업을 하면 안된다고 했는데 어떤 이유인지??
### Q. 리사이클러뷰에서 어댑터가 무엇인지?
### Q. constraintLayout에서 left,light와 start, end 의 차이점이 무엇인지?
### Q. 명시적 인텐트와 암시적 인텐트에 대해서 설명해 보세요
### Q. DetailFragment를 보면 document를 lazy로 초기화 하고 있는데 저렇게 한 이유가 있는지??
### Q. 프래그먼트의 argument로 생성자로 값을 넘길 수 있는건 아는지? 그렇게 시도해 본 경험이 있는지??
### Q. 모든 화면을 액티비티와 그 안에 프래그먼트로 설계를 하였는데 그 이유가 있는지??
### Q. constraintLayout을 사용하면 depth가 얕아 지면서 성능이 좋아진다고 했는데 그렇게 말한 이유는?
### Q. 프로젝트를 설계하면서 어느 부분에 가장 신경을 많이 썻는지??
### Q. 이미지를 띄우는 부분에 신경을 많이 썻다고 하는데 어떤 방식으로 이를 구현 했는지?
### Q. 안드로이드에서 Queue를 사용하는 것 예를 하나 들어보세요
### Q. 리사이클러뷰를 사용하는 이유에 대해서 설명하세요 뷰를 여러개 나열하면 되는데 굳이 사용하는 이유는??
### Q. Application class가 무엇인지 설명하세요
### Q. A 액티비티에서 startActivity로 B액티비티를 호출하고 B 액티비티에서 뒤로가기 버튼을 눌렀을 때 두 액티비티에서 일어나는 생명주기에 대해 설명해보세요. (각 액티비티로 구분이 아닌 두 액티비티를 합쳐서 일어나는 과정을 통틀어 순서 설명)

![Activity LifeCycle](./images/activity_lifecycle.png)
출처 : [Google Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle)

__실행 순서__

__01. 앱 실행__

A 액티비티 : onCreate -> onStart -> onResume

__02. startActivity(Intent(A, B)) 실행__

A 액티비티 : onPause
B 액티비티 : onCreate -> onStart -> onResume
A 액티비티 : onStop

__03. B 액티비티에서 뒤로가기 버튼 클릭__

B 액티비티 : onPause
A 액티비티 : onRestart -> onStart -> onResume
B 액티비티 : onStop -> onDestroy
