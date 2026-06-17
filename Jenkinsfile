pipeline {
    agent none

    stages {
        stage('Docker build check ') {
            agent { label 'docker-agent' }
            steps {
                sh 'whoami'
                sh 'id'
                sh 'ls -l /var/run/docker.sock'
                sh 'docker ps'
            }
        }
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