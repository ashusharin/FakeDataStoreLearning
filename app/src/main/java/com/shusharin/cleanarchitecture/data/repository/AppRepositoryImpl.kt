package com.shusharin.cleanarchitecture.data.repository

import android.util.Log
import com.shusharin.cleanarchitecture.data.api.ProductListItem
import com.shusharin.cleanarchitecture.data.datasource.local.LocalDataSource
import com.shusharin.cleanarchitecture.data.datasource.remote.RemoteDataSource
import com.shusharin.cleanarchitecture.domain.AppRepository
import com.shusharin.cleanarchitecture.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception

import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : AppRepository {

    override fun first() {

        remoteDataSource.method()
        localDataSource.method()
    }

    override fun getAllProduct(): Flow<List<Product>> = flow {

        val listProduct = mutableListOf<Product>()


        try {
            val response = remoteDataSource.getAllProduct()
            if (response.isSuccessful) {
                response.body()?.forEach {
                    val item = Product(it.id,
                        it.title,
                        it.price,
                        it.description,
                        it.category,
                        it.image,
                        it.rating.rate,
                        it.rating.count)
                    listProduct.add(item)
                }
                emit(listProduct)
                Log.d("getAllProduct", "list of product - ${listProduct.toString()}")
            }
        } catch (e: Exception) {
            Log.d("getAllProduct", "Request with error -> $e")
        }

    }
}