public void call() {
    final String jobBaseName = env.JOB_BASE_NAME // repository-name_deploy
    final List<String> splitJobBaseName = jobBaseName.split(/_/)

    final String projectName = splitJobBaseName[0]
    final String pipelineActionName = splitJobBaseName[1]

    env['b_PROJECT_NAME'] = projectName
    env['b_PIPELINE_ACTION_NAME'] = pipelineActionName
}
