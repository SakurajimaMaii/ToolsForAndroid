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

package cn.govast.vasttools.fragment.delegate

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import cn.govast.vasttools.lifecycle.reflexViewModel
import cn.govast.vasttools.viewbinding.reflexViewBinding

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/10/6
// Description: 
// Documentation:
// Reference:

open class FragmentVbVmDelegate<VB : ViewBinding, VM : ViewModel>(
    fragment: Fragment,
) : FragmentDelegate(fragment) {

    // ViewBinding
    private val mBinding: VB by lazy {
        fragment.reflexViewBinding()
    }

    // ViewModel
    private val mViewModel: VM by lazy {
        fragment.reflexViewModel(setVmBySelf()){
            return@reflexViewModel createViewModel(it)
        }
    }

    override fun getBinding(): VB {
        return mBinding
    }

    override fun getViewModel(): VM {
        return mViewModel
    }

}