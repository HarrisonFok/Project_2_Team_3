pipeline {
  environment {
    registry1 = "harrisonfok/covid_tracker_location_search_api"
    registry2 = "harrisonfok/covid_tracker_location_status_api"
    dockerHubCreds = "docker_hub"
    dockerImage1 = ""
    dockerImage2 = ""
    scannerHome = tool 'SonarQubeScanner'

  }
  agent any
  stages {

    stage('SonarCloud') {
        steps {
            withSonarQubeEnv('SonarCloud') {
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
                    -Dsonar.organization=$ORGANIZATION \
                    -Dsonar.java.binaries=target'
            }
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
                echo "$registry2:$currentBuild.number"
                sh "docker build -t $registry1 LocationSearchAPI"
                sh "docker build -t $registry2 LocationStatusAPI"
            }
        }
    }

    stage('Docker Deliver') {
        when {
            branch 'master'
        }
        steps {
            script {
                docker.withRegistry("", dockerHubCreds) {
                    sh "docker push harrisonfok/covid_tracker_location_search_api"
                    sh "docker push harrisonfok/covid_tracker_location_status_api"
                }
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
            sh "sed -i 's|image: harrisonfok/covid_tracker_location_search_api|image: harrisonfok/covid_tracker_location_search_api|g' Kubernetes/LocationStatusAPI.deployment.yaml"
            step([$class: 'KubernetesEngineBuilder',
                projectId: env.PROJECT_ID,
                clusterName: env.CLUSTER_NAME,
                location: env.REGISTRY_LOCATION,
                manifestPattern: 'Kubernetes',
                credentialsId: env.CREDENTIALS_ID,
                verifyDeployments: true])

            sh "sed -i 's|image: harrisonfok/covid_tracker_location_status_api|image: harrisonfok/covid_tracker_location_status_api|g' Kubernetes/LocationSearchAPI.deployment.yaml"
            step([$class: 'KubernetesEngineBuilder',
                projectId: env.PROJECT_ID,
                clusterName: env.CLUSTER_NAME,
                location: env.REGISTRY_LOCATION,
                manifestPattern: 'Kubernetes',
                credentialsId: env.CREDENTIALS_ID,
                verifyDeployments: true])
        }
    }
  }
}