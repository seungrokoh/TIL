# __면접 예상 질문__

면접 예상 질문과 답을 정리하는 공간입니다.
***

### __01. 객체지향 프로그래밍이란?__

객체지향 프로그래밍이란 __컴퓨터 중심적인 프로그래밍 방식이 아닌 사물 중심적 프로그래밍 패러다임을 말합니다.__ 즉, 현실 세계를 프로그래밍으로 옮겨와 프로그래밍 하는 것을 의미합니다. __모든 사물을 객체로 바라보고 애플리케이션에서 필요한 부분을 추출해 객체화 시킵니다.__ 여기서 불필요한 부분을 제거하는 것을 __추상화라고 합니다.__

### __02. Class란?__

클래스란, __객체의 타입을 구현하기 위한 프로그래밍 언어에서 제공하는 구현 메커니즘__ 입니다. 일반적으로 객체를 만들기 위한 __설계 도면 이라 합니다.__ 객체를 분류하는 기준은 타입이며, __타입을 나누는 기준은 객체의 행동 입니다.__ 객체를 분류하기 위해 타입을 결정하고 프로그래밍 언어를 이용해 타입을 구현할 수 있는 방법이 클래스 입니다.

### __03. 오버로딩과 오버라이딩이란?__

먼저 오버로딩인란, __함수명은 동일하지만 파라미터의 개수나 타입을 다르게 선언하는 것을 의미합니다.__ java의 println함수나 String.ValueOf() 등을 예로 들 수 있습니다.  
오버라이딩이란, __상속에서 사용되는 말로 부모 클래스의 함수를 재구현 하는 것을 의미합니다.__ Object클래스의 toString(), equals() 등을 재구현 하는 것을 예로 들 수 있습니다.

### __04. 상속이란?__

상속이란 __기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것을 말합니다.__ 상속을 통해 클래스를 작성하면 보다 적은 양의 코드로 클래스를 구현할 수 있습니다. 하지만 __어떤 클래스를 상속한다는 것은 해당 클래스에 의존한다는 뜻이므로 의존하는 클래스가 변경이 일어난다면 영향을 받을 수 있다는 단점이 있습니다.__

### __05. 캡슐화란?__

캡슐화란, __상태를 행동 뒤로 숨기는 것을 의미합니다.__ 두 객체의 메시지 전송을 예로 들면 __메시지 송신자는 메시지 수신자가 내부적으로 어떻게 동작하는지 전혀 몰라야 합니다.__ 단지 어떠한 행동을 한다고만 알고 있지 내부 상태가 변화하는 것에 대해서는 모릅니다. __캡슐화를 설명할 때 항상 따라오는 것은 공용 인터페이스에 관하여 얘기를 합니다.__ 외부에 제공하는 행동을 __공용 인터페이스로 선언을 하고__ 내부 구현은 감추는 것을 의미합니다.

### __06. JVM에 구조에 대해 설명하세요.__

JVM은 Java Virtual Machine의 약자로 스택 기반의 가상머신 입니다. Java와 OS사이의 중개자 역할을 수행하며 Java가 OS애 구애받지 않고 동작할 수 있게 만들어 줍니다. 그리고 가장 중요한 __메모리 관리 및 Garbege Collection을 수행합니다.__  
JVM은 프로그램을 수행하는 필요한 메모리를 할당 받고 __용도에 따라 Method Area, Call Stack, Heap로 나뉘어 집니다.__ 각 영역의 주요 기능은 다음과 같습니다.

* __Method Area__
    프로그램 실행 중 어떤 클래스가 사용되면, 해당 클래스에 대한 정보를 저장하는 공간입니다. 또한 클래스 변수(static)도 이곳에 저장됩니다.
* __Call Stack__
    프로그램 실행중 메서드에 대한 정보가 저장되는 공간입니다. 메서드가 호출되면 필요한 공간을 Call Stack에 할당 받으며 메서드가 실행되는 동안 지역변수 및 매개변수들과 연산의 중간 값 등을 저장합니다. 그리고 작업을 마치면 해당 공간을 반환되어 비어집니다.
* __Heap__
    프로그램 실행 중 인스턴스가 생성되는 공간입니다. 즉, new 연산자를 통해 만들어진 인스턴스 변수들이 저장됩니다.

### __07. Abstract Class와 Interface에 대해서 설명하세요.__

Abstract Class란 추상 메서드를 __하나 이상 가지고 있는 클래스를 말합니다.__ 미완성 설계도며 미완성이기 때문에 객체를 생성할 수 없습니다. 일반 클래스와 같이 extends를 이용하여 상속이 가능하며 일반 클래스를 상속하였을 경우 모든 함수를 오버라이딩 해야하는 것은 강제가 아니지만 __필요 함수를 강제 구현해야 한다면 추상 클래스를 사용할 수 있습니다.__

Interface란 오직 __추상 메서드와 상수만을 가질 수 있으며__ implements를 통해 상속받아 __구현해야 합니다.__ Interface 또한 미완성이기 때문에 객체를 생성할 수 없으며 __서로 다른 클래스에 공통점을 줄 수 있습니다.__ 보통 Interface를 설명할 때 __다형성과 연관지어 설명합니다.__ 또한 클래스와 클래스간에 직접적인 연관이 아닌 __간접적으로 관계를 맺기 때문에 유연한 프로그래밍이 가능합니다.__

### __08. 프로세스와 쓰레드에 대해서 설명하세요.__

프로세스란, __컴퓨터에서 실행되고 있는 프로그램을 의미합니다.__ 메모리에 올라와 실행되고 있는 프로그램의 인스턴스(독립적인 변수)이며 운영체제로 부터 자원을 할당 받는 작업의 단위입니다. 프로세스는 code, data, stack, heap의 구조로 이루어져 있으며 __기본적으로 한 프로세스 당 최소 1개의 쓰레드(메인 쓰레드)를 가지고 있습니다.__
쓰레드란, __프로세스 내에서 실행되는 여러 흐름의 단위를 의미합니다.__ 쓰레드는 프로세스가 할당 받은 자원을 사용하며 __각 스레드는 stack만 따로 할당받고 code, data, heap 영역은 공유합니다.__

***
# Android 예상 질문

### __01. Intent와 Bundle의 차이점에 대해서 설명하세요.__
Intent는 안드로이드 구성요소(컴포넌트) 간의 통신을 위한 메세지 객체입니다. 보통 액티비티 및 서비스를 시작할 때, 브로드캐스트를 전달할 때 사용하며 특정 값을 넘겨줘야 할 경우 그 값은 Bundle에 감싸져 전달됩니다. 만약 두 Activity간 데이터를 전달시 putExtra로 String 값을 넘겨주었을 때 그 값은 Bundle로 감싸져 넘겨지게 됩니다.

Intent는 택배 기사, Bundle은 택배 상자라고 생각하고 있습니다.

### __02. RecyclerView와 ListView의 차이점__

둘 다 리스트를 표현하기 위해 사용되며 먼저 ListView는 간단한 리스트(ex : 텍스트 등)을 쉽게 보여줄 수 있습니다. ViewHolder패턴을 강제하지 않으며 성능저하가 일어날 수 있습니다.  
RecyclerView는 ViewHolder패턴을 강제하며 View를 재사용하는데 용이합니다. 모든 뷰를 다 그리지 않고 몇개만 그린 다음 사용되지 않을 뷰를 찾아내 재사용합니다. 또한 커스터마이징하는데 쉬우며 뷰를 어떻게 그릴지(LayoutManager)정의하기 쉬우며 애니메이션도 쉽게 추가할 수 있습니다.  
여기서 ViewHolder패턴을 사용하는 이유는 findViewById의 사용 값이 너무 고비용이기 때문에 ViewHolder패턴을 사용합니다.
