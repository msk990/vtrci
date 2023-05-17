package org.kranj.vtrci.dtos

data class PriorityDto (
    val high: Int,
    val medium: Int,
    val notYet: Int,
    val no: Int
        )

data class SolutionDto(
    val blockchain: Int,
    val notBlockchain: Int,
    val notYet: Int,
    val no: Int
)

data class InformationDto (
    val website: Int,
    val municipality: Int,
    val interactive: Int,
    val no: Int
        )

data class BenefitDto(
    val definitely: Int,
    val maybe: Int,
    val probablyNot: Int,
    val definitelyNot: Int
)

data class MostLikedDto(
    val transparent: Int,
    val educational: Int,
    val funIs: Int,
    val all: Int

)