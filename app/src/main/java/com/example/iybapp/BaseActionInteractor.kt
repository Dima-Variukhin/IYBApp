package com.example.iybapp

import com.example.iybapp.data.ActionRepository
import com.example.iybapp.domain.Action
import java.lang.Exception

class BaseActionInteractor(
    private val repository: ActionRepository,
    private val actionFailureHandler: ActionFailureHandler,
    private val mapper: ActionDataModelMapper<Action.Success>
) : ActionInteractor {
    override suspend fun getAction(): Action {
        return try {
            val action = repository.getAction().map(mapper)
            Action.Success(action.activity, action.type, false)
        } catch (e: Exception) {
            Action.Failed(actionFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Action {
        return try {
            repository.changeActionStatus().map(mapper)
        } catch (e: Exception) {
            Action.Failed(actionFailureHandler.handle(e))
        }
    }

    override fun getFavoriteActions(favorite: Boolean) =  repository.chooseDataSource(favorite)

}