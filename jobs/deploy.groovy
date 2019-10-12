import groovy.text.SimpleTemplateEngine

println "building deploy job..."

def jfile = readFileFromWorkspace "pipelines/deploy.groovy"
def vars = binding.getVariables()

def fixParams(data) {
    for (i in data) {
        if (i.value instanceof Collection) {
            println "Iterating through ${i}..."
            fixParams(i.value)
        }
        else {
            i.value = "'$it'"
        }
    }
}
for (i in vars) {
    println "${i.key} : ${i.value}"
}
fixParams(vars)
// for (i in vars) {
//     if (i.value instanceof Collection) {
//         i.value = i.value.collect{ "'$it'" }
//     }
// }
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