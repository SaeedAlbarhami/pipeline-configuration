def getsourcecode(sourcecodeurl, branch)
{
    script 
    {
        def repo = checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
                             doGenerateSubmoduleConfigurations: false, extensions: [], 
                             submoduleCfg: [], userRemoteConfigs: [[url:sourcecodeurl]]])
        revision = sh(script: 'git log -1 --format=\'%h.%ad\' --date=format:%Y%m%d-%H%M | cat', returnStdout: true).trim()
        branch = repo.GIT_BRANCH.take(20).replaceAll('/', '_')
        if (branch != 'origin_master') {
            revision += "-${branch}"
        }
        sh "echo ${branch}"
        sh "echo 'Building revision: ${revision}'"
        return revision
    }
}
