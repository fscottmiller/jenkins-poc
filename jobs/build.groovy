def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/build.groovy")
def pipelineScript = getScript(file, binding.getVariables()['data'])

pipelineJob("${project}-Build") {
    displayName('Build')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}


