package dev.dzgeorgy.kameleon.ksp.utils

import com.google.devtools.ksp.symbol.KSClassDeclaration
import dev.dzgeorgy.kameleon.ksp.models.ParameterData
import dev.dzgeorgy.kameleon.ksp.models.PropertyData

internal fun KSClassDeclaration.getParameters(): List<ParameterData> {
    return this.primaryConstructor?.parameters?.map { parameter ->
        ParameterData(
            name = parameter.name!!.asString(),
            type = parameter.type.resolve(),
        )
    } ?: emptyList()
}

internal fun KSClassDeclaration.getProperties(): List<PropertyData> {
    return this.getAllProperties().map { property ->
        PropertyData(
            name = property.simpleName.asString(),
            type = property.type.resolve(),
        )
    }.toList()
}

internal fun matchParametersToProperties(
    parameters: List<ParameterData>,
    properties: List<PropertyData>
): Map<ParameterData, PropertyData> {
    return parameters.associateWith { parameter ->
        properties.first { property ->
            parameter.name == property.name &&
                    parameter.type.isAssignableFrom(property.type)
        }
    }
}
