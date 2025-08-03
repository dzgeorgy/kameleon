package dev.dzgeorgy.kameleon

import kotlin.reflect.KClass

annotation class MapTo(
    val target: KClass<*>,
)