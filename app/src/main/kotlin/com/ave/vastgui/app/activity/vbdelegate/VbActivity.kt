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

package com.ave.vastgui.app.activity.vbdelegate

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import com.ave.vastgui.app.R
import com.ave.vastgui.app.databinding.ActivityVbBinding
import com.ave.vastgui.tools.viewbinding.viewBinding

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2023/8/11
// Documentation: https://ave.entropy2020.cn/documents/tools/architecture-components/ui-layer-libraries/view-bind/vb-delegate/#activity

class VbActivity1 : FragmentActivity() {

    private val mBinding by viewBinding(ActivityVbBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment, VbFragment1(), null)
            .commitNow()
    }

}

class VbActivity2 : Activity() {

    private val viewBindingProperty = viewBinding(ActivityVbBinding::inflate)
    private val mBinding by viewBindingProperty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBindingProperty.clear()
    }

}

class VbActivity3 : ComponentActivity(R.layout.activity_vb) {

    private val mBinding by viewBinding(ActivityVbBinding::bind)

}

class VbActivity4 : ComponentActivity(R.layout.activity_vb) {

    /**
     * root 是 ActivityVbBinding 的根布局 id
     */
    private val mBinding by viewBinding(ActivityVbBinding::bind, R.id.root)

}