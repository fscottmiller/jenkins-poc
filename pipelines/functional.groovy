pipeline {
    agent any

    parameters {
        choice name: "ENVIRONMENT", choices: ${environments}
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Test') {
            steps {
                echo "Testing \${BRANCH}..."
            }
        }
        stage('Report Results') {
            steps {
                echo "Reporting results..."
            }
        }
    }
}