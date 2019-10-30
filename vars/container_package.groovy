def build_push(container_image, revision)
{
    script 
    {
      if (container_image == null || (container_image instanceof String && container_image.trim().isEmpty())) {
      echo "please send helm chart name as an input param"
      } 
    container('docker')
       {
           sh "docker --version"
          // This step should not normally be used in your script. Consult the inline help for details.
            withDockerRegistry(credentialsId: 'ecr:us-west-2:AWSECR', url: 'https://400493355003.dkr.ecr.us-west-2.amazonaws.com') {
                sh "docker build . -t 400493355003.dkr.ecr.us-west-2.amazonaws.com/${container_image}:${revision} --build-arg REVISION=${revision}"
                sh "docker push 400493355003.dkr.ecr.us-west-2.amazonaws.com/${container_image}:${revision}"
         }
           
       }
    }
}
