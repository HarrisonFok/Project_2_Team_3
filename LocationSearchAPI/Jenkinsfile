pipeline {
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
            // syntax of the plugin
            withMaven {
                sh 'mvn test'
            }
            junit skipPublishingChecks: true, testResults: 'target/surefire-reports/*.xml'
        }
    }
    stage('Build') {
        steps {
            echo "Build"
        }
    }
    stage('Docker Image') {
        steps {
            echo "Docker Image"
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