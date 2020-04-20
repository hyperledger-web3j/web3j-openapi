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
package org.web3j.openapi.codegen

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.web3j.openapi.codegen.config.GeneratorConfiguration

abstract class DefaultGenerator(
    val configuration: GeneratorConfiguration
) {
    abstract val packageDir: String

    val logger: Logger = LoggerFactory.getLogger(DefaultGenerator::class.java)

    abstract val folderPath: String

    abstract fun generate()
}
