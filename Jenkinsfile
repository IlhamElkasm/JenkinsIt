pipeline {
    agent any

    tools {
        // Assurez-vous que Maven est configuré avec le nom exact dans Jenkins
        maven 'maven_3_3_3'  // Nom de l'outil Maven configuré dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm 
            }
        }

        stage('Unit Tests') {
            steps {
                // Exécuter les tests Maven
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
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
