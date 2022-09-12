/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The VastMenuFab is base on Clans/FloatingActionButton,please see
 *
 *     https://github.com/Clans/FloatingActionButton
 *
 * Thanks a lot for this project.
 */

package com.gcode.vastmenufab

import android.content.res.Resources

internal object Util {

    @JvmStatic
    fun dp2px(dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale
    }

}