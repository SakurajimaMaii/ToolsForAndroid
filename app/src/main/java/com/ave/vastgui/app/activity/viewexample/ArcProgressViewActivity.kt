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

package com.ave.vastgui.app.activity.viewexample

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.ave.vastgui.app.databinding.ActivityArcProgressViewBinding
import com.ave.vastgui.appcompose.example.log.mLogFactory
import com.ave.vastgui.tools.activity.VastVbActivity
import com.ave.vastgui.tools.manager.filemgr.FileMgr
import com.ave.vastgui.tools.utils.ColorUtils
import com.ave.vastgui.tools.utils.download.DLManager
import com.ave.vastgui.tools.utils.download.DLTask
import com.ave.vastgui.tools.view.extension.refreshWithInvalidate

// Author: Vast Gui 
// Email: guihy2019@gmail.com
// Date: 2022/4/14 18:42
// Description:
// Documentation:

class ArcProgressViewActivity : VastVbActivity<ActivityArcProgressViewBinding>() {

    private val logger = mLogFactory.getLog(ArcProgressViewActivity::class.java)
    private lateinit var downloadTask: DLTask

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val colors = intArrayOf(
            ColorUtils.colorHex2Int("#F60C0C"),
            ColorUtils.colorHex2Int("#F3B913"),
            ColorUtils.colorHex2Int("#E7F716"),
            ColorUtils.colorHex2Int("#3DF30B"),
            ColorUtils.colorHex2Int("#0DF6EF"),
            ColorUtils.colorHex2Int("#0829FB"),
            ColorUtils.colorHex2Int("#B709F4")
        )
        val pos = floatArrayOf(
            1f / 7,
            2f / 7,
            3f / 7,
            4f / 7,
            5f / 7,
            6f / 7,
            1f
        )

//        val colors = intArrayOf(
//            ColorUtils.colorHex2Int("#ED4C67"),
//            ColorUtils.colorHex2Int("#FFFFFF"),
//        )
//        val pos = floatArrayOf(
//            0.3f,
//            1f
//        )

        getBinding().downloadCv.apply {
            setProgressShader(
                LinearGradient(
                    -700f, 0f, 700f, 0f,
                    colors, pos,
                    Shader.TileMode.CLAMP
                )
            )
        }

        getBinding().download.setOnClickListener {
            downloadApk()
        }

        getBinding().pause.setOnClickListener {
            downloadTask.pause()
        }

        getBinding().resume.setOnClickListener {
            downloadTask.resume()
        }

        getBinding().cancel.setOnClickListener {
            downloadTask.cancel()
        }
    }

    private fun downloadApk() {
        downloadTask = DLManager
            .createTaskConfig()
            .setDownloadUrl("https://down.oray.com/sunlogin/windows/SunloginClient_13.3.1.56398_x64.exe")
            .setSaveDir(FileMgr.appInternalFilesDir().path)
            .setListener {
                onDownloading = {
                    getBinding().downloadCv.refreshWithInvalidate {
                        setCurrentProgress(
                            it.rate * getBinding().downloadCv.mMaximumProgress
                        )
                    }
                }
                onFailure = {
                    logger.e("download failed:" + it.exception.stackTraceToString())
                }
                onSuccess = {
                    logger.i("download success.")
                    getBinding().downloadCv.refreshWithInvalidate {
                        setCurrentProgress(getBinding().downloadCv.mMaximumProgress)
                    }
                }
                onCancel = {
                    logger.i("download cancel.")
                    getBinding().downloadCv.refreshWithInvalidate {
                        resetProgress()
                    }
                }
            }
            .build()

        downloadTask.start()
    }

}