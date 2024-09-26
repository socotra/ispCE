rootProject.name = "ec-config-template"

pluginManagement {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.github.com/socotra/config-sdk-template") {
            credentials {
                username = "iquerspindola"
                password = "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"
            }
        }
    }
}
