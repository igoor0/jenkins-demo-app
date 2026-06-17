pipeline {
    agent none

    stages {
        stage('Checkout') {
            agent { label 'spring-agent' }
            steps {
                checkout scm
            }
        }

        stage('Build & Test (Spring)') {
            agent { label 'spring-agent' }
            steps {
                sh './mvnw clean package'
                stash includes: 'target/*.jar', name: 'jar'
            }
        }

        stage('Docker build') {
            agent { label 'docker-agent' }
            steps {
                unstash 'jar'
                sh 'docker build -t app:latest .'
            }
        }
    }
}