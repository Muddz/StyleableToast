### Android StyleableToast

An Android library that takes the standard Android Toast and takes it to the next level with a variety of styling options that gives your app a extra unique feeling.


Currently used in:
- [Quote Creator] (https://play.google.com/store/apps/details?id=org.m.muddzboy.QuoteCreator&hl=da) with +80.000 downloads!
- [Shoppist] (https://play.google.com/store/apps/details?id=com.muddii.shopping_list&hl=da)

*Feel free to contact me if you want your app to be included here*

### Features

- Set background color of the toast.
- Set the corner radius of the toast get shapes like, flat, slightly rounded square or rounded.
- Set the alpha/transparency of your toast.
- Set stroke width and color on your toast.
- Style the toast text with a text color or bold.
- Set a new font for the toast text.
- Set a icon beside the toast text.
- Define all setting in a styles.xml and just pass the style in the constructor.
- Start a spinning animation of your icon (see example below)

*(Next version: Builder pattern, elevtion and toast size)*

----

### Simple usage with a style resource:


**1) Style your toast in styles.xml. All available attributes:**

    <style name="StyledToast">

        <item name="android:textColor"></item>
        <item name="android:textStyle"></item>
        <item name="android:fontFamily"></item>
        <item name="android:colorBackground"></item>
        <item name="android:strokeWidth"></item>
        <item name="android:strokeColor"></item>
        <item name="android:radius"></item>
        <item name="android:alpha"></item>
        <item name="android:icon">/</item>
        
    </style>

**2) Pass your style resource in the constructor and call show();**

    StyleableToast.makeText(context, "Saving profile", Toast.LENGTH_LONG, R.style.StyledToast).show();
    
### Simple usage with a style resource:
-----
    
### Installation

Add the depedency in your build.gradle. The library is distributed via jCenter

```groovy
dependencies {
    compile 'com.muddzdev:styleabletoast:1.0.1'   
}
```
 ----

### License

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
