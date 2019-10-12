import groovy.text.SimpleTemplateEngine

class GetScript {
    static void fixData(data) {
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

    static String getPipelineScript(String file, Map vars) {
        def jfile = readFileFromWorkspace file
        fixData(vars)
        def script = new SimpleTemplateEngine().createTemplate(jfile).make(vars).toString()
        return script
    }
}
