package dev.dzgeorgy.kameleon

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
public annotation class Mapper

public interface CustomMapper<in From : Any, out To : Any>
