package com.jonathan.disneyapp.domain.usecase

import com.jonathan.disneyapp.data.repository.RegisterRepository
import com.jonathan.disneyapp.domain.model.Register
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {

    suspend operator fun invoke(register: Register): List<Register> {
       val registers = registerRepository.registerUserFromApi(register)

        return if (registers.isNot) {
            registerRepository.insertUser()
        }


    }
}