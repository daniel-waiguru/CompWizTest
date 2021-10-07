package io.compwiz.countrylister.domain.exceptions

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class HttpException(message: Response<*>): HttpException(message)