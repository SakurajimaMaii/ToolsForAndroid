/*
 * Copyright 2021-2024 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.log.vastgui.desktop.format

import com.log.vastgui.core.base.LogFormat
import com.log.vastgui.core.base.LogFormat.Companion.timeSdf
import com.log.vastgui.core.base.LogInfo
import com.log.vastgui.core.base.LogLevel
import com.log.vastgui.desktop.base.Blue
import com.log.vastgui.desktop.base.Cyan
import com.log.vastgui.desktop.base.Gray
import com.log.vastgui.desktop.base.Green
import com.log.vastgui.desktop.base.Purple
import com.log.vastgui.desktop.base.Red
import com.log.vastgui.desktop.base.Reset
import com.log.vastgui.desktop.base.White
import com.log.vastgui.desktop.base.Yellow

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/6/23
// Description: 
// Documentation:
// Reference:

/**
 * Line format with colorful style.
 *
 * @since 1.3.4
 */
object LineColorfulFormat : LogFormat {

    override fun format(logInfo: LogInfo): String {
        val time = timeSdf.format(logInfo.mTime)
        return "$Cyan$time$Reset ${logInfo.headColor()}[${logInfo.mLevel}|${logInfo.mTag}|${logInfo.mThreadName}]$Reset $Blue(${logInfo.mStackTrace?.fileName}:${logInfo.mStackTrace?.lineNumber})$Reset ${logInfo.mContent}"
    }

    /**
     * Get the head color by [LogInfo.mLevel].
     *
     * @since 1.3.4
     */
    private fun LogInfo.headColor() = when (mLevel) {
        LogLevel.VERBOSE -> Gray
        LogLevel.DEBUG -> Blue
        LogLevel.INFO -> Green
        LogLevel.WARN -> Yellow
        LogLevel.ERROR -> Red
        LogLevel.ASSERT -> Purple
        else -> White
    }

}