#!groovy

// Please refer to the following link on how to configure Jenkins file
// https://siaconfluence.sq.com.sg:8190/pages/viewpage.action?pageId=118622656

/*appData=[
  jdkVersion: "17",
  ocpDeploy: true,

  // Can customise base on branch to set different environment e.g.
  // if (env.BRANCH_NAME == "uat") {
  //   appData.ocpEnvironment = "uat"
  // }
  ocpEnvironment: "SIT",
  ocpApp: "gen-pe-sb-test-005",
  binaryPath : "target",
  ocpProject : "PE"
]

if (env.BRANCH_NAME == "master") {
    echo "Production releases should go through release pipeline"
} else if (env.BRANCH_NAME == "integration") {
    promote(appData)
} else {
    dev_ocpMaven(appData) // maven build and ocp deploy
}
*/
pipeline {
    agent {
        docker {
            image 'maven:3.9.0'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
          
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Push') {
            steps {
                echo 'Pushing'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying'
            }
        }
    }
}
