pipeline {
    agent any

    parameters {
        choice name: "ENVIRONMENT", choices: ${environments}
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Test') {
            steps {
                echo "Running performance tests from \${BRANCH} against \${ENVIRONMENT}..."
            }
        }
        stage('Report Results') {
            steps {
                echo "Reporting results..."
            }
        }
    }
}