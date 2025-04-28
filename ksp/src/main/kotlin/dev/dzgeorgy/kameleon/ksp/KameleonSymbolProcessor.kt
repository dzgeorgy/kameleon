package dev.dzgeorgy.kameleon.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import dev.dzgeorgy.kameleon.MapTo
import dev.dzgeorgy.kameleon.Mapper
import dev.dzgeorgy.kameleon.ksp.generators.MapToGenerator
import dev.dzgeorgy.kameleon.ksp.visitors.MapToAnnotationVisitor
import dev.dzgeorgy.kameleon.ksp.visitors.MapperAnnotationVisitor
import kotlin.math.log

class KameleonSymbolProcessor(
    logger: KSPLogger,
    codeGenerator: CodeGenerator
) : SymbolProcessor {

    private val mapToGenerator = MapToGenerator(logger, codeGenerator)
    private val mapToVisitor = MapToAnnotationVisitor(logger)
    private val mapperVisitor = MapperAnnotationVisitor(logger)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val customMappers = resolver.getSymbolsWithAnnotation(Mapper::class.qualifiedName!!).map {
            it.accept(mapperVisitor, Unit)
        }
        val mappers = resolver.getSymbolsWithAnnotation(MapTo::class.qualifiedName!!).map {
            it.accept(mapToVisitor, Unit)
        }
        mapToGenerator.generate(mappers + customMappers)
        return emptyList()
    }

}
