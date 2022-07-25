plugins {
    `java-library`
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
            name("GemsEconomy")
            name("JustAttribute")
            name("JustLevel")
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-chat")
    install("module-lang")
    install("module-database")
    install("platform-bukkit")
    install("expansion-command-helper")
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
    compileOnly("net.sakuragame.eternal:JustLevel:1.1.3-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:JustAttribute:1.1.1-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:GemsEconomy:4.9.5-SNAPSHOT@jar")
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