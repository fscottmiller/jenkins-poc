job("${project}-Deploy") {
    displayName('Deploy')
    description("Deploy your project to an environment.")
    environmentVariables {
        envs(
            DATAFILE: "${JENKINS_HOME}/projects/${project.replaceAll(" ", "-").toLowerCase()}/data.yaml"
        )
    }
    parameters {
        activeChoiceParam("BRANCH") {
        groovyScript {
            script("return (\"git ls-remote --heads ${repository}\").execute().text.readLines().collect { it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '') }")
            }
        }
        choiceParam("ENVIRONMENT", environments)
    }
    steps {
        shell("echo Project: $project")
        shell("echo Branch: \$BRANCH")
        shell("echo Environment: \$ENVIRONMENT")
        shell("echo Data File: \$DATAFILE")
        groovyCommand(groovyCommand(readFileFromWorkspace('scripts/yaml.groovy'))) {
            scriptParam("\$DATAFILE")
            scriptParam("\$BRANCH")
            scriptParam("\$ENVIRONMENT")
        }
    }
}