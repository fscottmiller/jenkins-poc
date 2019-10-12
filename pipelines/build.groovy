@Library('poc@dev') _

pipeline {
    agent any

    parameters {
        string name: "BRANCH", defaultValue: "master"
    }
    stages {
        stage('Build') {
            steps {
                echo "Building \${BRANCH}..."
            }
        }
        stage('Push') {
            steps {
                echo "Pushing to Artifactory..."
            }
        }
    }
}