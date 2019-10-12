job("${project}-Build") {
    displayName('Build')
    description("Build your project and push it to artifactory.")
    parameters {
        activeChoiceParam("Branch") {
        groovyScript {
            script("return (\"git ls-remote --heads ${repository}\").execute().text.readLines().collect { it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '') }")
            }
        }
    }
}