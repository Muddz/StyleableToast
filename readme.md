# StyleableToast
[![](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)
[![APK](https://img.shields.io/badge/Download-Demo-brightgreen.svg)](https://github.com/Muddz/StyleableToast/raw/master/demo.apk)

An Android library that takes the standard toast to the next level with many styling options. Style your toasts either by code or with a style in `styles.xml`. See examples below or try the demo app.



## Cases:

<img src="https://github.com/Muddz/StyleableToast/blob/master/cases.png" width="75%">


## Example with a style from styles.xml

1) Define a style. All available attributes:
```xml
    <style name="mytoast">
        <item name="textBold">true</item>
        <item name="textColor">#fff</item>
        <item name="font">@font/retrofont</item>
        <item name="textSize">14sp</item>
        <item name="colorBackground">#fff</item>
        <item name="solidBackground">true</item>
        <item name="strokeWidth">3dp</item>
        <item name="strokeColor">#fff</item>
        <item name="iconStart">@drawable/ic</item>
        <item name="iconEnd">@drawable/ic</item>
        <item name="length">LONG</item> LONG or SHORT
        <item name="gravity">top</item> top or center
        <item name="radius">5dp</item>
    </style>

```

2) Pass your style in the static constructor and call `show();` and you're done!

```java
    StyleableToast.makeText(context, "Hello World!", Toast.LENGTH_LONG, R.style.mytoast).show();
```

## Example with builder pattern:
```java
        new StyleableToast
                .Builder(context)
                .text("Hello world!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.BLUE)
                .show();
```

    
## Installation

Add the dependency in your `build.gradle`
```groovy
dependencies {
    implementation 'com.muddzdev:styleabletoast:2.1.3'   
}
```

## License

    Copyright 2016 Muddi Walid

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
