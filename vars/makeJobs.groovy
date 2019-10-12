import groovy.text.SimpleTemplateEngine

def call(project, data) {
    fixData(data)
    generate(project, data)
}

def generate(project, data) {
    jobDsl targets: ['jobs/*.groovy'].join('\n'), 
        additionalParameters: [
            project: project, 
            data: data
        ],
        removedJobAction: 'DELETE',
        removedViewAction: 'DELETE'
}

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
