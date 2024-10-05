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
include(":core")
include(":core:base")
include(":features:authorization")
include(":features:messages")
include(":features:profile")
include(":features:responses")
include(":core:common")
