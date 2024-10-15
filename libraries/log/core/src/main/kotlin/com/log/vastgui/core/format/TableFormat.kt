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

package com.log.vastgui.core.format

import com.log.vastgui.core.annotation.LogApi
import com.log.vastgui.core.base.LogDivider
import com.log.vastgui.core.base.LogFormat
import com.log.vastgui.core.base.LogFormat.Companion.timeSdf
import com.log.vastgui.core.base.LogInfo
import com.log.vastgui.core.base.cutStr
import com.log.vastgui.core.base.needCut

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/6/20 22:41
// Documentation: https://ave.entropy2020.cn/documents/log/log-core/format/

/**
 * Default maximum length of chars printed of a single log.
 * **Notes:Considering fault tolerance, 1000 is set here instead of 1024.**
 *
 * @since 1.3.4
 */
@LogApi
const val DEFAULT_MAX_SINGLE_LOG_LENGTH = 1000

/**
 * Default max print repeat times.
 *
 * @since 1.3.4
 */
@LogApi
const val DEFAULT_MAX_PRINT_TIMES = Int.MAX_VALUE

/**
 * Table format of [LogInfo].
 *
 * @see <img
 * src=https://github.com/SakurajimaMaii/Android-Vast-Extension/blob/develop/libraries/log/core/image/table_format.png?raw=true>
 * @since 1.3.4
 */
class TableFormat(
    private val mMaxSingleLogLength: Int,
    private val mMaxPrintTimes: Int,
    private val mHeader: LogHeader
) : LogFormat {

    /**
     * Log header configuration.
     *
     * @property thread `true` if you want to show [LogInfo.threadName] in
     * header, `false` otherwise.
     * @property tag `true` if you want to show [LogInfo.tag] in header,
     * `false` otherwise.
     * @property level `true` if you want to show [LogInfo.level] in header,
     * `false` otherwise.
     * @property time `true` if you want to show [LogInfo.time] in header,
     * `false` otherwise.
     * @since 1.3.4
     */
    data class LogHeader(
        val thread: Boolean,
        val tag: Boolean,
        val level: Boolean,
        val time: Boolean
    ) {
        companion object {
            /** @since 1.3.4 */
            val default = LogHeader(thread = true, tag = true, level = true, time = true)
        }
    }

    /** @since 1.3.4 */
    override fun format(logInfo: LogInfo): String = textFormat(logInfo)

    /**
     * Print [logInfo].
     *
     * @since 1.3.4
     */
    private fun textFormat(logInfo: LogInfo) =
        // The length of the log content is less than mMaxSingleLogLength
        if (!logInfo.needCut(mMaxSingleLogLength)) {
            logFormat(logInfo) { body, content ->
                // FIX: DEAL LINE SEPARATOR THAT EXIST WITHIN THE LOG CONTENT
                val patterns = content.split("\n", System.lineSeparator())
                patterns.forEach { pattern ->
                    body.appendLine(LogDivider.getInfo(pattern))
                }
            }
        }
        // The length of the log content is greater than mMaxSingleLogLength
        else {
            // Segment printing count
            var count = 0
            var printTheRest = true
            logFormat(logInfo, mMaxSingleLogLength * 4) { body, content ->
                // FIX: DEAL LINE SEPARATOR THAT EXIST WITHIN THE LOG CONTENT
                val patterns = content.split("\n", System.lineSeparator())
                patterns.forEach { pattern ->
                    var bytes = pattern.toByteArray()
                    if (mMaxSingleLogLength * 4 < bytes.size) {
                        do {
                            val subStr = bytes.cutStr(mMaxSingleLogLength)
                            body.appendLine(LogDivider.getInfo(String.format("%s", subStr)))
                            bytes = bytes.copyOfRange(subStr.toByteArray().size, bytes.size)
                            count++
                            if (count == mMaxPrintTimes) {
                                printTheRest = false
                                break
                            }
                        } while (mMaxSingleLogLength * 4 < bytes.size)
                    } else {
                        count++
                    }

                    if (printTheRest && count <= mMaxPrintTimes) {
                        body.appendLine(LogDivider.getInfo(String.format("%s", String(bytes))))
                    }
                }
            }
        }

    /**
     * Print log.
     *
     * @since 1.3.4
     */
    private inline fun logFormat(
        logInfo: LogInfo,
        len: Int = logInfo.printBytesLength,
        customScope: (StringBuilder, String) -> Unit
    ) = StringBuilder(logInfo.content.length * 4).apply {
        // It makes no sense to print a separator that is too long.
        val length = len.coerceAtMost(100)
        appendLine(LogDivider.getTop(length))
        val thread = if (mHeader.thread) "Thread: ${logInfo.threadName}" else ""
        val tag = if (mHeader.tag) "Tag: ${logInfo.tag}" else ""
        val level = if (mHeader.level) "Level: ${logInfo.level}" else ""
        val time = if (mHeader.time) "Time: ${timeSdf.format(logInfo.time)}" else ""
        appendLine(LogDivider.getInfo("$thread $tag $level $time"))
        appendLine(LogDivider.getDivider(length))
        appendLine(LogDivider.getInfo("${logInfo.stackTrace}"))
        appendLine(LogDivider.getDivider(length))
        customScope(this, logInfo.content)
        logInfo.throwable?.apply {
            appendLine(LogDivider.getDivider(length))
            appendLine(LogDivider.getInfo("$this"))
            for (item in this.stackTrace) {
                appendLine(LogDivider.getInfo("  at $item"))
            }
        }
        append(LogDivider.getBottom(length))
    }.toString()
}