pipeline {
    agent any

    stages {

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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t demo-app:latest .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker rm -f demo-app || true

                    docker run -d \
                      --name demo-app \
                      -p 8081:8080 \
                      demo-app:latest
                '''
            }
        }
    }
}