def jenkinsfile = readFileFromWorkspace('scripts/deploy.groovy')
println jenkinsfile

binding.variables.each {
//   println "${it.key} = ${it.value}"
//   println "\$${it.key}"
//   println "\$".getClass()
  def key = it.key.toString()
  def val = it.value.toString()
  print(key + ": ")
  println jenkinsfile.findAll(key)
  jenkinsfile = jenkinsfile.replaceAll(key, val)
}



pipelineJob("${project}-Deploy-Pipeline") {
    displayName('Deployment Pipeline')
    definition {
        cps { 
            script(jenkinsfile)
        }
    }
}