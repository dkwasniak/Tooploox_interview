package com.nomtek.domain.mapper

import com.tooploox.domain.api.model.PostApiModel
import com.tooploox.domain.mapper.SongsDomainModelToViewModelMapper
import com.tooploox.domain.viewmodel.PostViewModel
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PostApiModelToViewModelMapperTest {

    private lateinit var target: SongsDomainModelToViewModelMapper

    @Before
    fun setUp() {
        target = SongsDomainModelToViewModelMapper()
    }

    @Test
    fun testMapPostApiModelToViewModel() {
        //given
        val postApiModel = PostApiModel(1, 2, "str1", "str2")
        val postViewModel = PostViewModel(1, 2, "str1", "str2")

        //when
        val result = target.mapPostApiModelToViewModel(postApiModel)

        //then
        assertEquals(result, postViewModel)
    }
}