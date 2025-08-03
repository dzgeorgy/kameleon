package dev.dzgeorgy.kameleon

import kotlin.reflect.KClass

annotation class MapFrom(
    val source: KClass<*>,
)
