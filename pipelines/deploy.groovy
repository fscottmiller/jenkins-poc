pipeline {
    agent any

    environment {
        DEV = 'DEV'
        QA = 'QA'
        UAT = 'UAT'
    }

    parameters {
        choice name: "ENVIRONMENT", choices: environments
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Deploy') {
            steps {
                echo "Deploying \${BRANCH} to \${ENVIRONMENT}..."
            }
        }
    }
}