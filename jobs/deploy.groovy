get = evaluate(readFileFromWorkspace("scripts/get2.groovy"))

println "in deploy.groovy"
file = readFileFromWorkspace("pipelines/deploy.groovy")
script = get.getPipelineScript(file, binding.getVariables()['data'])

pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}