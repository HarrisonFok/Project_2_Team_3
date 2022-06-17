pipeline {
  environment {
    registry = "harrisonfok/covid_tracker"
    dockerHubCreds = "docker_hub"
    dockerImage = ""
  }
  agent any
  stages {

    stage('Quality Gate') {
        steps {
            echo "Quality Gate"
        }
    }

    stage('Unit Testing') {
        // When we merge to main, this will be skipped
        when {
            // It's the branch/es you're pushing to
            anyOf { branch 'ft_*'; branch 'bg_*'}
        }
        steps {
            echo "Unit Testing"
            /*
            // syntax of the plugin
            withMaven {
                sh 'cd LocationSearchAPI && mvn test'
            }
            junit skipPublishingChecks: true, testResults: 'target/surefire-reports/*.xml'
            */
        }
    }

    stage('Build') {
        when {
            branch 'master'
        }
        steps {
            withMaven {
                sh 'cd LocationSearchAPI && mvn package -DskipTests'
                sh 'cd LocationStatusAPI && mvn package -DskipTests'
            }
        }
    }

    stage('Docker Image') {
        when {
            branch 'master'
        }
        steps {
            script {
                echo "$registry:$currentBuild.number"
                dockerImage = docker.build "$registry"
            }
        }
    }

    stage('Docker Deliver') {
        steps {
            echo "Docker Deliver"
        }
    }

    stage('Wait for approval') {
        steps {
            echo "Wait for approval"
        }
    }

    stage('Deploy') {
        steps {
            echo "Deploy"
        }
    }
  }
}