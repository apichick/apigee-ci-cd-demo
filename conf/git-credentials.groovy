import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey

import jenkins.model.Jenkins

def privateKey = '/run/secrets/git-private-key'
def domain = Domain.global()
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

def credentials = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  'git-credentials',
  'jenkins',
  new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource(privateKey),
  null,
  'Git credentials'
)

store.addCredentials(domain, credentials)
