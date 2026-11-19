def call(Map cfg = [:]) {
  def appName   = cfg.name ?: error('name is required')
  def buildCmd  = cfg.build ?: 'echo build'
  def testCmd   = cfg.test  ?: 'echo test'
  pipeline {
    agent any
    stages {
      stage('Build')  { steps { sh buildCmd } }
      stage('Test')   { steps { sh testCmd } }
      stage('Scan')   { steps { echo "sonar + xray for ${appName}" } }
      stage('Publish'){
        when { branch 'main' }
        steps { echo "publish ${appName}:${env.BUILD_NUMBER}" }
      }
    }
    post { failure { notifyTeam(appName) } }
  }
}
