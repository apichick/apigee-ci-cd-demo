import hudson.security.csrf.DefaultCrumbIssuer

import jenkins.model.Jenkins

def instance = Jenkins.getInstance()

instance.setCrumbIssuer(new DefaultCrumbIssuer(true))

instance.save()
