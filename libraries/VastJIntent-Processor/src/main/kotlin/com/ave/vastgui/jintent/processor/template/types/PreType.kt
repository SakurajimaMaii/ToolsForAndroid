/*
 * Copyright 2022 VastGui guihy2019@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ave.vastgui.jintent.processor.template.types

import com.ave.vastgui.jintent.processor.utils.ClassType

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2023/4/30
// Description: 
// Documentation:
// Reference:

val ACTIVITYRESULTLAUNCHER =
    ClassType(
        "androidx.activity.result.ActivityResultLauncher",
        ClassType("android.content.Intent")
    )
val JINTENTREGISTER = ClassType("com.ave.vastgui.jintent.JIntentRegister")
val ACTIVITY = ClassType("android.app.Activity")
val BUNDLEUTILS = ClassType("com.ave.vastgui.jintent.utils.BundleUtils")
val BUNDLE = ClassType("android.os.Bundle")
val INTENT = ClassType("android.content.Intent")
val CONTEXT = ClassType("android.content.Context")