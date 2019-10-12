evaluate(readFileFromWorkspace("scripts/getPipelineScript.groovy"))

evaluate("println 'foo'")

println "in deploy.groovy"

script = getPipelineScript("pipelines/deploy.groovy", binding.getVariables()['data'])


pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}