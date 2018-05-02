import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl

import jenkins.model.Jenkins

def domain = Domain.global()
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

def username = new File("/run/secrets/apigee-username").text.trim()
def password = new File("/run/secrets/apigee-password").text.trim()

def credentials = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  "apigee-credentials", "Apigee credentials",
  username,
  password
)

store.addCredentials(domain, credentials)
