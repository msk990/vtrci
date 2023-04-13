package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface IngredientsRepository : JpaRepository<Ingredient, UUID>