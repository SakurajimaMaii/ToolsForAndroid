/*
 * Copyright 2021-2024 VastGui
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

package com.ave.vastgui.tools.view.ratingview

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2023/4/21

/**
 * Rating orientation
 *
 * @since 0.5.3
 */
sealed class StarOrientation(val code: Int) {
    internal data object UNSPECIFIED : StarOrientation(0)
    data object HORIZONTAL : StarOrientation(1)
    data object VERTICAL : StarOrientation(2)
}