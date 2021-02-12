package com.hiral.demotest.data.repositories

import com.hiral.demotest.data.network.MyApi
import com.hiral.demotest.data.network.SafeApiRequest
import com.hiral.demotest.data.network.responses.ApiResponse

class ApiResponseRepository(
    private val api: MyApi
) : SafeApiRequest() {
    suspend fun responseData(
    ): List<ApiResponse> {
        return apiRequest { api.getResponse() }
    }
}