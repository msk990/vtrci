package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Survey
import org.kranj.vtrci.service.SurveyService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/survey")
@RestController
class SurveyController (val service: SurveyService) {

    @CrossOrigin
    @GetMapping
    fun getAll() = service.getAll()


    @CrossOrigin
    @GetMapping("/priority")
    fun getSurveyPriority() = service.getByPriority()

    @CrossOrigin
    @GetMapping("/solution")
    fun getSurveySolution() = service.getBySolution()

    @CrossOrigin
    @GetMapping("/information")
    fun getSurveyInformation() = service.getByInformation()

    @CrossOrigin
    @GetMapping("/benefit")
    fun getSurveyBenefit() = service.getByBenefit()

    @CrossOrigin
    @GetMapping("/most-liked")
    fun getSurveyLiked() = service.getByMostLiked()

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveSurvey(@RequestBody survey: Survey): Survey = service.create(survey)

}