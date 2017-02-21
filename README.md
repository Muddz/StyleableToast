## Android StyleableToast

An Android library that takes the standard Android Toast to the next level with a variety of styling options that gives your app and user experience that little extra unique feeling! Style your toast either by code or in styles.xml!


Currently used in:
- [Quote Creator] (https://play.google.com/store/apps/details?id=org.m.muddzboy.QuoteCreator&hl=da) with +90.000 downloads!
- [Shoppist] (https://play.google.com/store/apps/details?id=com.muddii.shopping_list&hl=da)

*Feel free to contact me if you want your app to be included here*

<a href="https://github.com/Muddz/StyleableToast/raw/master/sample.apk">Download the sample .apk: </a>


## Features

- Style toasts in a styles.xml or from code.
- Set background color of the toast.
- Set the corner radius of the toast and archive different shapes.
- Set the transparency of your toast to get full solid or transparent toast.
- Set stroke width and stroke color on your toast.
- Style the toast text with a text color or bold.
- Set a custom font for the toast text.
- Set a icon beside the toast text.
- Set an spinning animation effect on your icon (see below example)
- Works from Api 16+

## Update version: 1.0.6 |  21 february 2017
- Added builder pattern
- Added cancel(); method
- Fixed argument error in setTextstyle();


## CASES:
![alt tag](https://github.com/Muddz/StyleableToast/blob/master/showcase.png)

## With spinIcon(); method:
![alt tag](https://media.giphy.com/media/hoq66naJQkECI/giphy.gif)


## Usage with a style resource:


**1) Style your toast in styles.xml. All available attributes:**
```xml
    <style name="StyledToast">
    
    <item name="android:textColor"></item>
    <item name="android:textStyle"></item> only bold!
    <item name="android:fontFamily"></item> For custom fonts just add the path -> fonts/myfont.ttf
    <item name="android:colorBackground"></item>
    <item name="android:strokeWidth"></item>   API 21+
    <item name="android:strokeColor"></item>   API 21+
    <item name="android:radius"></item>  radius for corners of the toast shape
    <item name="android:alpha"></item>   value between 0-255 where 255 is full solid
    <item name="android:icon">/</item>  drawable id of the icon. R.drawable.xx
        
    </style>
```

**2) Pass your style resource in the constructor and call show(); and you're done!**

```java
    StyleableToast.makeText(context, "Saving profile", Toast.LENGTH_LONG, R.style.StyledToast).show();
```
## Usage with code:
```java
    StyleableToast st = new StyleableToast(this, "Updating profile", Toast.LENGTH_SHORT);
    st.setBackgroundColor(Color.parseColor("#ff5a5f"));
    st.setTextColor(Color.WHITE);
    st.setIcon(R.drawable.ic_autorenew_black_24dp);
    st.spinIconAnimation();  
    st.setMaxAlpha();
    st.show();
```

## Or with Builder pattern:
```java
    st = new StyleableToast
            .Builder(this, "Turn off fly mode")
            .withBackgroundColor(Color.RED)
            .withTextColor(Color.WHITE)
            .withBoldText()
            .build();
```

-----
    
## Installation

Add the depedency in your build.gradle. The library is distributed via jCenter

```groovy
dependencies {
    compile 'com.muddzdev:styleabletoast:1.0.6'   
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
