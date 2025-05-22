def call(String imageName) {
    echo "building the application..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', usernameVariable: "USER", passwordVariable: "PASS")]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
}