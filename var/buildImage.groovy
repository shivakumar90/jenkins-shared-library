def call() {
    echo "building the application..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', usernameVariable: "USER", passwordVariable: "PASS")]) {
        sh "docker build -t shivakumarreddy1/demo-app:jma-3.0 ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push shivakumarreddy1/demo-app:jma-3.0"
    }
}