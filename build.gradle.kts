plugins {
    java
    id("socotra-ec-config-developer") version "v0.6.5"
}

`socotra-config-developer` {
    apiUrl.set(System.getenv("SOCOTRA_KERNEL_API_URL") ?: "https://api-kernel-dev.socotra.com")
    tenantLocator.set(System.getenv("SOCOTRA_KERNEL_TENANT_LOCATOR") ?: "5e8dd0c6-31f0-48ac-9f00-4938c322f123")
    personalAccessToken.set(System.getenv("SOCOTRA_KERNEL_ACCESS_TOKEN") ?: "SOCP_01J3NR6TV7JGR6YJ318QJQ02W0")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}
