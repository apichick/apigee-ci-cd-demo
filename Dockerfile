FROM jenkins/jenkins:lts
USER root
RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion
COPY conf/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY conf/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY jobs/job-template.xml /usr/share/jenkins/ref/jobs/job-template/config.xml