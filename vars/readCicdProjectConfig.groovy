import cicd.config.DeploymentConfigs

public Map call() {
    final Map cicdConfigMap = DeploymentConfigs.globalConfigs['cicdConfig']
    final String pipelineConfigFolderPath = cicdConfigMap['pipelineConfigFolderPath']
    final GString pipelineConfigYamlPath = "${pipelineConfigFolderPath}/pipeline-config.yaml"

    // Read Config
    final Map pipelineConfigMap = readYaml(file: pipelineConfigYamlPath)
    return pipelineConfigMap
}
