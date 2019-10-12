pipeline {
    agent any

    environment {
        DEV = 'DEV'
        QA = 'QA'
        UAT = 'UAT'
    }

    parameters {
        choice name: "ENVIRONMENT", choices: ${data['environments']}
        string name: "BRANCH", defaultValue: "master"
        // gitParameter name: 'BRANCH', branchFilter: 'origin/(.*)', defaultValue: 'master', type: 'PT_BRANCH', useRepository: "${repository}"
    }
    stages {
        stage('Deploy') {
            steps {
                echo "Deploying \${BRANCH} to \${ENVIRONMENT}..."
            }
        }
    }
}