pipeline {
  agent any
  stages {
    stage('Prep') {
      steps {
        echo 'Preperation'
      }
    }
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn clean package'
          }
        }
        stage('Test') {
          steps {
            sh 'mvn test'
          }
        }
      }
    }
    stage('End') {
      steps {
        echo 'Finished pipeline'
      }
    }
  }
}