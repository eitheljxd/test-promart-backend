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

        stage ('Final') {
            build job: 'aws-elasticbeanstalk-example', wait: false
        }      

    }

} 