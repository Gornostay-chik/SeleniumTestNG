pipeline {
    agent any

    stages {
        // 1) Скачиваем код из GitHub
        stage('Git') {
            steps {
                git 'https://github.com/Gornostay-chik/SeleniumTestNG.git'
            }
        }

        // 2) Чистим старые отчёты
        stage('Clean') {
            steps {
                sh 'rm -rf reports/*'
            }
        }

        // 3) Сборка и прогон тестов через Maven
        stage('Maven') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
            post {
                success {
                    // собираем XML-отчёты и артефакты
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                    archiveArtifacts artifacts: 'reports/*.html', followSymlinks: false
                    testNG()
                }
            }
        }
    }
}
