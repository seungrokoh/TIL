# BottomSheetDialogFragment

BottomSheetDialogFragment 모서리 둥글게 (radius) 만들기
***

### __BottomSheetDialogFragment 모서리 둥글게 만들기__

BottomSheetDialogFragment으로 View를 보여줘야 할 상황이 생겨 만들다 __모서리를 둥글게 하는 resource 파일을 만들고 해당 layout 파일에 background 적용을 시켰는데 제대로 적용이 되지 않는 현상이 발생하였다.__  
일반적인 CustomDialogFragment를 만들때와 같이 해당 Background 색을 투명으로 만들고 __custom background를 만들어 모서리를 둥글게 하면 해결이 될 줄 알았다.__ 하지만 예상과는 달리 모서리가 계속해서 뾰족하게 만들어지고 background color를 주면 __BottomSheetDialog View 부분만 radius가 들어갔다.__ 이 문제를 해결하기 위해 구글링을 한 결과 다음과 같이 적용하면 쉽게 해결할 수 있다.

먼저 BottomSheetDialogFragment에 적용할 __custom drawable 파일을 생성한다.__

:seedling: bg_radius_bottom_sheet_dialog.xml
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/colorWhite" />
    <corners
        android:topRightRadius="8dp"
        android:topLeftRadius="8dp"/>
</shape>
```

> 배경색이 흰색이고 왼쪽과 오른쪽 모서리만 둥글게 설정

그 다음 __res > values > style.xml__ 파일에 BottomSheetDialogFragment의 Theme을 추가한다.

:seedling: style.xml
```xml
<!-- Bottom Sheet Dialog Theme -->
<style name="AppBottomSheetDialogTheme"
    parent="Theme.Design.Light.BottomSheetDialog">
    <item name="bottomSheetStyle">@style/AppModalStyle</item>
</style>

<style name="AppModalStyle" parent="Widget.Design.BottomSheet.Modal">
    <item name="android:background">@drawable/bg_bottom_sheet</item>
</style>
```

그리고 적용하고자 하는 __BottomSheetDialogFragment의 onCreate 부분에 아래와 같이 setStyle을 작성하면 된다.__

:seedling: BottomSheetDialogFragment.java
```java
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
}
```

__References__
* [[Stack Overflow] Q : Round corner for BottomSheetDialogFragment](https://stackoverflow.com/a/56474000)

***

### __BottomSheetDialogFragment 잠시 띄웠다 없애기__
