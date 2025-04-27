package dev.dzgeorgy.kameleon.ksp.generators

import com.google.devtools.ksp.getVisibility
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ksp.toKModifier
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.dzgeorgy.kameleon.ksp.models.MapperData

class MapToGenerator(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) {

    fun generate(mappers: Sequence<MapperData>) {
        mappers.groupBy { it.source }.forEach { (source, mappersData) ->
            logger.info("Generating mappers for: $source", source)
            val fileBuilder = FileSpec.builder(source.packageName.asString(), "${source.simpleName.asString()}Mappers")
            val mappers: List<FunSpec> = mappersData.map { (_, target, mapping) ->
                logger.info("Generating mapper for: $target", source)
                FunSpec.builder("to${target.simpleName.asString()}")
                    .addModifiers(source.getVisibility().toKModifier()!!)
                    .receiver(source.asStarProjectedType().toTypeName())
                    .returns(target.asStarProjectedType().toTypeName())
                    .addCode(
                        mapping
                            .map { (from, to) -> "${from.name} = ${to.name}" }
                            .joinToString(
                                prefix = "return ${target.simpleName.asString()}(",
                                separator = ", ",
                                postfix = ")",
                            ),
                    )
                    .build()
            }
            fileBuilder
                .addFunctions(mappers)
                .build()
                .writeTo(codeGenerator, false)
        }
    }

}
