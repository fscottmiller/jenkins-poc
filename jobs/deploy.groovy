evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

println "in deploy.groovy"

script = Utils.getPipelineScript("pipelines/deploy.groovy", binding.getVariables()['data'])

pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}