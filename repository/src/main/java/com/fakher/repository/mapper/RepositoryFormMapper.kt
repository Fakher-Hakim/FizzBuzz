package com.fakher.repository.mapper

import com.fakher.business.model.FormEntity
import com.fakher.common.Mapper
import com.fakher.repository.model.FormPersistence

class RepositoryFormMapper : Mapper<FormPersistence, FormEntity> {

    override fun to(input: FormPersistence) = FormEntity(
        firstInt = input.firstInt,
        secondInt = input.secondInt,
        firstWord = input.firstWord,
        secondWord = input.secondWord,
        hits = input.hits
    )

    override fun from(input: FormEntity) = FormPersistence(
        firstInt = input.firstInt,
        secondInt = input.secondInt,
        firstWord = input.firstWord,
        secondWord = input.secondWord,
        hits = input.hits
    )
}