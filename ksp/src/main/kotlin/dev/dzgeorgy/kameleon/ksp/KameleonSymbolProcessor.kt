package dev.dzgeorgy.kameleon.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import dev.dzgeorgy.kameleon.MapTo
import dev.dzgeorgy.kameleon.ksp.visitors.MapToAnnotationVisitor

class KameleonSymbolProcessor(
    private val logger: KSPLogger, private val codeGenerator: CodeGenerator
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val mapToVisitor = MapToAnnotationVisitor(logger)
        resolver.getSymbolsWithAnnotation(MapTo::class.qualifiedName!!).forEach {
            it.accept(mapToVisitor, Unit)
        }
        return emptyList()
    }

}
