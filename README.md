# Giphy Browser

[![GitHub Actions](https://github.com/naveenkumarn27/giphy-browser/workflows/workflow/badge.svg)](.github/workflows/workflow.yml)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

GIPHY Browser is a **work-in-progress** Android app for viewing gif's and stickers using GIPHY api.

It displays the latest gif's and stickers depending on the search query. It makes use of the [GIPHY API](https://developers.giphy.com/) in order to show gif's and stickers.

<br>

| Search | Search List | Image | Dark Theme |
| ------ | ----- | ------ | ------ |
| ![Search](art/screenshots/screenshot-1.png) | ![Search List](art/screenshots/screenshot-2.png) | ![Image](art/screenshots/screenshot-3.png) | ![Dark Theme](art/screenshots/screenshot-4.png) |

## Android development

Giphy Browser attempts to make use of the latest Android libraries and best practices:
* Entirely written in [Kotlin](https://kotlinlang.org/) (including [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)) with [ktlint](https://github.com/pinterest/ktlint) for code style
* Makes use of [Android Jetpack](https://developer.android.com/jetpack/):
  * [Architecture Components](https://developer.android.com/jetpack/arch/) including **ViewModel**, **LiveData**, **Paging**, and **Navigation**
  * [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout), [View Binding](https://developer.android.com/topic/libraries/view-binding) and more for layouts and UI
  * [Android KTX](https://developer.android.com/kotlin/ktx) for more fluent use of Android APIs
  * [Navigation](https://developer.android.com/guide/navigation) to make the navigation easy
  * [Data-Store](https://developer.android.com/topic/libraries/architecture/datastore) to store data asynchronously
* [Retrofit](https://square.github.io/retrofit/)/[OkHttp](https://square.github.io/okhttp/) for networking
* [Glide](https://github.com/bumptech/glide) for image loading
* [Hilt-Android](https://dagger.dev/hilt/) for dependency injection
* Designed and built using Material Design [components](https://material.io/components/) and [theming](https://material.io/design/material-theming/overview.html#material-theming)
* Full [dark theme](https://material.io/design/color/dark-theme.html) support

## Contributions

Please feel free to file an issue for errors, suggestions or feature requests. Pull requests are also encouraged.

## License

```
Copyright 2021 Naveen Kumar Kuppan

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
