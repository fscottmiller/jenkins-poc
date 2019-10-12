job("${project}-Manual") {
    displayName('Manual Tests')
    description("Check the results of your manual testing..")
    parameters {
        choiceParam("Environment", environments)
    }
}