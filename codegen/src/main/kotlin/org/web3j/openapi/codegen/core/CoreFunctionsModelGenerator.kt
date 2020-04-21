/*
 * Copyright 2020 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.openapi.codegen.core

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import mu.KLogging
import org.web3j.openapi.codegen.LICENSE
import org.web3j.openapi.codegen.utils.SolidityUtils
import org.web3j.protocol.core.methods.response.AbiDefinition
import java.io.File

class CoreFunctionsModelGenerator(
    val packageName: String,
    private val contractName: String,
    private val functionName: String,
    val folderPath: String,
    val inputs: MutableList<AbiDefinition.NamedType>
) {
    fun generate() {
        val functionFile = getFunctions()
        functionFile.writeTo(File(folderPath))
    }

    private fun getFunctions(): FileSpec {
        val functionBuilder = FunSpec.constructorBuilder()

        val functionFile = FileSpec.builder(
            "$packageName.core.${contractName.toLowerCase()}.model",
            "${functionName.capitalize()}Parameters"
        )

        inputs.forEach {
            functionBuilder.addParameter(
                it.name,
                SolidityUtils.getNativeType(it.type)
            )
        }

        val constructor = TypeSpec.classBuilder("${functionName.capitalize()}Parameters")
            .primaryConstructor(functionBuilder.build()).build()

        return functionFile
            .addType(constructor)
            .addComment(LICENSE)
            .build()
    }

    companion object : KLogging()
}
