package com.example.iybapp.core.data

import com.example.iybapp.CommonDataModelMapper

class CommonDataModel(
    private val key: String,
    private val thirdText: Int,
    private val fourthText: Double,
    private val firstText: String,
    private val secondText: String,
    private val cached: Boolean = false
) : ChangeCommonItem {

    fun <T> map(mapper: CommonDataModelMapper<T>): T {
        return mapper.map(key, thirdText, fourthText, firstText, secondText, cached)
    }

    override suspend fun change(changeStatus: ChangeStatus) =
        changeStatus.addOrRemove(key, this)


    fun changeCached(cached: Boolean) =
        CommonDataModel(key, thirdText, fourthText, firstText, secondText, cached)

}