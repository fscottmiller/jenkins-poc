pipeline {
    agent any

    parameters {
        string name: "BRANCH", defaultValue: "master"
        choice name: "ENVIRONMENT", choices: ${environments}
    }
    stages {
        stage('Test') {
            steps {
                echo "Checking manual test results for \${BRANCH}..."
                echo "The environment is \${ENVIRONMENT}"
            }
        }
        stage('Report Results') {
            steps {
                echo "Reporting results..."
            }
        }
    }
}