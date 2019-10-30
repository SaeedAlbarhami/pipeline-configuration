def install_upgrade(helm_chart, revision, namespace, env)
{
    if (helm_chart == null || (helm_chart instanceof String && helm_chart.trim().isEmpty())) {
          echo "please send helm chart name as an input param"
      }    
    container('helm') {   
       println "checking client/server version"
       checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/SaeedAlbarhami/pipeline-configuration.git']]])
       sh "git status" 
       sh " ls -a "
       sh "cd helm"
       sh "pwd"  
       sh " ls -a "
       sh "helm upgrade --install --force '${helm_chart}' 'helm/${helm_chart}' -f 'helm/${helm_chart}'/values-'${env}'.yaml --set image.tag='${revision}' --namespace='${namespace}'"
       echo "To rollback to the previous revision (helm rollback ${helm_chart} 0)"
       sh "helm history ${helm_chart}"                    
    }
 }
                    
