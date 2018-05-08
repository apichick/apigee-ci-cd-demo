import jenkins.model.Jenkins
import org.jenkinsci.plugins.configfiles.GlobalConfigFiles
import org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig

def configFiles = Jenkins.instance.getExtensionList(GlobalConfigFiles)[0]

def id = 'apigee-settings'
def name = id
def comment = 'Apigee settings'
def content = new File('/usr/share/jenkins/ref/apigee-settings.xml').text

def configFile = new MavenSettingsConfig(id, name, comment, content, false, null)

configFiles.save(configFile)