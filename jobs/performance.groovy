def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/performance.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Performance") {
    displayName('Performance Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}