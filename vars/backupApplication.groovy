public void call(Map attrs = [:]) {
    // Get attributes
    final String sourcePath = attrs['sourcePath']
    final String backupDirectoryPath = attrs['backupDirectoryPath']

    // Create backup
    if (_isDirectoryEmpty(sourcePath)) {
        echo("${sourcePath} is empty, nothing to backup")

        // Generate empty mark
        final GString emptyMarkFileName = "no-backup-${env.b_PROJECT_NAME}-${env.BUILD_NUMBER}"
        final GString emptyMarkFilePath = "${backupDirectoryPath}/${emptyMarkFileName}"

        // Create mark
        sh(script:"touch ${emptyMarkFilePath}", label: 'Create no-backup mark')
        echo("Created empty mark instead of backup, ${emptyMarkFilePath}")
    } else {
        final GString backupZipFileName = "backup-${env.b_PROJECT_NAME}-${env.BUILD_NUMBER}.zip"
        final GString backupZipFilePath = "${backupDirectoryPath}/${backupZipFileName}"

        sh(script: "zip -q -r -9 ${backupZipFilePath} ${sourcePath}", label: 'zip application')
        echo("Current application was backed up in '${backupZipFilePath}'")
    }
}

private boolean _isDirectoryEmpty(String path) {
    // check if directory exists
    if (!fileExists(path)) {
        return true
    }
    // check if directory is empty
    final String listFilesOutput = sh(script: "ls --almost-all ${path}", label: 'List Directory Content', returnStdout: true)
    if (listFilesOutput) {
        return false
    }

    return true
}
