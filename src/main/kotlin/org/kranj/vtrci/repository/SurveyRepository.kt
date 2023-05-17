package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Survey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SurveyRepository : JpaRepository<Survey, UUID> {

    fun countByPriority(priority: String):Int

    fun countBySolution(solution: String): Int

    fun countByInformation(information:String): Int

    fun countByBenefit(benefit:String): Int

    fun countByMostLiked(mostLiked: String): Int
}