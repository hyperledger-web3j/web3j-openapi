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
package org.web3j.openapi.console

import picocli.CommandLine
import java.io.File
import java.util.concurrent.Callable
import org.web3j.openapi.console.utils.GradleUtils.runGradleTask

@CommandLine.Command(name = "run",
    description = ["Runs a web3j-openapi project"])
class RunCommand : Callable<Int> {
    @CommandLine.Option(names = ["-p", "--project"],
        description = ["specify the project directory to be run."],
        defaultValue = ".",
        required = true)
    lateinit var projectFolder: File

    override fun call(): Int {

        runGradleTask(
            projectFolder,
            "run",
            "Running the project in ${projectFolder.canonicalPath}",
            System.out)

        return 0
    }
}