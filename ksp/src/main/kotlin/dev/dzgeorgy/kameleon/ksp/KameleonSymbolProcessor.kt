package dev.dzgeorgy.kameleon.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import dev.dzgeorgy.kameleon.MapTo
import dev.dzgeorgy.kameleon.ksp.generators.MapToGenerator
import dev.dzgeorgy.kameleon.ksp.visitors.MapToAnnotationVisitor

class KameleonSymbolProcessor(
    logger: KSPLogger,
    codeGenerator: CodeGenerator
) : SymbolProcessor {

    private val mapToGenerator = MapToGenerator(logger, codeGenerator)
    private val mapToVisitor = MapToAnnotationVisitor(logger)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val mappers = resolver.getSymbolsWithAnnotation(MapTo::class.qualifiedName!!).map {
            it.accept(mapToVisitor, Unit)
        }
        mapToGenerator.generate(mappers)
        return emptyList()
    }

}
