/*
 * Copyright (C) 2020 Hank Adler
 */

package com.hankadler.util

/**
 * Functions for runtime call stack inspection.
 *
 * @author   hank@hankadler.com
 * @version  0.1.0
 * @license  MIT
 *
 * @property units List of callable units in chronological order.
 * @property classNames List of callable units canonical class names in chronological order.
 */
object CallStack {
    // @Properties
    val units get() = Thread.currentThread().stackTrace.reversed()
    val classNames get() = units.map {it.className.replace("$", ".")}.toSet().toList()

    // @Methods
    /**
     * Prints an indexed list of classes in chronological order.
     */
    fun printClassNames() {
        for (i in classNames.indices) {
            println("[$i]: ${classNames[i]}")
        }
    }

    /**
     * Returns the class name of the caller of the current unit.
     */
    fun getCallerOf(className: String?): String? {
        if (className == null) {
            return null
        }
        val index = classNames.indexOf(className)
        if (index != -1 && index > 0) {
            return classNames[index - 1]
        }
        return null
    }

    override fun toString(): String {
        var result = ""
        for (i in units.indices) {
            result += "[$i]: ${units[i]}\n"
        }
        return result
    }
}