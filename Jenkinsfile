pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Unit Tests') {
            steps {
                sh './mvnw test'  // Exécuter les tests unitaires Maven
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQubeScanner'  // Nom de l'outil configuré dans Jenkins
                    withSonarQubeEnv('SonarQube') {
                        sh "${scannerHome}/bin/sonar-scanner"  // Exécuter l'analyse SonarQube
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def app = docker.build("username/itsupport:${env.BUILD_NUMBER}")  // Remplace 'username' par ton identifiant DockerHub
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {  // Utiliser les identifiants DockerHub configurés dans Jenkins
                        app.push("${env.BUILD_NUMBER}")  // Pousser l'image avec le numéro de build comme tag
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()  // Nettoyer l'espace de travail après chaque exécution
        }
    }
}
