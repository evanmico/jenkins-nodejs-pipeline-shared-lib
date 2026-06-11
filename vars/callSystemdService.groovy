import cicd.os.SystemdService

public void call(Map attrs = [:]) {
    // Get Attributes
    final String command = attrs['command']
    final String serviceName = attrs['serviceName']

    // Create SystemdService object
    final SystemdService systemdService = new SystemdService(serviceName)

    // Interact with Systemd
    switch (command) {
        case 'stop':
            systemdService.stopService()
            sleep(time: 15, unit: 'SECONDS')
            _assertServiceStatus(systemdService, 'stopped')
            break
        case 'start':
            systemdService.startService()
            sleep(time: 15, unit: 'SECONDS')
            _assertServiceStatus(systemdService, 'started')
            break
        default:
            error("systemd command ${command} not supported")
    }
}

private void _assertServiceStatus(SystemdService systemdService, String expectedStatus) {
    switch(expectedStatus) {
        case 'stopped':
            if (systemdService.isServiceActive()) {
                error("Systemd Service '${systemdService.SERVICE_NAME}' is not stopped")
            } else {
                echo("Systemd Service '${systemdService.SERVICE_NAME}' is stopped")
            }
            break
        case 'started':
            if (systemdService.isServiceActive()) {
                echo("Systemd Service '${systemdService.SERVICE_NAME}' is started")
            } else {
                error("Systemd Service '${systemdService.SERVICE_NAME}' is not started")
            }
            break
        default:
            error("Expected Status '${expectedStatus}' is not supported")
    }
}
