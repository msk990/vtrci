package org.kranj.vtrci.transformer

import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page

interface Transformer<A,B> {

    fun transform(source: A): B
}