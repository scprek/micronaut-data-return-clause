package com.example


import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.annotation.Where
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.UUID
import javax.validation.constraints.NotNull

@Repository("locations-db")
@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class LocationRepository: CrudRepository<LocationModel, UUID> {

    @Where("deleted_at is NULL")
    abstract fun existsByLocationId(@NotNull locationId: Long): Boolean

    @Query(
        """
        INSERT INTO locations (location_id, external_location_id, partner)
        VALUES( :locationId, :externalLocationId, :partner)
        ON CONFLICT (location_id) WHERE deleted_at IS NULL
        DO
        UPDATE SET external_location_id = EXCLUDED.external_location_id, partner = EXCLUDED.partner
        RETURNING id
    """)
    abstract fun upsertTestReturning(locationId: Long, externalLocationId: String, partner: PartnerType): UUID


    @Query(
        """
        UPDATE locations
        SET external_location_id = :externalLocationId, partner = :partner
        WHERE location_id = :locationId
        RETURNING id
    """)
    abstract fun updateTestReturning(locationId: Long, externalLocationId: String, partner: PartnerType): UUID
}
