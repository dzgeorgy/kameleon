package dev.dzgeorgy.kameleon.ksp.models

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType

data class MapToAnnotationData(
    val target: KSClassDeclaration
)

data class MapperData(
    val source: KSClassDeclaration,
    val target: KSClassDeclaration,
    val mappers: Map<ParameterData, PropertyData>
)

data class PropertyData(
    override val name: String,
    override val type: KSType
) : BaseData()

data class ParameterData(
    override val name: String,
    override val type: KSType
) : BaseData()

abstract class BaseData {
    abstract val name: String
    abstract val type: KSType
}
