import groovy.text.SimpleTemplateEngine

println "fixData"
def fixData(data) {
    println "---- function fixParams -----"
    for (i in data) {
        if (i.value instanceof Map) {
            println "Iterating ${i.key}..."
            fixData(i.value)
        } else if (i.value instanceof Collection) {
            println "Fixing collection values"
            i.value = i.value.collect{ "'$it'" }
        }
    }
}

println "getPipelineScript"
def getPipelineScript(String file, LinkedHashMap vars) {
    println "Workspace: ${WORKSPACE}"
    def jfile = readFileFromWorkspace "pipelines/deploy.groovy"
    fixData(vars)
    def script = new SimpleTemplateEngine().createTemplate(jfile).make(vars).toString()
    return script
}

println binding.getVariables()