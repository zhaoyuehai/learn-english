import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
//    id("com.palantir.docker") version "0.25.0"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "com.yuehai"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
//    mavenCentral()
    maven("http://maven.aliyun.com/nexus/content/groups/public")
//    maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("mysql:mysql-connector-java")
//    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.apache.commons:commons-pool2:2.8.0")

    implementation("com.alibaba:fastjson:1.2.75")
    implementation("com.alibaba:druid-spring-boot-starter:1.1.20")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.2")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.2.13")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

//docker {
//    //镜像名称
//    name = "${project.group}/${tasks.jar.get().archiveBaseName}"
//    setDockerfile(File("src/docker/Dockerfile"))
//    files(tasks.jar.get().archiveFile)
//    buildArgs(mapOf(Pair("JAR_FILE","${tasks.jar.get().archiveFileName}")))
//}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}