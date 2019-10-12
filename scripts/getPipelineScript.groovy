import groovy.text.SimpleTemplateEngine

def fixData(data) {
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

def call(String file, Map vars) {
    fixData(vars)

    def engine = new SimpleTemplateEngine()
    def script = engine.createTemplate(file).make(vars).toString()

    return script
}

return this

