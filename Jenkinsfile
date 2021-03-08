node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/eitheljxd/test-promart-backend.git', branch: 'master'
        }

        stage('Build') {
            sh 'mvn clean install'
            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }
        stage('Image') {
            dir ('discovery-service') {
                def app = docker build -t aws-elasticbeanstalk-example .
                app.push()
            }
        }

        stage ('Run') {
            docker.image("aws-elasticbeanstalk-example").run('-p 5000:5000 aws-elasticbeanstalk-example')
        }
        stage('Unit Test') { 
		        steps {
		            sh 'mvn clean package  -DskipTests' 
		            sh 'newman run ./../PROMART.postman_collection.json'
		        }
		 }
		 
        stage('Publish') {
            steps {
                sh './mvnw package'
            }
            post {
                success {
                    archiveArtifacts 'target/aws-elasticbeanstalk-example-0.0.1-SNAPSHOT.jar'
                    sh 'aws configure set region us-east-2'
                    sh 'aws s3 cp ./target/aws-elasticbeanstalk-example-0.0.1-SNAPSHOT.jar s3://promart-java/CIDI-PROMART/BuildArtif/aws-elasticbeanstalk-example-0.0.1-SNAPSHOT.jar'
 
                }
            }
        }

        stage ('Final') {
            build job: 'aws-elasticbeanstalk-example', wait: false
        }      

    }

} 