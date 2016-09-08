## Themer

### 简介
可灵活定制的主题支持库</br>
以最简洁的方式实现变更主题配色。</br>

### ScreenShot
![示例1](https://raw.githubusercontent.com/NightFarmer/Themer/master/sample/screenshot/Themer1.gif "示例1")
![示例1](https://raw.githubusercontent.com/NightFarmer/Themer/master/sample/screenshot/Themer2.gif "示例1")

### 使用方法

#### 依赖
gradle

/build.gradle
```
repositories {
    ...
    maven {
        url "https://jitpack.io"
    }
}
```
/app/build.gradle
```
dependencies {
    ...
    compile 'com.github.NightFarmer:Themer:1.0.0'
}
```
#### 使用
在Application中初始化调用
```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Themer.INSTANCE.init(this, R.style.def);//设置默认主题
	...
    }
}
```

在Activity的Ui线程中任意地方调用切换主题方法
```java
    Themer.INSTANCE.setTheme(this, R.style.def, bundle);//直接切换
```
OR
```java
    Themer.INSTANCE.setThemeSoft(this, R.style.def, bundle);//带有300毫秒的渐变动画
```

#### 主题定义

在values文件夹下新增attrs文件，并自定义主题参数
```xml
<resources>
    <attr name="textcolor" format="reference|color" />
    <attr name="buttonBackcolor" format="reference|color" />
    ....
<resources>
```

在不同的Theme中定义不同的值
style_def.xml
```xml
    <style name="def" parent="AppBase">
        <item name="buttonBackcolor">#0000FF</item>
        <item name="textcolor">#ff0000</item>
        ...
    </style>
```
styel_dark.xml
```xml
    <style name="def" parent="AppBase">
        <item name="buttonBackcolor">#000000</item>
        <item name="textcolor">#FFFFFF</item>
        ...
    </style>
```

在xml布局文件中使用
```xml
        <com.nightfarmer.themelib.view.Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="?attr/buttonBackcolor"
            android:textColor="?attr/textcolor"
            android:text="主题测试" />
```

#### 数据缓存
Themer在变更主题时会触发除当前activity外的所有已被创建且未销毁的activity的重建, 若应用中变更主题时存在有临时数据的activity存在栈中, 强烈建议复写这些activity的onSaveInstanceState方法来缓存临时数据, 以免造成UI数据丢失.

Themer在变更主题时会重建当前Activity, 建议将临时数据通过setTheme的第三个参数bundle传入, 并在activity的intent中获取:
```java
    public void change(){
        Bundle cacheData = new Bundle();
        cacheData.putString("cachedStr", "hello theme !");
        Themer.INSTANCE.setTheme(this, R.style.Theme_Blue, cacheData);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        Bundle cachedData = getIntent().getBundleExtra(Themer.INSTANCE.getCachedDataKey());
        if (cachedData != null) {
            Log.i("chachDataTest", "data: " + cachedData.getString("cachedStr"));
        }
    }
```


