FROM jenkins/jenkins:lts
USER root
RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion
COPY setup/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY setup/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY jobs/job-template.xml /usr/share/jenkins/ref/jobs/job-template/config.xml
COPY config-files/apigee-settings.xml /usr/share/jenkins/ref/apigee-settings.xml