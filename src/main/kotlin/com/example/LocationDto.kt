package com.example

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Introspected
data class LocationDto(
    @field:NotNull
    val locationId: Long,
    @field:NotNull
    val externalLocationId: String,
    @field:NotEmpty
    val partner: PartnerType
) {
    fun toModel(): LocationModel {
        return LocationModel(
            locationId = locationId,
            externalLocationId = externalLocationId,
            partner = partner
        )
    }
}
