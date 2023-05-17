package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.*
import org.kranj.vtrci.model.Survey
import org.kranj.vtrci.repository.SurveyRepository
import org.springframework.stereotype.Service

@Service
class SurveyService (val repository: SurveyRepository) {

    fun getAll() = repository.findAll()

    fun getByPriority() : PriorityDto {
        val priotityDto = PriorityDto(
            high = repository.countByPriority("high"),
            medium = repository.countByPriority("medium"),
            notYet = repository.countByPriority("not yet"),
            no = repository.countByPriority("no")
        )
        return priotityDto
    }

    fun getBySolution() : SolutionDto {
        val solutionDto = SolutionDto(
            blockchain = repository.countBySolution("blockchain"),
            notBlockchain = repository.countBySolution("not blockchain"),
            notYet = repository.countBySolution("not yet"),
            no = repository.countBySolution("no")
        )
        return solutionDto
    }

    fun getByInformation() : InformationDto {
        val informationDto = InformationDto(
            website = repository.countByInformation("website"),
            municipality = repository.countByInformation("municipality"),
            interactive = repository.countByInformation("interactive"),
            no = repository.countByInformation("no")
        )
        return informationDto
    }

    fun getByBenefit() : BenefitDto {
        val benefitDto = BenefitDto(
            definitely = repository.countByBenefit("definitely"),
            maybe = repository.countByBenefit("maybe"),
            probablyNot = repository.countByBenefit("probably not"),
            definitelyNot = repository.countByBenefit("definitely not")
        )
        return benefitDto
    }

    fun getByMostLiked() : MostLikedDto {
        val mostLiked = MostLikedDto(
            transparent = repository.countByMostLiked("transparent"),
            educational = repository.countByMostLiked("educational"),
            funIs = repository.countByMostLiked("fun"),
            all = repository.countByMostLiked("all")
        )
        return mostLiked
    }

    fun create(survey:Survey) : Survey = repository.save(survey)
}