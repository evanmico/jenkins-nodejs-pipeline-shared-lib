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
            break
        default:
            error("systemd command ${command} not supported")
    }
}
