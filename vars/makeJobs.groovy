import groovy.text.SimpleTemplateEngine

def call(project, data) {
    fixData(data)
    // makeEnvJobs(project, data)
    // makeEnvJobs(project, data)
}

def makeJobs(project, data) {
    jobDsl targets: ['jobs/*.groovy'].join('\n'), 
        additionalParameters: [
            project: project, 
            data: data
        ],
        removedJobAction: 'DELETE',
        removedViewAction: 'DELETE'
}

// def makeEnvJobs(project, data) {
//     for (environment in data['environments']) {
//         jobDsl targets: ['jobs/env/env.groovy'].join('\n'), 
//             additionalParameters: [
//                 project: project, 
//                 data: data,
//                 environment: environment,
//                 current: environment,
//             ],
//             removedJobAction: 'DELETE',
//             removedViewAction: 'DELETE'
//     }
// }

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
