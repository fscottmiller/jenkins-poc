// this job would simply call Octopus right now
// it could be configured to deploy on its own

job("${project}-Deploy") {
    displayName('Deploy')
    description("Deploy your project to an environment.")
    parameters {
        activeChoiceParam("Release") { // TODO: query Artifactory for packages
        groovyScript {
            script("return (\"git ls-remote --heads ${repository}\").execute().text.readLines().collect { it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '') }")
            }
        }
        choiceParam("Environment", environments)
    }
}