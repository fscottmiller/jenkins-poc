import groovy.text.SimpleTemplateEngine

println "building deploy job..."

def jfile = readFileFromWorkspace "pipelines/deploy.groovy"
def vars = binding.getVariables()

def fixData(data) {
    println "---- function fixParams -----"
    for (i in data) {
        if (i.value instanceof Map) {
            println "Iterating ${i.key}..."
            fixData(i)
        } else if (i.value instanceof Collection) {
            println "Fixing collection values"
            i.value = i.value.collect{ "'$it'" }
        }
    }
}

fixData(vars['data'])
// for (i in vars) {
//     if (i.value instanceof Collection) {
//         i.value = i.value.collect{ "'$it'" }
//     }
// }
println "------------------------------------------------------------------------------"
for (i in vars) {
    println "${i.key} : ${i.value}"
}


def engine = new SimpleTemplateEngine()
template = engine.createTemplate(jfile).make(vars).toString()

pipelineJob("${project}-Deploy") {
    displayName('Deploy')
    definition {
        cps { 
            script(template)
        }
    }
}