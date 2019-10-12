def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/manual.groovy")
def pipelineScript = getScript(file, binding.getVariables()['data'])

pipelineJob("${project}-Manual") {
    displayName('Manual Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}