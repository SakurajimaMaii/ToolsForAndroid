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

package com.ave.vastgui.tools.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources.NotFoundException
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.ave.vastgui.tools.helper.ContextHelper


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/6/19
// Documentation: https://ave.entropy2020.cn/documents/VastTools/core-topics/app-resources/ResUtils/

object ResUtils {

    /** Resources not found tag. */
    const val RESOURCES_NOT_FOUND = 0

    /** Error color hex string. */
    private const val ERROR_COLOR = "#c0392b"

    /** Get string by [id]. */
    @JvmStatic
    @JvmOverloads
    fun getString(@StringRes id: Int, context: Context = ContextHelper.getAppContext()): String =
        context.resources.getString(id)

    /**
     * Get drawable by [name].
     *
     * @param name the name of the drawable.
     * @return the drawable resource corresponding to the [name], otherwise
     *     [callback].
     */
    @JvmStatic
    @JvmOverloads
    fun getDrawable(
        name: String,
        callback: Drawable,
        context: Context = ContextHelper.getAppContext()
    ): Drawable {
        return getDrawable(name, context) ?: callback
    }

    /**
     * Get drawable by [name].
     *
     * @param name the name of the drawable.
     * @return the drawable resource corresponding to the [name], otherwise
     *     null if the resource does not exist.
     */
    @SuppressLint("DiscouragedApi")
    @JvmStatic
    @JvmOverloads
    fun getDrawable(name: String, context: Context = ContextHelper.getAppContext()): Drawable? {
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (RESOURCES_NOT_FOUND == resId) {
            null
        } else {
            ResourcesCompat.getDrawable(context.resources, resId, context.theme)
        }
    }

    /**
     * Get drawable by [resId].
     *
     * @param resId the resource id of the drawable.
     * @return the drawable resource corresponding to the [resId], otherwise
     *     [callback].
     */
    @JvmStatic
    @JvmOverloads
    fun getDrawable(
        @DrawableRes resId: Int,
        callback: Drawable,
        context: Context = ContextHelper.getAppContext()
    ): Drawable {
        return getDrawable(resId, context) ?: callback
    }

    /**
     * Get drawable by [resId].
     *
     * @param resId the resource id of the drawable.
     * @return the drawable resource corresponding to the [resId], otherwise
     *     null if the resource does not exist.
     */
    @JvmStatic
    @JvmOverloads
    fun getDrawable(
        @DrawableRes resId: Int,
        context: Context = ContextHelper.getAppContext()
    ): Drawable? {
        return try {
            ResourcesCompat.getDrawable(context.resources, resId, context.theme)
        } catch (ex: NotFoundException) {
            null
        }
    }

    /**
     * A single color value in the form 0xAARRGGBB. Or color int of
     * [ERROR_COLOR] will be return if resource is not exist.
     *
     * @param id the resource id of color.
     */
    @JvmStatic
    @JvmOverloads
    fun getColor(@ColorRes id: Int, context: Context = ContextHelper.getAppContext()): Int {
        return try {
            context.getColor(id)
        } catch (e: NotFoundException) {
            ColorUtils.colorHex2Int(ERROR_COLOR)
        }
    }

    /**
     * Retrieve a dimensional for a particular resource ID for use as an offset
     * in raw pixels. The returned value is converted to integer pixels for
     * you. An offset conversion involves simply truncating the base value to
     * an integer. Otherwise 0 will be return.
     *
     * @param id the resource id of dimension.
     */
    @JvmStatic
    @JvmOverloads
    fun getDimensionPixelOffset(
        @DimenRes id: Int,
        context: Context = ContextHelper.getAppContext()
    ): Int {
        return try {
            context.resources.getDimensionPixelOffset(id)
        } catch (ex: NotFoundException) {
            0
        }
    }

}