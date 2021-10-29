pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Compile"){
            steps {
                sh "./gradlew compileJava"
                echo 'Compile stage'
            }
        }
        stage("Build"){
             steps {
                 sh "./gradlew build"
                 echo 'Build stage'
             }
        }
        stage("Install"){
              steps {
                  sh "./gradlew installDist"
                  echo 'Install stage'
             }
        }
        stage("OpenJar"){
              steps {
                  sh "cd build/install/task_git-advanced/bin"
                  echo 'Open stage'
             }
        }
        stage("Run"){
              steps {
                   sh "./task_git-advanced"
                   echo 'Run stage'
              }
        }
    }
}