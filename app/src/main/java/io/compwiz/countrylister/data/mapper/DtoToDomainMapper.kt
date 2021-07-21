package io.compwiz.countrylister.data.mapper

interface DtoToDomainMapper<E, D> {
    fun toDomain(dto: E): D
}