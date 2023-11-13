package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.model.Art
import org.kranj.vtrci.service.ArtService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/pub/art")
@RestController
class ArtControllerPub (val service: ArtService) {
    @CrossOrigin
    @GetMapping
    fun getAllArt(pageable: Pageable) = service.getAll(pageable)


    @CrossOrigin
    @GetMapping("/{id}")
    fun getArtById(@PathVariable id: UUID): Art = service.getById(id)

//    @CrossOrigin
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun deleteArt(@PathVariable id: UUID) = service.remove(id)
//
//    @CrossOrigin
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun createNewArt(
//        @RequestParam("name") name: String,
//        @RequestParam("src") src: MultipartFile,
//        @RequestParam("simple") simple: MultipartFile,
//    ) = service.create(NewArtDto(name, src, simple) )

}