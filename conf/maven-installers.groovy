import hudson.tasks.Maven.MavenInstallation
import hudson.tools.InstallSourceProperty
import hudson.tools.ToolProperty
import hudson.tools.ToolPropertyDescriptor
import hudson.util.DescribableList

import jenkins.model.Jenkins

def versions = [
  "maven-3.x": "3.5.2"
]

def desc = Jenkins.instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0]

def installations = []

for(v in versions) {
    def installer = new hudson.tasks.Maven.MavenInstaller(v.value)
    def installerProps = new InstallSourceProperty([installer])
    def installation = new MavenInstallation(v.key, null, [installerProps])
    installations.push(installation)
}

desc.setInstallations(installations.toArray(new MavenInstallation[0]))

desc.save() 