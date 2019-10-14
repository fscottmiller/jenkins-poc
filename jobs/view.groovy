// creates a view for the project

listView("${project}") {
    jobFilters {
        regex {
            matchType(matchType.INCLUDE_MATCHED)
            regex(".*${project}.*")
        }
        columns {
            status()
            weather()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
            buildButton()
        }
    }
}