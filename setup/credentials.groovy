import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey

import jenkins.model.Jenkins

def domain = Domain.global()
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

Properties props = new Properties()
File propsFile = new File('/run/secrets/apigee-credentials')
props.load(propsFile.newDataInputStream())

def username = props.getProperty('USERNAME')
def password = props.getProperty('PASSWORD')

def apigeeCredentials = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  "apigee-credentials", "Apigee credentials",
  username,
  password
)
store.addCredentials(domain, apigeeCredentials)

def privateKey = '/run/secrets/git-private-key'

def gitCredentials = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  'git-credentials',
  'jenkins',
  new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource(privateKey),
  null,
  'Git credentials'
)

store.addCredentials(domain, gitCredentials)