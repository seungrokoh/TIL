# __Jetpack Paging [코드랩 실습](https://codelabs.developers.google.com/codelabs/android-paging/index.html)__

# Jetpack Paging Library
한 번에 필요한 데이터 덩어리(chunck)만 로드(load)하고 표시(display)하는 데 도움을 주는 라이브러리.  

RecyclerView, LiveData, RxJava, Room과 결합되어 사용할 수 있음.

# __주요 컴포넌트__
Paging Library의 주요 컴포넌으로 DataSource, PagedList, PagedListAdapter가 있다.

## __DataSource__
데이터의 **SnapShot**을 로딩하기 위한 기본 클래스이며 총 3가지의 타입을 가지고 있다.

* __PageKeyedDataSource__
페이지의 key 값을 기반으로 페이징을 수행하며 Next/PreVious 필드를 가지고 있는 경우 이용.

* __ItemKeydDataSource__
페이지 아이템의 key 값을 기반으로 페이징을 수행하며 처음/마지막 아이템의 key 값을 이용

* __PositionalDataSource__
페이지 번호나 offset을 이용해서 페이징을 수행

3가지 타입은 서버에 따라 선택하면 된다.

## __PagedList__

DataSource로부터 데이터를 chunck(pages)로 로드하는 Collection. 또한 데이터의 변경이 일어나면 UI에 제공하는 역할

* __LivePagedListBuilder__
PagedList를 생성하는 빌더 : LiveData<PagedList<Repo>>

## __PagedListAdapter__
PagedLIst를 이용한 Recyclerview.Adapter  

DiffUtil을 통해 비교 후 값이 변한 아이템들만 변경. 비교 연산은 별도의 background 스레드에서 실행되며 결과물은 main 스레드에서 UI 작업 진행

# __References__
* [Android Paging codelab](https://codelabs.developers.google.com/codelabs/android-paging/index.html#2)
* I/O '19 Extended in Korea Android Seoul Codelab'
* [codechacha님 블로그](https://codechacha.com/ko/android-jetpack-paging/)
* [Paging Library, 그것이 쓰고싶다 - 한로니님 블로그](https://medium.com/@jungil.han/paging-library-%EA%B7%B8%EA%B2%83%EC%9D%B4-%EC%93%B0%EA%B3%A0%EC%8B%B6%EB%8B%A4-bc2ab4d27b87)
* [[안드로이드] Paging Library 적용기 - 장범석님블로그](http://dktfrmaster.blogspot.com/2018/11/paging-library.html)
* [Android Paging Library - 이승현님 블로그](https://brunch.co.kr/@oemilk/211)
* [Android Paging Library 분석 - Shim Myungpyo님 미디엄](https://medium.com/@myungpyo/android-paging-library-%EB%B6%84%EC%84%9D-ddff2662edac)
