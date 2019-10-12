def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/functional.groovy")
def pipelineScript = getScript(file, binding.getVariables()['data'])

pipelineJob("${project}-Functional") {
    displayName('Functional Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}