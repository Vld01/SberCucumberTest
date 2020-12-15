pipeline {
   agent any
   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/Vld01/SberCucumberTest'

            // Run Maven on a Unix agent.
            sh "mvn clean test -Dcucumber.filter.tags="${TAG}""

         }
      }
   }
}

pipeline{
   agent any
    stages{
        stage('Run Tests'){
            steps{
                withMaven(jdk: 'openjdk_1.8', maven: 'Maven3') {
                    bat "mvn clean test -Dcucumber.filter.tags=\"${TAG}\""
                }
            }
        }
        stage('Allure Report Generation'){
            steps{
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}