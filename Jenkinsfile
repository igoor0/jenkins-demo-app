pipeline {
    agent none

    environment {
        IMAGE = "igoor0/jenkins-demo-app"
        REGISTRY_CREDENTIALS = "dockerhub"
    }

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

        stage('Docker Build') {
                   agent { label 'docker-agent' }
                   steps {
                       unstash 'jar'
                       sh """
                           docker build -t ${IMAGE}:${BUILD_NUMBER} .
                           docker tag ${IMAGE}:${BUILD_NUMBER} ${IMAGE}:latest
                       """
                   }
               }

       stage('Docker Login') {
           agent { label 'docker-agent' }
           steps {
               withCredentials([usernamePassword(
                   credentialsId: "${REGISTRY_CREDENTIALS}",
                   usernameVariable: 'USER',
                   passwordVariable: 'PASS'
               )]) {
                   sh '''
                       echo "$PASS" | docker login -u "$USER" --password-stdin
                   '''
               }
           }
       }

       stage('Docker Push') {
           agent { label 'docker-agent' }
           steps {
               sh """
                   docker push ${IMAGE}:${BUILD_NUMBER}
                   docker push ${IMAGE}:latest
               """
           }
       }
    }
    post {
        always {
            echo 'Pipeline finished'
        }

        success {
            echo 'Build + Push SUCCESS'
        }

        failure {
            echo 'Pipeline FAILED'
        }
    }
}