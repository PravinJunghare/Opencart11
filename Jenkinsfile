pipeline {
    agent any
    stages {
        stage('Build Project') {
            steps {
                echo 'Building Project'
            }	
        }
        stage('Deploy to Dev env') {
            steps {
                echo 'Deploying to Dev env'
            }
        }
           stage('Deploy to QA env') {
            steps {
                echo 'Deploying to QA env'
            }
        }
           stage('Run Regression Test Cases') {
            steps {
                echo 'Running Regression Test Cases '
            }
        }
           stage('Deploy to Stage ') {
            steps {
                echo 'Deploy to Satge'
            }
        }
           stage('Run Sanity Test Cases') {
            steps {
                echo 'Running Sanity Test Cases'
            }
        }
           stage('Deploy to Production env') {
            steps {
                echo 'Deploying to Production env'
            }
       } 
    }
}
