package com.example


import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.util.UUID
import javax.validation.Valid

@Controller("/v1/locations")
open class LocationController (
    private val locationRepository: LocationRepository
) {
    @Post(consumes = [MediaType.APPLICATION_JSON])
    open fun updateLocation(
        @Body @Valid fleetLocations: List<LocationDto>
    ): HttpResponse<LinkedHashSet<UUID>> {
        val ids: LinkedHashSet<UUID> = linkedSetOf()
        fleetLocations.map {
            it.toModel()
        }.forEach { model ->
            val id = locationRepository.upsertTestReturning(model.locationId, model.externalLocationId, model.partner)
            id?.let { ids.add(it) }
        }
        return HttpResponse.ok( ids)
    }
}
