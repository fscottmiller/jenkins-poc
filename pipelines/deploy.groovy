@Library('poc@dev') _

pipeline {
    agent any

    parameters {
        choice name: "ENVIRONMENT", choices: ${environments}
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Deploy') {
            steps {
                echo "Deployment type: "
                echo "Deploying \${BRANCH} to \${ENVIRONMENT}..."
            }
        }
    }
}