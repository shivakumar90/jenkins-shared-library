#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    def script
    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the application..."
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-repo', usernameVariable: "USER", passwordVariable: "PASS")]) {
            script.sh "docker build -t $imageName ."
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
            script.sh "docker push $imageName"
        }
    }
}