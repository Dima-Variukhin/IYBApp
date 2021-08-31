package com.example.iybapp.domain

import com.example.iybapp.CommonDataModelMapper
import com.example.iybapp.core.data.CommonRepository
import com.example.iybapp.core.domain.CommonInteractor
import com.example.iybapp.core.domain.FailureHandler

class BaseInteractor<E>(
    private val repository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success, E>
) : CommonInteractor {
    override suspend fun getItem(): CommonItem {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): CommonItem {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override fun getFavorites(favorite: Boolean) = repository.chooseDataSource(favorite)

}