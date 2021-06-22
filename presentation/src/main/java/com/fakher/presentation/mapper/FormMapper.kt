package com.fakher.presentation.mapper

import com.fakher.business.model.FormEntity
import com.fakher.common.Mapper
import com.fakher.presentation.model.Form

class FormMapper : Mapper<Form, FormEntity> {

    override fun to(input: Form) = FormEntity(
        firstInt = input.firstInt.toInt(),
        secondInt = input.secondInt.toInt(),
        firstWord = input.firstWord,
        secondWord = input.secondWord,
        hits = input.hits.toInt()
    )

    override fun from(input: FormEntity) = Form(
        firstInt = input.firstInt.toString(),
        secondInt = input.secondInt.toString(),
        firstWord = input.firstWord,
        secondWord = input.secondWord,
        hits = input.hits.toString()
    )
}