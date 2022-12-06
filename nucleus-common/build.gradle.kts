plugins {
    id("nucleus.base-conventions")
    id("nucleus.publishing-conventions")
}

dependencies {
    api(project(":nucleus-api"))
    // TODO Isolate javelin as an implementation detail with a messenger API
    compileOnly("fr.xpdustry:javelin-core:${Versions.javelin}")
    // TODO Investigate google/auto as a replacement for immutables
    compileOnly("org.immutables:value:${Versions.immutables}")
    annotationProcessor("org.immutables:value:${Versions.immutables}")

    implementation("org.mongodb:mongodb-driver-sync:${Versions.mongodb}") {
        exclude("org.slf4j", "slf4j-api") // Provided by Distributor
    }
    implementation("com.password4j:password4j:${Versions.password4j}") {
        exclude("org.slf4j", "slf4j-api") // Provided by Distributor
    }
}
