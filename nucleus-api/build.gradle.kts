plugins {
    id("nucleus.base-conventions")
    id("nucleus.publishing-conventions")
}

dependencies {
    compileOnly(libs.immutables.value.annotations)
    annotationProcessor(libs.immutables.value.processor)
}