def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/performance.groovy")
def pipelineScript = getScript(file, binding.getVariables()['data'])

pipelineJob("${project}-Performance") {
    displayName('Performance Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}