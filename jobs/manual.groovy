def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/manual.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Manual") {
    displayName('Manual Tests')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}