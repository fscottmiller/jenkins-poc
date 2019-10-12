get = evaluate(readFileFromWorkspace("scripts/get2.groovy"))

println "in deploy.groovy"

script = get.getPipelineScript("pipelines/deploy.groovy", binding.getVariables()['data'])

pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}