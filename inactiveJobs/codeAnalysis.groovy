job("${project}-Analysis") {
    displayName('Static Code Analysis')
    description("Run static code analysis on your project.")
    parameters {
        activeChoiceParam("Branch") {
        groovyScript {
            script("return (\"git ls-remote --heads ${repository}\").execute().text.readLines().collect { it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '') }")
            }
        }
    }
}