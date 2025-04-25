package dev.dzgeorgy.kameleon

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
public annotation class MapTo(
    val target: KClass<*>
)
