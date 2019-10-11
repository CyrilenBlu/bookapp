pipeline {
  agent any
  stages {
    stage('Prep') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '-v /root/.m2:/root/.m2'
        }

      }
      steps {
        echo 'Preperation'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing pipieline'
        sh 'mvn test'
      }
    }
    stage('End') {
      steps {
        echo 'End pipeiline'
      }
    }
  }
}