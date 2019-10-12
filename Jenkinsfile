pipeline {
    agent any

    stages {
        stage('Configure Jobs') {
            steps {
                script {
                    def yaml = readYaml file: 'config.yaml'
                    for (project in yaml.keySet()) {
                        println "----- ${project} builing -----"
                        jobDsl targets: ['jobs/*.groovy'].join('\n'), 
                            additionalParameters: [
                                project: project, 
                                data: yaml[project]
                            ],
                            removedJobAction: 'DELETE',
                            removedViewAction: 'DELETE'
                    }
                }
            }
        }
        stage('Create Data Storage') {
            steps {
                script {
                    try {
                        sh script: "mkdir ${JENKINS_HOME}/projects",
                            label: "Making projects directory..."
                    } catch (Exception e) {
                        echo "Project directory already exists. Doing nothing..."
                    }
                    def yaml = readYaml file: 'config.yaml'
                    for (projectName in yaml.keySet()) {
                        dir = projectName.replaceAll(" ", "-").toLowerCase()
                        try {
                            sh script: "mkdir ${JENKINS_HOME}/projects/${dir}",
                                label: "Making directory for project ${dir}"
                        } catch (Exception e) {
                            echo "Directory for project ${projectName} already exists. Doing nothing..."
                        }
                    }                    
                }
            }
        }
        stage('Initialize Storage Files') {
            steps {
                script {
                    def yaml = readYaml file: 'config.yaml'
                    for (projectName in yaml.keySet()) {
                        dir = projectName.replaceAll(" ", "-").toLowerCase()
                        map = [:]
                        for (env in yaml[projectName]['environments']) {
                            map[env] = ""
                        }
                        try {
                            sh script: "test -f ${JENKINS_HOME}/projects/${dir}/data.yaml",
                                label: "Checking for data store..."
                            echo "Data store exists. Doing nothing..."
                        } catch (Exception e) {
                            echo "Initializing data store for ${projectName}"
                            writeYaml file: "${JENKINS_HOME}/projects/${dir}/data.yaml", data: map
                        }
                    }
                }
            }
        }
    }
}