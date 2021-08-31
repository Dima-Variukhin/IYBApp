package com.example.iybapp.core.data

import com.example.iybapp.CommonDataModelMapper

class CommonDataModel<E>(
    private val key: E,
    private val thirdText: Int,
    private val fourthText: Double,
    private val firstText: String,
    private val secondText: String,
    private val cached: Boolean = false
) : ChangeCommonItem<E> {

    fun <T> map(mapper: CommonDataModelMapper<T, E>): T {
        return mapper.map(key, thirdText, fourthText, firstText, secondText, cached)
    }

    override suspend fun change(changeStatus: ChangeStatus<E>) =
        changeStatus.addOrRemove(key, this)


    fun changeCached(cached: Boolean) =
        CommonDataModel(key, thirdText, fourthText, firstText, secondText, cached)

}