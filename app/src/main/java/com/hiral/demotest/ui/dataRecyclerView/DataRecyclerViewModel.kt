package com.hiral.demotest.ui.dataRecyclerView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hiral.demotest.data.network.MyApi
import com.hiral.demotest.data.network.responses.ApiResponse
import com.hiral.demotest.data.repositories.ApiResponseRepository
import com.hiral.demotest.utils.ApiException
import com.hiral.demotest.utils.Resource
import kotlinx.coroutines.Dispatchers

class DataRecyclerViewModel : ViewModel() {

    val api: MyApi = MyApi.invoke()
    val apiResponseRepository = ApiResponseRepository(api)

    fun fetchUsers1() = liveData<Resource<List<ApiResponse>>>(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(apiResponseRepository.responseData()))
        } catch (e: ApiException) {
            e.printStackTrace()
            emit(Resource.error(null, e.localizedMessage))
        }
    }
}