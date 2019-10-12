def getter = evaluate(readFileFromWorkspace("scripts/get2.groovy"))
def file = readFileFromWorkspace("pipelines/deploy.groovy")

println "file: ${file.getClass()}"
println "getter: ${getter.getClass()}"

script = getter.getPipelineScript(file, binding.getVariables()['data'])


pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(script)
        }
    }
}