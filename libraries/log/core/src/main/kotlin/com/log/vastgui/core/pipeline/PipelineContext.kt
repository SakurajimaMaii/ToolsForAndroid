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

package com.log.vastgui.core.pipeline

// Author: ywnkm
// Email: https://github.com/ywnkm
// Date: 2024/6/22
// Documentation: https://ave.entropy2020.cn/documents/log/log-core/advanced/advanced/

/**
 * [PipelineContext].
 *
 * @since 1.3.4
 */
abstract class PipelineContext<TSubject : Any, TContext : Any>(val context: TContext) {

    /**
     * @since 1.3.4
     */
    abstract var subject: TSubject

    /**
     * @since 1.3.4
     */
    abstract fun finish()

    /**
     * @since 1.3.4
     */
    abstract fun proceedWith(subject: TSubject): TSubject

    /**
     * @since 1.3.4
     */
    abstract fun proceed(): TSubject

    /**
     * @since 1.3.4
     */
    internal abstract fun execute(initial: TSubject): TSubject

}