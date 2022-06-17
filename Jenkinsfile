pipeline {
  agent any
  stages {
    stage('Quality Gate') {
        steps {}
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
        }
    }
    stage('Build') {
        steps {}
    }
    stage('Docker Image') {
        steps {}
    }
    stage('Docker Deliver') {
        steps {}
    }
    stage('Wait for approval') {
        steps {}
    }
    stage('Deploy') {
        steps {}
    }
  }
}