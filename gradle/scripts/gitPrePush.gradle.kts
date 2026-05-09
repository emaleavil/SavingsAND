import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

gradle.projectsLoaded {
    rootProject.tasks.register("installPrePushTask") {
        doLast {
            val gitHooksDir = Paths.get(rootDir.absolutePath, ".git", "hooks")
            val sourceFile = Paths.get(rootDir.absolutePath, "gradle", "scripts", "pre-push")

            if (Files.exists(gitHooksDir) && Files.exists(sourceFile)) {
                val hookFile = gitHooksDir.resolve("pre-push")
                Files.copy(sourceFile, hookFile, StandardCopyOption.REPLACE_EXISTING)
                hookFile.toFile().setExecutable(true)
                println("✅ Git hook installed successfully!")
            } else {
                println("⚠️ Skipping Git hook installation: .git directory or script not found.")
            }
        }
    }
}
