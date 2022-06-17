pipeline {
  environment {
    registry1 = "harrisonfok/covid_tracker_location_search_api"
    registry2 = "harrisonfok/covid_tracker_location_status_api"
    dockerHubCreds = "docker_hub"
    dockerImage1 = ""
    dockerImage2 = ""
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
                echo "$registry1:$currentBuild.number"
                //echo "$registry2:$currentBuild.number"
                sh "docker build -t $registry1 LocationSearchAPI"
            }
        }
    }

    stage('Docker Deliver') {
        when {
            branch 'master'
        }
        steps {
            script {
                sh "docker push harrisonfok/covid_tracker_location_search_api"
            }
            echo "Docker Deliver"
        }
    }

    stage('Wait for approval') {
        when {
            anyOf {branch 'master'}
        }
        steps {
            script {
                try {
                    timeout(time: 20, unit: 'MINUTES') {
                        approved = input message: 'Deploy to production?', ok: 'Continue',
                            parameters: [choice(name: 'approved', choices: 'Yes\nNo', description: 'Deploy build to production')]

                        if(approved != 'Yes') {
                            error('Build did not pass approval')
                        }
                    }
                } catch(error) {
                    error('Build failed because timeout was exceeded');
                }
            }
        }
    }

    stage('Deploy') {
        steps {
            echo "Deploy"
        }
    }
  }
}