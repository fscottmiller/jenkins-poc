def getScript = evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

def file = readFileFromWorkspace("pipelines/promoteEnv.groovy")
def vars = binding.getVariables()['data']
def pipelineScript = getScript(file, vars)

pipelineJob("${project}-Promote-${environment}") {
    displayName("Promote ${Environment}")
    definition {
        cps { 
            script(pipelineScript)
        }
    }
}


