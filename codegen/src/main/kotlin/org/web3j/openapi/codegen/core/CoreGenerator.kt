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

import org.web3j.openapi.codegen.DefaultGenerator
import org.web3j.openapi.codegen.config.GeneratorConfiguration
import org.web3j.openapi.codegen.utils.CopyUtils
import org.web3j.openapi.codegen.utils.TemplateUtils
import java.io.File

class CoreGenerator(
    override val configuration: GeneratorConfiguration
) : DefaultGenerator(
    configuration
) {
    override val packageDir = configuration.packageName.split(".").joinToString("/")
    override val folderPath = CopyUtils.createTree("core", packageDir, configuration.outputDir)

    override fun generate() {
        copyGradleFile()
        val context = setContext()
        copySources(context)
    }

    private fun setContext(): HashMap<String, Any> {
        return hashMapOf("packageName" to configuration.packageName)
    }

    private fun copyGradleFile() {
        logger.debug("Copying core/build.gradle")
        CopyUtils.copyResource(
            "core/build.gradle",
            File(folderPath.substringBefore("core")))
    }

    private fun copySources(context: HashMap<String, Any>) {
        File("codegen/src/main/resources/core/src/")
            .listFiles()
            ?.forEach { it ->
                logger.debug("Generating from ${it.canonicalPath}")
                TemplateUtils.generateFromTemplate(
                    context = context,
                    outputDir = folderPath,
                    template = TemplateUtils.mustacheTemplate(it.path.substringAfter("resources/")),
                    name = "${it.name.removeSuffix(".mustache")}.kt"
                )
            }
    }
}