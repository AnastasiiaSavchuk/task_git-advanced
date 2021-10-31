pipeline {
    agent any
    triggers {
    cron('H */8 * * *') //regular builds
    pollSCM('* * * * *') //polling for changes, here once a minute
    }
    stages {
        stage('Build'){
             steps {
                 sh 'gradle build'
             }
        }
        stage('Run'){
              steps {
                   sh 'gradle run --args="10+5/(8-5)"'
              }
        }
    }
}