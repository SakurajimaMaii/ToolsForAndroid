/*
 * Copyright 2024 VastGui guihy2019@gmail.com
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

package com.ave.vastgui.app.log

import com.ave.vastgui.tools.log.android
import com.log.vastgui.core.LogFactory
import com.log.vastgui.core.base.LogStore
import com.log.vastgui.core.base.Logger
import com.log.vastgui.core.getLogFactory
import com.log.vastgui.core.json.GsonConverter
import com.log.vastgui.core.plugin.LogJson
import com.log.vastgui.core.plugin.LogPrinter
import com.log.vastgui.core.plugin.LogStorage
import com.log.vastgui.core.plugin.LogSwitch

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2023/7/5
// Documentation: https://ave.entropy2020.cn/documents/VastTools/log/description/

@JvmField
val mLogFactory: LogFactory = getLogFactory {
    install(LogSwitch) {
        open = true
    }
    install(LogPrinter) {
        logger = Logger.android(30)
    }
    install(LogStorage) {
        logStore = LogStore.android()
    }
    install(LogJson){
        converter = GsonConverter(true)
    }
}