import groovy.text.SimpleTemplateEngine

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

def getPipelineScript(String file, Map vars) {
    fixData(vars)
    def script = new SimpleTemplateEngine().createTemplate(file).make(vars).toString()
    return script
}

return this

