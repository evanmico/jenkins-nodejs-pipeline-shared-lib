public void call() {
    _npmInstall()
}

private void _npmInstall() {
    // if npm ci doesn't work, use 'npm install'
    sh(script: 'npm ci', label: 'Install npm dependencies')
}
