evaluate(new File("../scripts/getPipelineScript.groovy"))

script = getPipelineScript("pipelines/deploy.groovy", binding.getVariables()['data'])


pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}