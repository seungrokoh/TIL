# :seedling: __Java Tips__
---
:book: contents

* Converting `String[]` to `ArrayList<String>`
* Converting `ArrayList<String>` to `String[]`
***

### __:seedling: Converting `String[]` to `ArrayList<String>`__

```java
String[] strings = new String[];
List<String> list = Arrays.asList(strings);
```
### __:seedling: Converting `ArrayList<String>` to `String[]`__

```java
List<String> list = new ArrayList<>();
list.add("list1");
list.add("list2");

String[] strings = list.stream().toArray(String[]::new);
```

or

```java
String[] strings = list.toArray(new String[list.size()]);
```
