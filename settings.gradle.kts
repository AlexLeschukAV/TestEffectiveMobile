pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestEffectiveMobile"
include(":app")
include(":domain")
include(":domain:models")
include(":data")
include(":data:api")
include(":domain:api")
include(":data:impl")
include(":domain:impl")
include(":features")
include(":features:offers")
include(":features:favourites")
include(":features:responses")
include(":features:messages")
include(":features:profiles")
include(":core")
include(":core:base")
include(":features:authorization")
