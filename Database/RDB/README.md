# __관계형 데이터 모델링(RDB)__

**업무파악** -> **개념적 데이터 모델링** -> **논리적 데이터 모델링** -> **물리적 데이터 모델링**

***

# __업무 파악__
어떤 것을 꿈꾸고 있는지, 어떤 결과물을 만들어내고 싶은지를 파악하는 단계 (기획서 또는 UI)
* 원하는 결과물을 함께 고민하고 그려가며 UI를 설계해본다.
* 서로 고민하며 원하는 결과물을 구체화시킬 수 있다.

__@TODO__
__원하는 결과물을 기획하기__
[ovenapp](https://ovenapp.io/)을 이용해 결과물을 만들어보기

***

# __개념적 데이터 모델링__
내가 하고자 하는 일이 어떠한 개념들을 가지고 있고 상호작용하고 있는지 생각하는 단계  
이 과정에서 ER-다이어그램을 뽑아낼 수 있다.

* 현실에서 개념을 추출하는 일종의 **필터**를 제공한다.
* 개념에 대해서 다른사람들과 대화할 수 있게 해주는 일종의 언어로서 작용하게 된다.
* 이를 이루게 해주는 것이 **Entity Relationship Diagram(ERD)** 이다.

> 포함적인 관계가 아닌 평면적인 관계로 개념을 뽑아낸다.

## __Entity Relationship Diagram__
현실을 3가지 관점으로 간단하게 바라볼 수 있는 **Finder와 같은 역할을 한다.** 현실로부터 개념을 인식하는 도구 이면서 다른사람도 알아볼 수 있게 해줄 수 있게 해준다. **또한 매우 쉽게 표로 전환할 수 있다.**

#### __ERD의 3가지 관점__
1. 정보 - 정보를 발견하고 다른사람에게 표현할 수 있게 해준다.
2. 그룹 - 서로 연관된 정보를 그룹핑해서 인식하고 다른사람에게 표현할 수 있게 해준다.
3. 정보 그룹사이의 관계를 인식하고 다른사람에게 표현할 수 있게 해준다.

#### __ERD를 만드는 방법__
1. 서로 연관된 정보들을 묶어주는 큰 덩어리부터 뽑아낸다. (ex. 글, 저자, 댓글 등)
2. 글, 저자, 댓글 들을 모두 동등하게 표현한다.
3. 표 안에 표가 내포되어 있는 관계는 허용하지 않는다.

__[표 1. 거대 단일 테이블]__

|글 제목|글 내용|글 저자 이름|글 저자 소개|글 저자 가입일|댓글 내용|댓글 저자|댓글 저자 소개|댓글 저자 가입일|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|글 1 제목|글 1 내용|<div style="background:yellow;">저자 1 이름</span>|<div style="background:yellow;">저자 1 소개</span>|<div style="background:yellow;">저자 1 가입일</span>|댓글 1 내용|<div style="background:yellow;">저자 1 이름</span>|<div style="background:yellow;">저자 1 소개</span>|<div style="background:yellow;">저자 1 가입일</span>|
|글 2 제목|글 2 내용|<div style="background:yellow;">저자 1 이름</span>|<div style="background:yellow;">저자 1 소개</span>|<div style="background:yellow;">저자 1 가입일</span>|댓글 2 내용|<div style="background:yellow;">저자 1 이름</span>|<div style="background:yellow;">저자 1 소개</span>|<div style="background:yellow;">저자 1 가입일</span>|
|글 3 제목|글 3 내용|<div style="background:green;">저자 2 이름</span>|<div style="background:green;">저자 2 소개</span>|<div style="background:green;">저자 2 가입일</span>|댓글 3 내용|<div style="background:#20a4e6;">저자 3 이름</span>|<div style="background:#20a4e6;">저자 3 소개</span>|<div style="background:#20a4e6;">저자 3 가입일</span>|

**표 1**과 같이 하나의 표 안에 모든 정보를 다 담을 수도 있다. 즉 여러개의 컬럼(Column)을 가진 표를 만들 수 있다. **하지만** 극단적으로 1천개의 컬럼이 필요한 표를 만들었을 때 불필요한 정보를 불러올 수 있다. 또한 1억개의 데이터가 있다면 **데이터의 중복이 발생**하게 된다. 즉, **거대 단일 테이블로 표현하면 데이터를 다룰 때 효율이 떨어지고 중복이 발생한다.** 이러한 중복을 최소화 하기 위해 아래와 같이 표를 나눈다.

__[표 2. 글 테이블]__

|아이디|제목|내용|저자 아이디|
|:---:|:---:|:---:|:---:|
|1|제목 1|내용 1|1|
|2|제목 2|내용 2|1|
|3|제목 3|내용 3|1|

__[표 3. 저자 테이블]__

|아이디|이름|소개|
|:---:|:---:|:---:|
|1|이름 1|소개 1|
|2|이름 2|소개 2|
|3|이름 3|소개 3|

__[표 4. 댓글 테이블]__

|아이디|제목|내용|작성일|저자 아이디|
|:---:|:---:|:---:|:---:|:---:|
|1|제목 1|내용 1|작성일 1|1|
|2|제목 2|내용 2|작성일 2|1|
|3|제목 3|내용 3|작성일 3|2|

**위와 같이 테이블을 여러개로 쪼갰을 때의 이점**
1. 주제에 따라서 데이터를 **그룹핑**할 수 있다.
2. 필요한 정보만을 뽑아낼 수 있다.
3. **Join**을 사용하여 아래 [표 5]와 같이 **관계된 테이블을 합성**하여 뽑아낼 수 있다.

> **SELECT** 댓글, 내용, 댓글.작성일, 저자.이름, 저자.소개
**FROM** 댓글 **LEFT JOIN** 저자 **ON** 댓글.저자 아이디 = 저자.아이디

__[표 5. Join 결과]__

|댓글 내용|댓글 작성일|저자|저자 소개|
|:---:|:---:|:---:|:---:|
|댓글 1 내용|댓글 1 작성일|<div style="background:green;">저자 1 이름</span>|<div style="background:green;">저자 1 자기소개</span>|
|댓글 2 내용|댓글 2 작성일|<div style="background:green;">저자 1 이름</span>|<div style="background:green;">저자 1 자기소개</span>|
|댓글 3 내용|댓글 3 작성일|<div style="background:green;">저자 1 이름</span>|<div style="background:green;">저자 1 자기소개</span>|

> 포함적인 관계가 아닌 평면적인 관계로 개념을 뽑아낸다.

### __ERD 에서의 표현__
#### __Entity - Table__
찾아낸 **개념을 Entity**라고 말하며 추후 Table로 전환이 된다.

    개념 : 글, 저자, 댓글 등

#### __Attribute - column__
개념안에 있는 **구체적인 데이터**를 말하며 추후 Table의 Column이 된다.

    구체적 데이터 : 글(개념)의 제목, 본문, 생성일 등

Entity를 Directory라고 생각하고 Attribute를 File이라고 한다면  Entity는 Child Directory를 가질 수 없는 **평면적인 Directory**라고 생각하면 된다.

#### __Relation - PK, FK__
**Entity들 간의 연관성을 표현**해 준 것을 Relation 이라고 하며 PK, FK의 형태로 관계가 표현이 되며 **Join을 통해 테이블들을 연결**한다.

    저자 --- <쓰다> --- 글
    글 --- <소속> --- 댓글
    저자 --- <쓰다> --- 댓글

***
## **Entity 정의**
**읽기**에서는 Entity를 찾기가 힘들고 **쓰기**에서는 대체적으로 찾기 쉽다. 즉, 예제에서 살펴보면 **저자등록, 글 작성, 댓글 작성** 등이 쓰기에 해당한다.

__~~@TODO~~__
__~~ER-Diagram 그려보기~~__
~~[draw.io](https://www.draw.io)를 이용해 ER-Dialgram 그려보기~~

## **각 Entity에서 속성 뽑아내기**
* 글 - 제목, 작성일, 본문
* 저자 - 이름, 자기소개, 가입일
* 댓글 - 본문, 작성일

각 Entity의 속성들을 정의 했다면 식별자(Identifier)를 설정해줘야 한다. **식별자란 해당 Entity의 Raw를 나타내는 고유 속성**이며 추후 PrimaryKey가 된다.

**식별자로 사용될 수 있는 Column**
중복이 발생할 수 없는 속성(Column)을 식별자로 선정해야 한다.

* 후보키(candidate key) - 식별자가 될 수 있는 key들
* 기본키(primary key) - 후보키 중에서 선정한 식별자
* 대체키(alternate key) - 기본키가 아닌 후보키들 (성능향상을 위해서 secondary index를 걸기 좋은 key)
* 중복키(composite key) - 두개의 key를 합쳐 기본키로 설정함

식별자를 선정하기 위해 각 Entity에 Primary Key가 될 수 있는 속성을 추가함.

* 글 - **글 아이디(auto increasement)**, 제목, 작성일 본문
* 저자 - **저자 아이디(auto increasement)**, 자기소개, 가입일
* 댓글 - **댓글 아이디(auto increasement)**, 본문, 작성일

***
## **Relationship 정의**
각 테이블들의 **PrimaryKey와 ForeignKey가 연결**되는 걸 통해서 실제로 구현이 된다.

    PrimaryKey - 각 테이블의 Raw를 식별하는 유일무일한 식별자
    ForeignKey - 다른 테이블의 PrimaryKey와 연결되어 있는 식별자

### __Cardinality와 Optionality__

**Cardinality**
ERD에서 데이터베이스가 지켜야할 제약조건 중 연결(Connectivity)를 나타내는 것

    일대일(One To On, 1:1) - X에 속하는 한 개체는 Y에 속하는 한 개체에만 연결되며, Y에 속하는 한 개체도 X에 속하는 한 개체에만 연결된다.(ex. 담임 - 반)
    일대다(One To Many, 1:N) - X에 속하는 한 개체는 Y에 속하는 한 개체에만 연결되며, Y에 속하는 한 개체는 X에 속하는 여러 개체들과 연결된다. (ex. 저자 - 댓글)
    다대다(Many To Many, N:M) - X에 속하는 한 개체는 Y에 속하는 여러 개체들과 연결될 수 있으며, Y에 속하는 한 개체도 X에 속하는 여러 개체들과 연결될 수 있다. (ex. 글 - 저자 (공동 저자))

__Optionality__
ERD에서 두 테이블간 필수관계(Mandatory)와 선택관계(Optional)를 나타내는 것

    Ex) 저자 - 댓글간의 관계
    저자의 관점 - 저자는 댓글을 작성하지 않을수도 있다.
    댓글의 관점 - 각 댓글은 반드시 저자가 있다.

    표현 (ㅁ - 테이블)
    ㅁ--|-----O--ㅁ

## __ER-Diagram__
![ER-Diagram](./images/ER_Diagram.png)

# __논리적 데이터 모델링__
생각한 개념들을 관계형 데이터 베이스 패러다임에 맞는 표로 전환하는 작업

## __Mapping Rule__
ERD를 통해 표현한 내용을 관계형 데이터 베이스에 맞는 형식을로 전환할 때 사용하는 방법론

    Entity -> Table
    Attribute -> Column
    Relation -> PK, FK

@TODO ER Master 도구 사용해보기 (ermater.sourceforge.net)

## __Relationship -> PK, FK__

* __1:1 관계 (저자 - 휴면저자)__
의존 관계에 따라 PK, FK를 따질 수 있음.  
**저자는 휴면저자에 의존하지 않지만 휴면저자는 저자에 의존함.** 따라서 저자 테이블에 PK를 배치하고 휴면저자 테이블에 FK를 배치한다.  

* __1:N 관계 (댓글 - 저자, 댓글 - 글)__
1:N 관계에서는 N인 쪽이 FK를 가지면 된다.

* __N:M 관계 (저자 - 글)__

__예제__

__:book: 저자 및 작성글 현황__

| 저자 | 작성 글     |
| :------------- | :------------- |
| kim       | MySQL, SQL Server, ORACLE       |
| lee       | MySQL, SQL Server       |

__:seedling: author 테이블__

| id | name | profile | created |
|:---:|:---:|:---:|:---:|
|1|kim|developer|2011|
|2|lee|designer|2012|
|3|park|planner|2013|

__:seedling: topic 테이블__

| id | title | description | created |
|:---:|:---:|:---:|:---:|
|1|MySQL ... |...|2011|
|2|ORACLE ... |...|2012|
|3|SQL Server ... |...|2013|

위와 같은 **N : M 관계인 두 테이블에서** PK, FK를 설정해야한다. 만약 **topic에 author 정보를 적는다면 아래와 같이 만들어 진다.**

__:seedling: author 정보를 기입한 topic 테이블__

| id | title | description | created |author_id|
|:---:|:---:|:---:|:---:|:---:|
|1|MySQL ... |...|2011|1,2|
|2|ORACLE ... |...|2012||
|3|SQL Server ... |...|2013|1,2|

**하지만** 이렇게 만드는 경우 **두 테이블간 JOIN을 할 수 없을 뿐더러 검색할 때 많은 제약사항이 생긴다.** author테이블에 topic정보를 기입할 때에도 마찬가지이다. **이를 해결하기 위해서 중재자인 Mapping Table을 따로 만든다.**

## __Mapping Table__
**author 테이블**과 **topic 테이블**의 사이를 이어주는 Mapping Table인 **Write 테이블**을 만든다.

**:seedling: write 테이블 (Mapping Table)**

|author_id|topic_id|created|
|:---:|:---:|:---:|
|1|1|...|
|1|2|...|
|1|3|...|
|2|1|...|
|2|3|...|

Mapping Table을 작성하게 되면 두 테이블이 결합되었을 때 의미가 있는 정보 (ex. 각 저자가 언제 글을 수정하였는 가 등의 정보)를 알 수 있게 된다.

@TODO ER Master 도구 사용해 N:M 관계까지 모두 그려보기

## __정규화 (Normalization)__
정제되지 않은 데이터(표)를 관계형 데이터 베이스에 어울리는 표로 만들어주는 레시피이다.

### __예제__
Unnormalized Form에서부터 제 1 정규화, 제 2 정규화, 제 3 정규화를 거쳐가면서 관계형 데이터베이스에 걸맞는 표로 만들어가기.

__:seedling: Unnormalized Form__
관계형 데이터베이스에 맞지 않는 상태

|<U>title</U>|<U>type</U>|description|created|author_id|author_name|author_profile|price|tag|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|MySQL|paper|MySQL is ... |2011|1|kim|developer|10000|rdb, free|
|MySQL|online|MySQL is ... |2011|1|kim|developer|0|rdb, free|
|ORACLE|online|ORACLE is ... |2012|1|kim|developer|0|rdb, commercial|

## __제 1 정규화(First Normal Form)__
원칙 : **Atomic columns**  
각 행의 컬럼 값들이 Atomic 해야한다. (각각의 컬럼이 하나의 값만을 가져야 한다.)

> 만약 해당 컬럼이 여러개의 값을 가져도 된다면 정규화를 진행할 필요가 없다.

__잘못된 제 1 정규화의 예__

|<U>title</U>|<U>type</U>|description|created|author_id|author_name|author_profile|price|tag|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|MySQL|paper|MySQL is ... |2011|1|kim|developer|10000|rdb|
|MySQL|paper|MySQL is ... |2011|1|kim|developer|10000|free|

제 1 정규화를 만족하지만 **데이터의 중복이 일어나고 있음.**

|<U>title</U>|<U>type</U>|description|created|author_id|author_name|author_profile|price|tag1|tag2|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|MySQL|paper|MySQL is ... |2011|1|kim|developer|10000|rdb|free|

제 1 정규화를 만족하지만 **만약 tag를 추가한다면 테이블 전체 구조를 변경하거나 낭비(null)가 생긴다.**

> **제 1 정규화를 만족하면서 데이터를 효율적으로 다루기 위해 테이블을 쪼갠다.**

__:seedling: 테이블 쪼개기__

    Topic -N-------M- Tag (N : M 관계)
    따라서 Mapping Table을 만들어야 함

tag는 글의 type과는 관계 없이 **글의 제목(title)에만 의존하고 있다.** 따라서 **topic의 중복키 중 title만을 PK로 가져온다.** 또한 topic과 tag를 mapping하므로 tag의 id값을 가져와 Mapping Table의 PK를 만든다.

__topic Table__

|<U>title</U>|<U>type</U>|description|created|author_id|author_name|author_profile|price|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|MySQL|paper|MySQL is ... |2011|1|kim|developer|10000|
|MySQL|online|MySQL is ... |2011|1|kim|developer|0|
|ORACLE|online|ORACLE is ... |2012|1|kim|developer|0|

__topic_tag_relation (Mapping Table)__

|<U>topic_title</U>|<U>tag_id</U>|
|:---:|:---:|
|MySQL|1|
|MySQL|2|
|ORACLE|1|
|ORACLE|3|

__tag Table__

|tag|name|
|:---:|:---:|
|1|rdb|
|2|free|
|3|commercial|

## __제 2 정규화(Second Normal Form)__
원칙 : No partial dependencies (부분 종속성이 없어야 한다.)

> 테이블의 기본키가 중복키를 가지고 있다면 제 2 정규화를 진행하여야 한다.

**제 1 정규화를 거친 topic 테이블**에서 discription, created, author_id, author_name, author_profile 컬럼이 중복이 일어나고 있다.

위에서 명시한 컬럼들은 **title 컬럼에만 부분적으로 종속되고 있다.** topic 테이블은 type 컬럼이 paper, online에 따라 price가 달라진다. 즉, title, type, price를 위한 테이블이다.

__:seedling: 제 2 정규화(부분 종속성 제거) 진행하기__
제 1 정규화를 마친 topic 테이블을 제 2 정규화 진행해 부분 종속성 제거하기

> 부분적으로 종속되는 컬럼들만 모으고 전체적으로 종속되는 컬럼을 따로 쪼갠다.

__topic Table__

|<U>title</U>|description|created|author_id|author_name|author_profile|
|:---:|:---:|:---:|:---:|:---:|:---:|
|MySQL|MySQL is ... |2011|1|kim|developer|
|ORACLE|ORACLE is ... |2011|1|kim|developer|

부분적으로 종속되는 컬럼들만 모음으로써 데이터의 중복을 제거한다.

__topic_type Table__

|title|type|price|
|:---:|:---:|:---:|
|MySQL|paper|10000|
|MySQL|online|0|
|ORACLE|online|0|

전체적으로 종속되는 컬럼을 따로 쪼갠다.

## __제 3 정규화(Third Normal Form)__
원칙 : No transitive dependencies(이행적 종속성 제거)

topic 테이블에서 **author_id 컬럼은 title 컬럼에 종속된다.** 또한 author_name, author_profile 컬럼은 author_id 컬럼에 의존하고 있다. 이러한 관계를 **이행적 종속성을 가진다고 말한다.**

> 이행적 종속성을 가지고 있다면 중복이 발생하고 있다는 의미이다.

__:seedling: 제 3 정규화(이행적 종속성 제거) 진행하기__
먼저 중복이 발생하는 author_id, author_name, author_profile 컬럼을 따로 빼내 새로운 테이블을 만든다.

> 이행적 종속성 때문에 발생하는 중복된 컬럼들을 따로 빼내 새로운 테이블을 만들고, 기존 테이블에 이행적 종속성을 유발시키는 컬럼은 남겨둔다.

__topic Table__

|<U>title</U>|description|created|author_id|
|:---:|:---:|:---:|:---:|
|MySQL|MySQL is ... |2011|1|
|ORACLE|ORACLE is ... |2011|1|

__author Table__

|id|author_name|author_profile|
|:---:|:---:|:---:|
|1|kim|developer|

## __제 3 정규화까지 마친 최종 테이블__

__author Table__

|id|author_name|author_profile|
|:---:|:---:|:---:|
|1|kim|developer|

__topic Table__

|<U>title</U>|description|created|author_id|
|:---:|:---:|:---:|:---:|
|MySQL|MySQL is ... |2011|1|
|ORACLE|ORACLE is ... |2011|1|

__topic_type Table__

|title|type|price|
|:---:|:---:|:---:|
|MySQL|paper|10000|
|MySQL|online|0|
|ORACLE|online|0|

__topic_tag_relation (Mapping Table)__

|<U>topic_title</U>|<U>tag_id</U>|
|:---:|:---:|
|MySQL|1|
|MySQL|2|
|ORACLE|1|
|ORACLE|3|

__tag Table__

|tag|name|
|:---:|:---:|
|1|rdb|
|2|free|
|3|commercial|

# __물리적 데이터 모델링__
어떤 데이터베이스를 사용할 것인지 생각하는 단계  
표를 생성하는 SQL코드를 산출할 수 있다.

> 이상적인 표를 구체적인 제품에 맞는 현실적인 표를 만드는 단계. (성능이 중요)

일단 운영을 조금이라도 해보자. 데이터가 쌓이고 처리량이 많아져야 느려지는 부분의 분별이 생긴다. 이때 적당한 시점에서 **각 쿼리의 성능을 평가해보고 병목이 발생하는 지점을 집중적으로 해결을 해보자.** (find slow query - query를 실행할 때 느려지는 부분을 찾아보기)

성능을 향상시키기 위한 여러가지 방법이 존재한다. 그 중 최후의 방법은 역정규화(denormalization)

역 정규화 - 이상적으로 정규화 된 표의 구조를 손을 대는 것
역 정규화는 혹독한 댓가를 치뤄야함. 이러한 댓가를 지불할만한 가치가 있는지를 먼저 살펴보고 다른 방법을 먼저 시도해보기.

index - 행에 대한 읽기 성능을 비약적으로 향상시킨다. 대신 쓰기 성능을 비관적으로 희생시킨다. 왜냐하면 쓰기가 일어날 때 마다 그 행이 index가 걸려있다면 입력된 정보를 잘 정리정돈 하기 위한 복잡한 연산과정이 필요. 이 과정에서 시간이 오래 걸리고 저장 공간을 더 많이 차지한다. 하지만 잘 정리정돈 되면 빠르게 읽을 수 있기 때문에 사용

application 영역에서 cache화 시키는 방법을 생각하는 것도 방법.

## __역정규화란?__
정규화를 통해 만든 이상적인 표를 성능이나 개발의 편의성을 위해 구조를 바꾸는 것.

정규화는 쓰기의 편리함을 위해서 읽기의 성능을 희생하는 것이다. 왜냐하면 여러개의 표로 쪼개지기 때문에 JOIN을 사용해야 하는데 JOIN은 비싼 작업이기 때문이다. 이렇게 정규화 된 테이블에서 읽기가 자주 일어날 때 정규화로 인해서 성능이 느려지는 경우가 생기는데 이때 여러가지 방법을 사용해보고 그래도 성능이 향상되지 않는다면 최후의 수단으로 역정규화를 진행해보자.

    생각해둘 것.

    1. 정규화를 한 다음 역정규화를 진행하는 것이다.
    2. 처음부터 정규화를 아예 하지 않은 표가 좋은거라고 할 수 없다.
    3. 정규화를 한다고 해서 반드시 성능이 떨어지는 것도 아니다.

정규화는 엄격한 규칙에 따라서 순차적으로 진행하는 것이지만, 역정규화는 규칙이 있는게 아니다. 그 상황에 맞는 역정규화를 진행하는데 여러 기법들이 있을 수 있고 자신이 생각하는 창의적인 방법을 생각해낼 수 있다. 아래서 진행하는 것들은 일종의 Sample과 같은 역할이다.

## __컬럼의 역정규화 - 컬럼 중복 : JOIN 줄이기__
topic_title에 대한 tag 이름을 가져와보기

__topic_tag_relation__

|<U>topic_title</U>|<U>tag_id</U>|
|:---:|:---:|
|MySQL|1|
|MySQL|2|
|ORACLE|1|
|ORACLE|3|

__tag Table__

|tag|name|
|:---:|:---:|
|1|rdb|
|2|free|
|3|commercial|

__:seedling: 역 정규화 전 JOIN을 통한 Query__
```mysql
SELCT tag.name
FROM topic_tag_relation AS TTR
LEFT JOIN tag
ON TTR.tag_id = tag.id
WHERE topic_id = 'MySQL';
```

비용이 많이 드는 방법인 JOIN을 사용함으로써 운영중인 서비스의 성능이 저하될 수 있다. 이때 선택할 수 있는 방법은 여러가지가 있지만 가장 이해하기 쉬운 방법은 topic_tag_relation 테이블의 tag의 name을 넣는 것이다. 즉, 중복을 허용하는 것이다. 중복을 허용하지만 JOIN을 사용하지 않기 때문에 훨씬 더 빠르게 데이터를 뽑아낼 수 있다는 장점이 있다.

__역정규화를 거친 topic_tag_relation__

|<U>topic_title</U>|<U>tag_id</U>|tag_name|
|:---:|:---:|:---:|
|MySQL|1|rdb|
|MySQL|2|free|
|ORACLE|1|rdb|
|ORACLE|3|commercial|

__:seedling: 역 정규화 후 JOIN이 없는 Query__
```mysql
SELCT tag.name
FROM topic_tag_relation AS TTR
LEFT JOIN tag
ON TTR.tag_id = tag.id
WHERE topic_id = 'MySQL';
```

정규화를 진행하기 전에 가지고 있던 문제점을 고스란히 가지게 된다. 또한 기존에 있던 tag 테이블은 그대로 있기 때문에 안좋은 상황이 된다. 역정규화를 하게 되면 시스템의 복잡도가 높아지게 된다.

## __컬럼의 역정규화 - 파생 컬럼의 형성 : 계산작업을 줄이기__
목표 : 각각의 저자가 몇개의 글을 작성했는지를 목록으로 표현한다.

__author Table__

|id|author_name|author_profile|
|:---:|:---:|:---:|
|1|kim|developer|

__topic Table__

|<U>title</U>|description|created|author_id|
|:---:|:---:|:---:|:---:|
|MySQL|MySQL is ... |2011|1|
|ORACLE|ORACLE is ... |2012|1|
|SQL SERVER|SQL SERVER is ... |2013|2|

__:seedling: 역 정규화 전 Query__
```mysql
SELECT author_id COUNT(author_id)
FROM topic
GROUP BY author_id;
```
만약 위와 같은 작업을 굉장히 빈번한 작업이라면 GROUP BY가 비싼 작업이 될 수 있다. 이 점에 대해서 author 테이블에 각 저자가 몇개의 글을 가지고 있는지 나타내는 컬럼을 추가해 글을 추가할 때마다 1씩 증가시킬 수 있다.

__역정규화를 거친 author 테이블__

|id|author_name|author_profile|topic_count|
|:---:|:---:|:---:|:---:|
|1|kim|developer|2|

__:seedling: 역 정규화 후 JOIN이 없는 Query__
```mysql
SELECT id, topic_count
FROM author;
```
GROUP BY를 할 필요가 없기 때문에 훨씬 빠르게 데이터를 가져올 수 있다. 하지만 topic_count는 새로운 글이 작성 될 때마다 갱신시켜줘야 하는 비용이 발생하기 때문에 이 점에 대해서 고려해야 한다.

## __컬럼의 역정규화 - 컬럼을 기준으로 테이블을 분리__

만약 topic 테이블의 description의 용량이 굉장히 크고 description을 제외한 나머지 컬럼들을 조회하는 연산과 description을 포함해서 조회하는 연산이 양쪽 다 굉장히 빈번할 때 처리하는 방법.

__:bulb: 역 정규화를 통해 테이블 쪼개기__
description을 제외한 테이블, PrimaryKey와 description만을 가진 테이블 두 개로 쪼갠다.

__:seedling: topic 테이블__

|<U>title</U>|created|author_id|
|:---:|:---:|:---:|
|MySQL|2011|1|
|ORACLE|2012|1|
|SQL SERVER|2013|2|

__:seedling: topic_description 테이블__

|title|description|
|:---:|:---:|
|MySQL|MySQL is ... |
|ORACLE|ORACLE is ... |
|SQL SERVER|SQL SERVER is ... |

이렇게 두 개의 테이블로 쪼갠다면 **용량이 큰 description 컬럼은 topic에 없기 때문에 여러가지 장점을 가지게 된다.** 만약 쪼갠 두 테이블의 사용이 매우 빈번하다면 두 테이블을 각 다른 컴퓨터에 저장해서 쓰기/읽기 작업을 시키면 컴퓨터 여러대로 분산할 수 있다. 이런 방법을 샤딩이라고 한다. 성능의 한계가 느껴졌을 때 여러대의 컴퓨터로 Scale out하는 기법 중 하나이다. 하지만 유지하기도 힘들고 어렵기 때문에 최후의 수단으로 사용하는 방법 중 하나이다.

## __컬럼의 역정규화 - 행을 기준으로 테이블을 분리__
만약 사용자가 많으면서 조회가 빈번하게 일어난다면 해당 테이블을 행을 기준으로 여러개로 나눈다. 예를 들어, author_id를 기준으로 1000번까지 저장되는 테이블을 만들고 2000번까지 저장되는 테이블을 만든다.

__topic_1000 Table__

|<U>title</U>|description|created|author_id|
|:---:|:---:|:---:|:---:|
|MySQL|MySQL is ... |2011|1|
|ORACLE|ORACLE is ... |2012|1|
|SQL SERVER|SQL SERVER is ... |2013|2|

__topic_2000 Table__

|<U>title</U>|description|created|author_id|
|:---:|:---:|:---:|:---:|
|SQL SERVER|SQL SERVER is ... |2013|1500|

이렇게 테이블을 나눈 다면 author_id에 따라 query를 요청하는 서버를 달리하여 데이터 관리를 분산시킨다. 이렇게 한다면 각각의 물리적인 서버마다 서로다른 테이블을 저장하고 조회를 처리하는 것을 통해서 무한이 많은 처리량을 소화할 수 있다. 하지만 이렇게 처리한다면 사고 위험성도 높아질 뿐더러 노하우도 필요한 테크닉이기 때문에 궁지에 몰렸을 때 사용

## __관계의 역정규화 - 지름길을 만든다__
목표 : 저자의 태그 아이디와 태그명을 조회한다.

topic_tag_relation, tag 두 테이블의 JOIN을 통해서 정보를 뽑아낼 수 있다. 하지만 두 테이블 만으로는 author_id 값을 가져올 수 없기 때문에 topic테이블도 함께 JOIN을 해야한다. 이 문제를 낮추기 위한 역정규화 방식.

__:seedling: 역 정규화 전 Query__

```mysql
SELECT tag.id, tag.name
FROM topic_tag_relation AS TTR
LEFT JOIN tag ON TTR.tag_id = tag.id
LEFT JOIN topic ON TTR.topic_title = topic.title
WHERE author_id = 1;
```

__:bulb: 역 정규화를 통해 비용 줄이기__
topic_tag_relation 테이블에 author_id 컬럼을 추가해서 조회할 수 있게 만들어 JOIN을 줄인다.

__역 정규화를 거친 topic_tag_relation 테이블__

|<U>topic_title</U>|<U>tag_id</U>|author_id|
|:---:|:---:|:---:|
|MySQL|1|1|
|MySQL|2|1|
|ORACLE|1|1|
|ORACLE|3|1|

__:seedling: 역 정규화 후 Query__

```mysql
SELECT tag.id, tag.name
FROM topic_tag_relation AS TTR
LEFT JOIN tag ON TTR.tag_id = tag.id
WHERE author_id = 1;
```
