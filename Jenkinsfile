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
                // Utilisez 'bat' pour exécuter les commandes Maven sur Windows
                bat './mvnw test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQubeScanner'  // Nom de l'outil configuré dans Jenkins
                    withSonarQubeEnv('SonarQube') {
                        // Utilisez 'bat' pour exécuter les commandes SonarQube Scanner sur Windows
                        bat "${scannerHome}/bin/sonar-scanner.bat"
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
