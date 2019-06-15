[실습](https://codelabs.developers.google.com/codelabs/android-paging/index.html)

PagedList : DataSourceㄹ부터 데이터를 chunks(pages)로 로드하는 List
* 현재 content의 snapshot을 갖고, data가 변경되었다는 callback

DataSource : data의 snapshots을 pagedList에 로드해주는 base class
DataSource.Factory : dataSource 생성

paging이 되어있지 않는 데이터의 목록을 chunk 단위로 받아올 수 있다.

1. ItemKeyedDatasource : item의 key를 이용해 페이징
2. PageKeyedDataSource : 인접 페이지에 대한 정보를 이요해 페이징


# Repository


# Paging UI Component
## PagedListAdapter

* 달라진 점 : 표시할 객체가 nullable이 될 수 있따.

## PagedList Configuration
