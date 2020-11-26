package com.mcsimf.doh.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mcsimf.doh.core.googledoh.Api
import com.mcsimf.doh.core.googledoh.entity.DoHResponse
import retrofit2.HttpException
import java.io.IOException

/**
 * @author Maksym Fedyay on 11/25/20 (mcsimf@gmail.com).
 */
object Core {

    private val api = Api()

    fun lookupDoH(dns: String): LiveData<Res<DoHResponse>> = liveData {
        emit(Res.loading())
        try {
            val data = api.dohApi.lookupDoH(name = dns)
            emit(Res.complete(data))
        } catch (e: IOException) {
            emit(Res.error<DoHResponse>(e.message))
        } catch (e: HttpException) {
            emit(Res.error<DoHResponse>(e.message, e.code()))
        } catch (e: Exception) {
            emit(Res.error<DoHResponse>(e.message))
        }
    }

}