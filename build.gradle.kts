plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.41"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    description {
        contributors {
            name("闲蛋")
        }
        dependencies {
            name("DataManager-Bukkit")
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-chat")
    install("module-lang")
    install("platform-bukkit")
    classifier = null
    version = "6.0.9-31"
}

repositories {
    maven {
        credentials {
            username = "a5phyxia"
            password = "zxzbc13456"
        }
        url = uri("https://maven.ycraft.cn/repository/maven-snapshots/")
    }
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v11200:11200")
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    compileOnly("net.sakuragame:datamanager-bukkit-api:2.0.2-SNAPSHOT@jar") {
        isTransitive = true
    }
    compileOnly("net.sakuragame.eternal:DragonCore:2.6.1-SNAPSHOT@jar")
    compileOnly("com.taylorswiftcn:UIFactory:1.0.2-SNAPSHOT@jar")
    compileOnly(kotlin("stdlib"))
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

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            groupId = project.group.toString()
        }
    }
}