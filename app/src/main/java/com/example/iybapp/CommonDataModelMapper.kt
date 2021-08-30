package com.example.iybapp

import com.example.iybapp.domain.CommonItem

interface CommonDataModelMapper<T> {
    fun map(
        key: String,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ): T
}

class CommonSuccessMapper<T> : CommonDataModelMapper<CommonItem.Success> {
    override fun map(
        key: String,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ) = CommonItem.Success(first, second, cached)

}

