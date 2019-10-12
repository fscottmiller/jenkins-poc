job("${project}-Functional") {
    displayName('Functional Tests')
    description("Run functional tests on your project.")
    parameters {
        choiceParam("Environment", environments)
    }
}