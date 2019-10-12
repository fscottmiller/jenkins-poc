def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/build.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Build") {
    displayName('Build')
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}


