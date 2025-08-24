import org.kordamp.gradle.plugin.markdown.tasks.MarkdownToHtmlTask

buildscript {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute(module("com.overzealous:remark:1.1.0"))
                .using(module("com.wavefront:remark:2023-07.07"))
                .because("not available on maven central anymore")
        }
    }
}

plugins {
    id("org.kordamp.gradle.markdown") version "2.2.0"
}

tasks.register<MarkdownToHtmlTask>("myTask") {
    val htmlBuildDir = file(layout.buildDirectory.dir("html"))
    sourceDir = file(System.getProperty("user.home"))
            .resolve("Documents/Notes/Numenor Labs")
    outputDir = htmlBuildDir
    doLast {
        copy {
            from(htmlBuildDir.resolve("Workstation setup.html"))
            rename("Workstation setup.html", "workstation-setup.html")
            into(file("docs"))
        }
    }
}
