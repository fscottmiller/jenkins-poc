import groovy.text.SimpleTemplateEngine

println "building deploy job..."

def jfile = readFileFromWorkspace "pipelines/deploy.groovy"
def vars = binding.getVariables()

for (i in vars) {
    if (i.value instanceof Collection) {
        i.value = i.value.collect{ "'$it'" }
    }
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