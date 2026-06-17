pipeline {
    agent { label 'spring-agent' }

    stages {
        stage('Who am I') {
            steps {
                sh 'hostname'
                sh 'pwd'
            }
        }
        stage('Debug') {
            steps {
                sh 'java -version'
                sh 'mvn -v || true'
                sh 'pwd'
                sh 'ls -la'
            }
        }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
    }
}