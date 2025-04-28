package dev.dzgeorgy.kameleon

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
public annotation class Mapper(
    val mapper: KClass<CustomMapper<*, *>>
)

public interface CustomMapper<From: Any, To: Any>
