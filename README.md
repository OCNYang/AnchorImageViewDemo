# 锚点图片

![示意图](https://github.com/OCNYang/AnchorImageViewDemo/raw/master/imgs/part.png)

* 根据锚点坐标给图片设置锚点
* 锚点具有点击事件
* 图片可以放大缩小移动，锚点相对位置不变


# Demo 效果

![Demo 效果](https://github.com/OCNYang/AnchorImageViewDemo/raw/master/imgs/demo.gif)

# 使用方法

## 引入

**Step 1. Add the JitPack repository to your build file**  

Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
	
**Step 2. Add the dependency**  

```
dependencies {
        implementation 'com.github.OCNYang:AnchorImageViewDemo:v1.0'
}
```


## 具体使用方法

> 请参考 Demo 源码

# 加载图片不显示问题

1. 在 res 文件夹下创建一个xml文件夹，然后创建一个 network_security_config.xml 文件，文件内容如下：

    > 已经在库中提供，可以直接使用
    
    ```
    <?xml version="1.0" encoding="utf-8"?>
    <network-security-config>
        <base-config cleartextTrafficPermitted="true" />
    </network-security-config>
    ```

2. 接着，在 AndroidManifest.xml 文件下的 application 标签增加以下属性：

    ```
    <application
    ...
     android:networkSecurityConfig="@xml/network_security_config"
    ...
        />
    ```