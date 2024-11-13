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

package com.ave.vastgui.core.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/11/13
// Description: Suspend a coroutine with a timeout.
// Reference: https://stackoverflow.com/questions/48974369/is-it-possible-to-suspend-a-coroutine-with-a-timeout

/**
 * A suspendCancellableCoroutine with timeout extension.
 *
 * @since 0.1.3
 */
suspend inline fun <T : Any> suspendCoroutineWithTimeout(timeout: Long, crossinline block: (CancellableContinuation<T>) -> Unit): T? =
    withTimeoutOrNull(timeout) {
        suspendCancellableCoroutine(block = block)
    }