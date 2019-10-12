import groovy.text.SimpleTemplateEngine

println "building deploy job..."

def jfile = readFileFromWorkspace "pipelines/deploy.groovy"
def vars = binding.getVariables()

def fixData(data) {
    println "---- function fixParams -----"
    println data.getClass()
    if (data instanceof Collection) {
        println "Iterating..."
        // fixParams(i.value)
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