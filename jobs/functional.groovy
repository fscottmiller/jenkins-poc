// creates a functional testing pipeline

def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/functional.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Functional") {
    displayName('Functional Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}