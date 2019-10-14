// creates a deploy pipeline
// should deploy an existing package to an environment
// coul simply trigger an Octopus job

def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/deploy.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}