package com.example.iybapp

import com.example.iybapp.domain.CommonItem

interface CommonDataModelMapper<T, E> {
    fun map(
        key: E,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ): T
}

class CommonSuccessMapper<E> : CommonDataModelMapper<CommonItem.Success<E>, E> {
    override fun map(
        key: E,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ) = CommonItem.Success(key, first, second, cached)

}

