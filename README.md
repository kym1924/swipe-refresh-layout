<div>
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white" />
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=Kotlin&logoColor=white" />
<img src="https://img.shields.io/badge/writer-kym1924-yellow?&style=flat&logo=Android"/>
</div>

# SwipeRefreshLayout
Implement *the swipe-to-refresh* UI pattern.
<br><br>
<img width=360 height=760 src="https://user-images.githubusercontent.com/63637706/136909350-819719cc-9700-4376-ba2c-680fca2687e0.gif"/>

#### 1. Initialize
```groovy
// build.gradle in Module
implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
```
<br>

#### 2. Declaration
```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:id="@+id/swipe_refresh_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    	<!-- Only One ChildView -->

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```
<br>

#### 3. setOnRefreshListener
Set the listener to be notified *when a refresh is triggered* via the swipe gesture.
```kotlin
binding.swipeRefreshLayout.setOnRefreshListener {
    // TODO
    binding.swipeRefreshLayout.isRefreshing = false 
    // 'isRefreshing' is notification that refresh is over.
}
```
<br>

#### 4. setColorSchemeResources() & setColorSchemeColors()
*Set the color resources used in the progress animation from color resources.*
```kotlin
// @param colorResIds
binding.swipeRefreshLayout.setColorSchemeResources(
    R.color.purple_500,
    R.color.purple_700,
    R.color.purple_200
)

// @param colors
binding.swipeRefreshLayout.setColorSchemeColors(
    getColor(R.color.purple_500),
    getColor(R.color.purple_700),
    getColor(R.color.purple_200)
)
```

#### 4-1. SwipeRefreshLayout.java
```java
// @param colorResIds
public void setColorSchemeResources(@ColorRes int ... colorResIds) {
    final Context context = getContext();
    int[] colorRes = new int[colorResIds.length];
    for (int i = 0; i < colorResIds.length; i++) {
        colorRes[i] = ContextCompat.getColor(context, colorResIds[i]);
    }
    setColorSchemeColors(colorRes);
}

// @param colors
public void setColorSchemeColors(@ColorInt int ... colors) {
    ensureTarget();
    mProgress.setColorSchemeColors(colors);
}
```

#### 4-2. CircularProgressDrawable.java
```java
// This is the default set of colors.
private static final int[] COLORS = new int[]{
    Color.BLACK
};

public void setColorSchemeColors(@NonNull int ... colors) {
    mRing.setColors(colors);
    mRing.setColorIndex(0);
    invalidateSelf();
}
        
void setColorIndex(int index) {
    mColorIndex = index;
    mCurrentColor = mColors[mColorIndex];
}
```
<br>

##### Reference
- https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout
- https://developer.android.com/reference/androidx/swiperefreshlayout/widget/SwipeRefreshLayout
