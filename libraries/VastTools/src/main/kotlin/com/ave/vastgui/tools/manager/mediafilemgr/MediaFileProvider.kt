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

package com.ave.vastgui.tools.manager.mediafilemgr

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/11/2
// Description: 
// Documentation:
// Reference:

/** Using to provide information about media file. */
@JvmDefaultWithCompatibility
interface MediaFileProvider {
    fun getDefaultRootDirPath(type: SupportMediaType): String?

    fun getDefaultFileName(type: SupportMediaType): String

    /**
     * Get file by [uri].
     *
     * @return file, null otherwise.
     */
    fun getFileByUri(uri: Uri): File?

    fun getFileUriAboveApi30(file: File): Uri?

    @RequiresApi(Build.VERSION_CODES.R)
    fun getFileUriAboveApi30(saveOptions: Map<String, String>.() -> Unit): Uri?

    /**
     * Get the uri by [file].
     *
     * Please register a provider in AndroidManifest.xml. For example:
     * ```xml
     * <provider
     *      android:name="androidx.core.content.FileProvider"
     *      android:authorities="${applicationId}"
     *      android:exported="false"
     *      android:grantUriPermissions="true">
     *      <meta-data
     *          android:name="android.support.FILE_PROVIDER_PATHS"
     *          android:resource="@xml/file_paths" />
     * </provider>
     * ```
     *
     * @param authority By default, the value is the app package name.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun getFileUriAboveApi24(file: File, authority: String?): Uri

    /** Get the uri by [file]. */
    fun getFileUriOnApi23(file: File): Uri

}