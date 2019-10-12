job("${project}-Performance") {
    displayName('Performance Test')
    description("Run performance tests for your project.")
    parameters {
        choiceParam("Environment", environments)
    }
}