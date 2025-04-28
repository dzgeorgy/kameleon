package dev.dzgeorgy.kameleon.ksp.visitors

import com.google.devtools.ksp.closestClassDeclaration
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSTopDownVisitor
import dev.dzgeorgy.kameleon.ksp.models.MapperData
import dev.dzgeorgy.kameleon.ksp.utils.getParameters
import dev.dzgeorgy.kameleon.ksp.utils.getProperties
import dev.dzgeorgy.kameleon.ksp.utils.matchParametersToProperties

class MapperAnnotationVisitor(
    private val logger: KSPLogger
) : KSTopDownVisitor<Unit, MapperData>() {

    override fun defaultHandler(node: KSNode, data: Unit): MapperData {
        throw IllegalStateException("Unexpected node type: ${node::class.simpleName}")
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit): MapperData {
        logger.info("Processing custom mapper: ${classDeclaration.qualifiedName?.asString()}", classDeclaration)
        val classDeclarationData = classDeclaration.superTypes.first().element!!.typeArguments
        val source = classDeclarationData[0].type!!.resolve().declaration.closestClassDeclaration()!!
        val target = classDeclarationData[1].type!!.resolve().declaration.closestClassDeclaration()!!
        val mapperData = MapperData(
            source,
            target,
            mappers = matchParametersToProperties(
                target.getParameters(),
                source.getProperties(),
            ),
        )
        logger.info("Mapper data: $mapperData", classDeclaration)
        return mapperData
    }

}
