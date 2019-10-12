@Library('poc@dev') _

pipeline {
    agent any

    parameters {
        choice name: "ENVIRONMENT", choices: ${environments}
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Test') {
            steps {
                echo "Running performance tests from \${params.BRANCH} against \${params.ENVIRONMENT}..."
            }
        }
        stage('Report Results') {
            steps {
                echo "Reporting results..."
            }
        }
    }
}