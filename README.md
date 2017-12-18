## Android StyleableToast

An Android library that takes the standard Android Toast to the next level with many styling options that gives your app and user experience an extra unique feeling! Style your Toast either by code or in styles.xml!

<a href="https://github.com/Muddz/StyleableToast/raw/master/demo.apk">Download the demo.apk</a>


## Features

- Style toasts in a styles.xml or from code.
- Set background color of the toast.
- Set the corner radius of the toast for different shapes.
- Set full solid for the toast background.
- Set a stroke color and width of your toast.
- Style the toast text with a text color, bold style or text size.
- Set a custom font for the toast text.
- Set a icon next to the toast text on either the right or left side.
- Set an spinning animation effect on your icon (see below example)
- Supports RTL
- Works from Api 16+


## Update version: 2.0.0  **IMPORTANT** |  18 December 2017

!! READ THE CHANGES FROM `1.0.9` TO `2.0.0` BEFORE UPDATING

- Support for RTL phones
- added setIconLeft() & setIconRight()
- added setTextSize()
- added the new styles.xml attribute. See below!
- added new makeText() constructor without length
- replaced setAlpha() with setSolidBackground()
- replaced setStrokeWidth() and setStrokeColor() with setStroke(width,color)
- Removed getToast() and build()
- Removed 4 lines text limit
- Adjusted the default paddings and values for the toast
- Updated depedencies
- Over all refactoring

## CASES:
![alt tag](https://github.com/Muddz/StyleableToast/blob/master/showcase.png)

## With spinIcon(); method:
![alt tag](https://media.giphy.com/media/hoq66naJQkECI/giphy.gif)


## Usage with a style from styles.xml:

**1) Style your toast in styles.xml. All available attributes:**
```xml
        <item name="textBold">true</item>
        <item name="textColor">#fff</item>
        <item name="textFont">fonts/dosis.otf</item>
        <item name="textSize">14sp</item>
        <item name="colorBackground">#fff</item>
        <item name="solidBackground">true</item>
        <item name="strokeWidth">3dp</item>     API 21+
        <item name="strokeColor">#fff</item>    API 21+
        <item name="iconLeft">@drawable/ic</item>
        <item name="iconRight">@drawable/ic</item>
        <item name="length">LONG</item>         ENUM: LONG | SHORT
        <item name="cornerRadius">5dp</item>
```

**2) Pass your style in the static constructor and call show(); and you're done!**

```java
    StyleableToast.makeText(context, "Hello World!", R.style.StyledToast).show();
```

## With Builder pattern:
```java
        new StyleableToast
                .Builder(context)
                .text("Hello world!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.BLUE)
                .show();
```

-----
    
## Installation

Add the depedency in your `build.gradle.` The library is distributed via jCenter

```groovy
dependencies {
    implementation 'com.muddzdev:styleabletoast:2.0.0'   
}
```
 ----

## License

    Copyright 2017 Muddii Walid (Muddz)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
