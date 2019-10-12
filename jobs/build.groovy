import groovy.text.SimpleTemplateEngine

println "building build job..."

def jfile = readFileFromWorkspace "pipelines/build.groovy"
def vars = binding.getVariables()

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

pipelineJob("${project}-Build") {
    displayName('Build')
    definition {
        cps { 
            script(template)
        }
    }
}