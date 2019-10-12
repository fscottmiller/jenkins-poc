@Library('poc@dev') _

pipeline {
    agent any

    environment {
        current = ${current}
        next = ${next}
    }
    stages {
        stage('Promote \${current} to \${next}') {
            steps {
                echo "Promoting..."
            }
        }
        stage('Record Results') {
            steps {
                echo "Recording results..."
            }
        }
    }
}