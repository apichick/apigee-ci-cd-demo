import hudson.tools.InstallSourceProperty

import jenkins.model.Jenkins
import jenkins.plugins.nodejs.tools.NodeJSInstallation
import jenkins.plugins.nodejs.tools.NodeJSInstaller

def instance = Jenkins.getInstance()

def desc = instance.getDescriptor("jenkins.plugins.nodejs.tools.NodeJSInstallation")

def versions = [
  "nodejs-8.x": "8.11.1"
]

def globalPackages = ''
def refreshHours = 100

def installations = []

for (v in versions) {
  def installer = new NodeJSInstaller(v.value, globalPackages, refreshHours)
  def installerProps = new InstallSourceProperty([installer])
  def installation = new NodeJSInstallation(v.key, "", [installerProps])
  installations.push(installation)
}

desc.setInstallations(installations.toArray(new NodeJSInstallation[0]))

desc.save() 


