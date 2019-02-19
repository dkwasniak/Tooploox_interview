package com.tooploox.domain.interactor

import com.tooploox.domain.mapper.PostApiModelToViewModelMapper
import com.tooploox.domain.repository.MainRepository
import javax.inject.Inject

open class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository,
    private val mapper: PostApiModelToViewModelMapper
) {

}