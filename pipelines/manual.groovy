@Library('poc@dev') _

pipeline {
    agent any

    parameters {
        string name: "BRANCH", defaultValue: "master"
        choice name: "ENVIRONMENT", choices: ${environments}
    }
    stages {
        stage('Test') {
            steps {
                echo "Checking manual test results for \${params.BRANCH}..."
                echo "The environment is \${params.ENVIRONMENT}"
            }
        }
        stage('Report Results') {
            steps {
                echo "Reporting results..."
            }
        }
    }
}