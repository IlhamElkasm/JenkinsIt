pipeline {
    agent any

   tools {
       maven 'maven_3_3_3'
   }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Unit Tests') {
            steps {
                // Run Maven tests
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Check Docker version to ensure it's installed
                    bat 'docker --version'
                    def app = docker.build("username/itsupport:${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        app.push("${env.BUILD_NUMBER}")
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
