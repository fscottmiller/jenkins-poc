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
                echo "Deploying \${params.BRANCH} to \${params.ENVIRONMENT}..."
            }
        }
    }
}