plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.56"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

taboolib {
    install("common")
    install("common-5")
    install("module-chat")
    install("module-configuration")
    install("module-database")
    install("module-kether")
    install("module-lang")
    install("module-metrics")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("expansion-javascript")
    install("expansion-alkaid-redis")
    classifier = null
    version = "6.0.12-69"
    description {
        contributors {
            name("CPJiNan")
            name("Golden_Water")
            name("2000000")
            name("xiaochunkun")
            name("Sting")
        }
        dependencies {
            name("PlaceholderAPI").optional(true)
            name("MythicMobs").optional(true)
            name("AttributePlus").optional(true)
            name("SX-Attribute").optional(true)
            name("OriginAttribute").optional(true)
        }
    }
    relocate("kotlinx.serialization", "kotlinx.serialization160")
}

repositories {
    mavenCentral()
    maven(url = "https://mvn.lumine.io/repository/maven-public/")
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly(kotlin("stdlib"))
    taboo("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    taboo("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.6.0")
    compileOnly("io.lumine.xikage:MythicMobs:4.11.0@jar")
    compileOnly("io.lumine:Mythic-Dist:5.3.5@jar")
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

//publishing {
//    repositories {
//        maven {
//            url = uri("https://repo.tabooproject.org/repository/releases")
//            credentials {
//                username = project.findProperty("taboolibUsername").toString()
//                password = project.findProperty("taboolibPassword").toString()
//            }
//            authentication {
//                create<BasicAuthentication>("basic")
//            }
//        }
//    }
//    publications {
//        create<MavenPublication>("library") {
//            from(components["java"])
//            groupId = project.group.toString()
//        }
//    }
//}