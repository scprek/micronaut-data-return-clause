package com.example

import io.micronaut.data.annotation.AutoPopulated
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant
import java.util.UUID

@MappedEntity("locations")
data class LocationModel(
    @field:Id
    @AutoPopulated
    var id: UUID? = null,
    var locationId: Long,
    var externalLocationId: String,
    var partner: PartnerType,
    @DateCreated
    var createdAt: Instant? = null,
    @DateUpdated
    var updatedAt: Instant? = null,
)
