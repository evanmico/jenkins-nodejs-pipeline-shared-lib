import cicd.config.DeploymentConfigs

public void call(String repositoryName, String sourceBranch) {
    // Check if sourceBranch empty
    if (!sourceBranch) {
        error("Source branch for git clone is '${sourceBranch}'") // fails if empty and shows the value
    }

    // Clone Repo
    final String gitUrl = _generateGitUrl(repositoryName)
}

private String _generateGitUrl(String repositoryName) {
    final String remoteUrlPattern = DeploymentConfigs.globalConfigs["git"]["remoteHttps"]
    return remoteUrlPattern.replace("<REPOSITORY_NAME>", repositoryName)
}
