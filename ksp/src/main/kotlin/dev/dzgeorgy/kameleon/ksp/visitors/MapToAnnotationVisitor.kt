package dev.dzgeorgy.kameleon.ksp.visitors

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.closestClassDeclaration
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.visitor.KSTopDownVisitor
import dev.dzgeorgy.kameleon.MapTo
import dev.dzgeorgy.kameleon.ksp.models.MapToAnnotationData
import dev.dzgeorgy.kameleon.ksp.models.MapperData
import dev.dzgeorgy.kameleon.ksp.utils.getParameters
import dev.dzgeorgy.kameleon.ksp.utils.getProperties
import dev.dzgeorgy.kameleon.ksp.utils.matchParametersToProperties

class MapToAnnotationVisitor(
    private val logger: KSPLogger
) : KSTopDownVisitor<Unit, MapperData>() {

    override fun defaultHandler(node: KSNode, data: Unit): MapperData {
        throw IllegalStateException("Unexpected node type: ${node::class.simpleName}")
    }

    @OptIn(KspExperimental::class)
    override fun visitClassDeclaration(source: KSClassDeclaration, data: Unit): MapperData {
        logger.info("Processing class: ${source.qualifiedName?.asString()}", source)
        logger.info("Has annotation: ${source.isAnnotationPresent(MapTo::class)}", source)
        val mapToAnnotationData = source.annotations.first().getAnnotationData()
        val mapperData = MapperData(
            source,
            mapToAnnotationData.target,
            mappers = matchParametersToProperties(
                mapToAnnotationData.target.getParameters(),
                source.getProperties(),
            ),
        )
        logger.info("Mapper data: $mapperData", source)
        return mapperData
    }

    private fun KSAnnotation.getAnnotationData(): MapToAnnotationData {
        return MapToAnnotationData(
            target = (this.arguments.first().value as KSType).declaration.closestClassDeclaration()!!,
        )
    }

}
